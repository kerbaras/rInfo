package unlp.info.util;

public class Nodo<E> {
	private E dato;
	private Nodo<E> siguiente;
	 
	public Nodo(){
		dato = null;
		siguiente = null;
	}
	
	public Nodo(E d){
		this();
		dato = d;
	}
	
	public Nodo(E d, Nodo<E> sig){
		dato = d;
		siguiente = sig;
	}
	
	public E getDato() {
		return dato;
	}
	
	public void setDato(E dato) {
		this.dato = dato;
	}
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
}