package unlp.info.rInfo;

import java.awt.Point;

public abstract class Robot extends unlp.info.rInfo.gui.Robot implements Runnable {
	public static final int	NORTE 	= 0,
								OESTE 	= 1,
								ESTE 	= 2,
								SUR 	= 3;
	
	private int sentido;
	private boolean mensaje;
	private String mensaje_data;
	protected Listener[] listeners;
	
	public Robot(String nombre) {
		super(nombre);
	}

	protected void mover(){
		switch (sentido){
			case NORTE:
				pos.setLocation(pos.getX() + 1, pos.getY());
				break;
			case SUR:
				pos.setLocation(pos.getX() - 1, pos.getY());
				break;
				
			case OESTE:
				pos.setLocation(pos.getX(), pos.getY() + 1);
				break;
			case ESTE:
				pos.setLocation(pos.getX(), pos.getY() - 1);
				break;
		}
		
	}
	
	protected void derecha(){
		
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
		return mensaje_data;
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id , String data){
		
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id, int data){
		
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id, boolean data){
		
	}

	public abstract void draw();
}
