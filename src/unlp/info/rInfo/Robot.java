package unlp.info.rInfo;

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
		Point esquina = new Point(x,y);
		try {
			Boolean esq = Programa.getEsquina(esquina);
			synchronized (esq) {
				while (Programa.isEsquinaBlocked(esquina)) {
						esq.wait();
				}
				Programa.bloquearEsquina(esquina);
				robot.dispatchChangeStateListeners(robot, "Ejecutando");
			}
		}catch (InterruptedException e){
			robot.dispatchChangeStateListeners(robot, "Error");
			e.printStackTrace();
			Programa.informar("Error", this);
		}catch (Exception e) {
			robot.dispatchChangeStateListeners(robot, "Error");
			e.printStackTrace();
			Programa.informar("Error", this);
		}
		
	}

	protected void liberarEsquina(int x, int y){
			Programa.liberarEsquina(new Point(x, y));
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

	protected void informar(Object msg){
		Programa.informar(msg, this);
	}
}
