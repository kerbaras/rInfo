package unlp.info.rInfo;

import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.GRobot;
import java.awt.*;
import java.util.Enumeration;

public abstract class Robot{
	private GRobot robot;
	private int flores = 0, papeles = 0;
	private Buzon buzon = new Buzon();

	public Robot(int id) {
		robot = new GRobot(id, this);
	}

	protected void mover(){ this.robot.mover();	}
	protected void derecha(){ this.robot.derecha(); }

	protected int getAv() { return this.robot.getPos().x; }
	protected int getCa() { return this.robot.getPos().y; }

	protected boolean hayFlorEnLaBolsa() { return flores > 0; }
	protected boolean hayPapelEnLaBolsa(){ return papeles > 0;}

	protected boolean hayFlorEnLaEsquina() { return Programa.getFlores(robot.getPos()) > 0; }
	protected boolean hayPapelEnLaEsquina() { return Programa.getPapeles(robot.getPos()) > 0; }
	protected boolean hayObstaculoEnLaEsquina() { return Programa.hayObstaculo(robot.getPos()); }

	protected void tomarFlor() {
		if (!hayFlorEnLaEsquina()) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("No hay flor en la esquina"));
			return;
		}
		Esquina esquina = Programa.getEsquina(robot.getPos());
		esquina.setFlores(esquina.getFlores() - 1);
		flores++;
		robot.dispatchChangeBolsaListeners(flores, papeles);
		Programa.getWorkspace().getCity().drawResource(robot.getPos());
		robot.dormir();
	}

	protected void depositarFlor() {
		if (flores <= 0) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("No hay flor en la bolsa"));
			return;
		}
		Esquina esquina = Programa.getEsquina(robot.getPos());
		esquina.setFlores(esquina.getFlores() + 1);
		flores--;
		robot.dispatchChangeBolsaListeners(flores, papeles);
		Programa.getWorkspace().getCity().drawResource(robot.getPos());
		robot.dormir();
	}

	protected void tomarPapel() {
		if (!hayPapelEnLaEsquina()) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("No hay papel en la esquina"));
			return;
		}
		Esquina esquina = Programa.getEsquina(robot.getPos());
		esquina.setPapeles(esquina.getPapeles() - 1);
		papeles++;
		robot.dispatchChangeBolsaListeners(flores, papeles);
		Programa.getWorkspace().getCity().drawResource(robot.getPos());
		robot.dormir();
	}

	protected void depositarPapel() {
		if(papeles <= 0) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("No hay papel en la bolsa"));
			return;
		}
		Esquina esquina = Programa.getEsquina(robot.getPos());
		esquina.setPapeles(esquina.getPapeles() + 1);
		papeles--;
		robot.dispatchChangeBolsaListeners(flores, papeles);
		Programa.getWorkspace().getCity().drawResource(robot.getPos());
		robot.dormir();
	}

	protected void bloquearEsquina(int x, int y){
		robot.dispatchChangeStateListeners(robot, "Esperando");
		try {
			Esquina esquina = Programa.getEsquina(new Point(x,y));
			synchronized (esquina) {
				while (esquina.isBlocked()) {
					esquina.wait();
				}
				esquina.bloquear();
				Programa.bloquearEsquina(new Point(x,y));
			}
			robot.dispatchChangeStateListeners(robot, "Ejecutando");
		}catch (Exception e) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(e);
		}

	}
	protected void liberarEsquina(int x, int y){
		try {
			Programa.getEsquina(new Point(x,y)).liberar();
			Programa.liberarEsquina(new Point(x, y));
		}catch (Exception e) {
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(e);
		}
	}

	protected void informar(Object msg){
		Programa.informar(msg, this);
	}


	public abstract void comenzar();

	public void iniciar(int x, int y){
		if(this.robot.isRunning()){
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("No se puede iniciar un robot ya iniciado."));
			return;
		}
		Point start = new Point(x, y);
		Esquina esquina = Programa.getEsquina(start);
		synchronized (esquina.moveBlock) {
			if (esquina.getRobot() != null) {
				robot.dispatchChangeStateListeners(robot, "Error");
				Programa.handle(new Exception("Robot " + robot.getId() + " colisionó con Robot" + esquina.getRobot().getId()));
				return;
			}
			robot.setPos(start);
			esquina.setRobot(this);
			Programa.registrarRobot(this, robot);
			//robot.boot();
		}
	}

	public void asignarArea(Area area){
		try {
			area.addRobot(this);
		}catch (Exception e){
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(e);
		}
	}

	public void setBolsa(int flores, int papeles){
		if(this.robot.isRunning()){
			robot.dispatchChangeStateListeners(robot, "Error");
			Programa.handle(new Exception("Robot ejecutandose. No se puede asignar bolsa"));
			return;
		}
		this.papeles = papeles;
		this.flores = flores;
		robot.dispatchChangeBolsaListeners(flores, papeles);
	}

	public int getId(){
		return robot.getId();
	}

	public void setColor(Color color){
		this.robot.setColor(color);
	}

	private void recivirMensaje(Object mensaje, Robot from){
		recivirMensaje(new Mensaje(from, mensaje));
	}

	private void recivirMensaje(Mensaje mensaje){
		buzon.newMensaje(mensaje);
	}

	protected void enviarMensaje(int id, Object mensaje){
		enviarMensaje(Programa.getRobot(id), mensaje);
	}

	private void enviarMensaje(Robot robot, Object mensaje){
		robot.recivirMensaje(mensaje, this);
	}

	protected void enviarMensaje(Object mensaje){
		Enumeration<Robot> robots = Programa.getRobots();
		while (robots.hasMoreElements()){
			Robot robot = robots.nextElement();
			if(robot != this)
				robot.recivirMensaje(mensaje, this);
		}
	}

	protected Mensaje esperarMensaje(){
		robot.dispatchChangeStateListeners(robot, "Esperando");
		return buzon.esperar();
	}

	protected Mensaje esperarMensaje(Robot robot){
		this.robot.dispatchChangeStateListeners(this.robot, "Esperando");
		return buzon.esperar(robot);
	}

	protected Mensaje esperarMensaje(int id){
		robot.dispatchChangeStateListeners(robot, "Esperando");
		return buzon.esperar(Programa.getRobot(id));
	}
}
