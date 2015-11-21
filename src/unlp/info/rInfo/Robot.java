package unlp.info.rInfo;

import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.GRobot;

import java.awt.*;

public abstract class Robot{
	private GRobot robot;

	public Robot(int id) {
		robot = new GRobot(id, this);
	}

	public abstract void comenzar();
	
	protected void bloquearEsquina(int x, int y){
		robot.dispatchChangeStateListeners(robot, "Esperando");
		try {
			Esquina esquina = Programa.getEsquina(new Point(x,y));
			synchronized (esquina) {
				while (esquina.isBlocked()) {
					esquina.wait();
				}
				esquina.bloquear();
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
		}catch (Exception e) {
			robot.dispatchChangeStateListeners(robot, "Error");
			e.printStackTrace();
			Programa.informar("Error: " + e.getMessage(), this);
		}
	}

	public int getId(){
		return robot.getId();
	}

	public void setColor(Color color){
		this.robot.setColor(color);
	}

	protected void mover(){ this.robot.mover();	}
	protected void derecha(){
		this.robot.derecha();
	}
	protected void setPos(int x, int y){ this.robot.setPos(new Point(x, y));}
	protected int getAv() {return this.robot.getPos().x;}
	protected int getCa() {return this.robot.getPos().y;}

	public void iniciar(int x, int y){
		Programa.registrarRobot(this, robot);
		robot.setPos(new Point(x, y));
		robot.boot();
	}

	public void asignarArea(Area area){
		try {
			area.addRobot(this);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void informar(Object msg){
		Programa.informar(msg, this);
	}
}
