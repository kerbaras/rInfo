package unlp.info.rInfo;

import java.awt.geom.Point2D;

public abstract class Robot extends unlp.info.rInfo.gui.Robot implements Runnable {
	public static final int	NORTE 	= 0,
								OESTE 	= 1,
								ESTE 	= 2,
								SUR 	= 3;
	
	private Point2D pos;
	private int color;
	private Bolsa bolsa;
	private int sentido;
	
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

	public abstract void draw();
}
