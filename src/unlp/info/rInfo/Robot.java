package unlp.info.rInfo;

import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.GRobot;
import java.awt.*;

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
		if(hayFlorEnLaEsquina()){
			Esquina esquina = Programa.getEsquina(robot.getPos());
			esquina.setFlores(esquina.getFlores() - 1);
			flores += 1;
			Programa.getWorkspace().getCity().drawResource(robot.getPos());
			robot.dormir();
		}
	}
	protected void depositarFlor() {
		if(flores > 0){
			Esquina esquina = Programa.getEsquina(robot.getPos());
			esquina.setFlores(esquina.getFlores() + 1);
			flores -= 1;
			Programa.getWorkspace().getCity().drawResource(robot.getPos());
			robot.dormir();
		}
	}
	protected void tomarPapel() {
		if(hayPapelEnLaEsquina()){
			Esquina esquina = Programa.getEsquina(robot.getPos());
			esquina.setPapeles(esquina.getPapeles() - 1);
			papeles += 1;
			Programa.getWorkspace().getCity().drawResource(robot.getPos());
			robot.dormir();
		}
	}
	protected void depositarPapel() {
		if(papeles > 0){
			Esquina esquina = Programa.getEsquina(robot.getPos());
			esquina.setPapeles(esquina.getPapeles() + 1);
			flores -= 1;
			Programa.getWorkspace().getCity().drawResource(robot.getPos());
			robot.dormir();
		}
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
			e.printStackTrace();
			Programa.informar("Error: " + e.getMessage(), this);
		}

	}
	protected void liberarEsquina(int x, int y){
		try {
			Programa.getEsquina(new Point(x,y)).liberar();
			Programa.liberarEsquina(new Point(x, y));
		}catch (Exception e) {
			robot.dispatchChangeStateListeners(robot, "Error");
			e.printStackTrace();
			Programa.informar("Error: " + e.getMessage(), this);
		}
	}

	protected void informar(Object msg){
		Programa.informar(msg, this);
	}


	public abstract void comenzar();

	public void iniciar(int x, int y){
		robot.setPos(new Point(x, y));
		Programa.registrarRobot(this, robot);
		robot.boot();
	}

	public void asignarArea(Area area){
		try {
			area.addRobot(this);
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	public int getId(){
		return robot.getId();
	}

	public void setColor(Color color){
		this.robot.setColor(color);
	}

	public void recivirMensaje(Object mensaje, Robot from){
		recivirMensaje(new Mensaje(from, mensaje));
	}

	public void recivirMensaje(Mensaje mensaje){
		buzon.newMensaje(mensaje);
	}

	protected void enviarMensaje(int id, Object mensaje){
		enviarMensaje(Programa.getRobot(id), mensaje);
	}

	protected void enviarMensaje(Robot robot, Object mensaje){
		robot.recivirMensaje(mensaje, this);
	}

	protected void enviarMensaje(Object mensaje){
		Programa.enviarMensaje(this, mensaje);
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
