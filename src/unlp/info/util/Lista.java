package unlp.info.util;

public class Lista<E>{
	private Nodo<E> pri, act, ult;
	
	public Lista(){
		pri = null;
		act = pri;
		ult = pri;
	}
	
	public void agregar(E dato){
		Nodo<E> nodo = new Nodo<E>(dato);
		if (pri != null){
			nodo.setSiguiente(ult);
			ult = nodo;
		}else{
			pri = nodo;
			ult = pri;
		}
	}
	
	public void agregarInicio(E dato){
		pri = new Nodo<E>(dato, pri);
	}
	
	public Nodo<E> avanzar(){
		return act = act.getSiguiente(); 
	}
	
	public E getDato(){
		return act.getDato();
	}
	
	public Nodo<E> getActual(){
		return act;
	}
	
	public Nodo<E> getPrimero(){
		return pri;
	}
	
	public Nodo<E> getSiguiente(){
		return act.getSiguiente();
	}
}