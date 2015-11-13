package unlp.info.rInfo;

import java.awt.Point;

public abstract class Robot extends unlp.info.rInfo.gui.Robot {
	private Programa programa;

	public Robot(String nombre) {
		super(nombre);
	}
	
	protected synchronized void bloquearEsquina(Point esquina){
		try {
			while (programa.isEsquinaBlocked(esquina)) {
				wait();
			}
			programa.bloquearEsquina(esquina);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}

	protected synchronized void liberarEsquina(Point esquina){
		programa.liberarEsquina(esquina);
	}
	
	protected void enviarMensaje(Robot robot, String id , String data){
		
	}
	
	protected void enviarMensaje(Robot robot, String id, int data){
		
	}
	
	protected void enviarMensaje(Robot robot, String id, boolean data){
		
	}
	
	protected void enviarMensaje(Robot robot, String data){
		
	}
	
	public synchronized void recibirMensaje(Robot robot, String data){

	}
	
	protected int getMensaje(){
		return 0;
	}
	
	public synchronized void dispatchMensaje(Robot robot, String id , Object data){
		
	}
}
