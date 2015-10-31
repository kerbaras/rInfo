package unlp.info.rInfo.gui;

import java.awt.Color;
import java.awt.Point;

public abstract class Robot {
	protected String nombre;
	protected Color corlor;
	protected Point pos;
	protected int Bolsa;
	
	public Robot(){
		
	}
	
	public Robot(String nombre){
		this.nombre = nombre;
	}
	
	public abstract void draw();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getCorlor() {
		return corlor;
	}

	public void setCorlor(Color corlor) {
		this.corlor = corlor;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getBolsa() {
		return Bolsa;
	}

	public void setBolsa(int bolsa) {
		Bolsa = bolsa;
	}
}
