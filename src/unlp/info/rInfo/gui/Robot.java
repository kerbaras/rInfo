package unlp.info.rInfo.gui;

public abstract class Robot {
	private String nombre;
	
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
}
