package unlp.info.rInfo;

import java.awt.Point;

public abstract class Robot extends unlp.info.rInfo.gui.Robot {
	
	public Robot(String nombre) {
		super(nombre);
	}
	
	protected synchronized void bloquearEsquina(Point e){
		while(true){
			
		}
		
	}

	protected synchronized void liberarEsquina(Point e){
		
	}
	
	protected void enviarMensaje(Robot robot, String id , String data){
		
	}
	
	protected void enviarMensaje(Robot robot, String id, int data){
		
	}
	
	protected void enviarMensaje(Robot robot, String id, boolean data){
		
	}
	
	protected void enviarMensaje(Robot robot, String data){
		
	}
	
	public synchronized void recivirMensaje(Robot robot, String data){

	}
	
	protected int getMensaje(){
		return 0;
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id , Object data){
		
	}
}
