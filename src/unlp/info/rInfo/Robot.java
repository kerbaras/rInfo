package unlp.info.rInfo;

import java.awt.Point;

public abstract class Robot extends unlp.info.rInfo.gui.Robot {
	private boolean mensaje;
	private String mensaje_data;
	protected Listener[] listeners;
	
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
		mensaje = true;
		mensaje_data = data;
	}
	
	protected String getMensaje(){
		while(!mensaje){
			continue;
		}
		mensaje = false;
		return mensaje_data;
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id , String data){
		
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id, int data){
		
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id, boolean data){
		
	}
}
