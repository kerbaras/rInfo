package unlp.info.rInfo;

import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.GRobot;
import unlp.info.rInfo.gui.Workspace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public final class Programa{

	private static Hashtable<Integer, Robot> robots = new Hashtable<Integer, Robot>();
	private static ArrayList<Area> areas = new ArrayList<Area>();
	private static Esquina[][] esquinas = new Esquina[100][100];
	private static Workspace workspace;
	public static volatile int fps = 15;
	private static boolean running = false;

	public static synchronized void registrarRobot(Robot robot, GRobot guiRobot){
		if (workspace == null){
			running = true;
			workspace = new Workspace();
			workspace.setVisible(true);
		}
		addRobot(robot);
		workspace.registrarRobot(guiRobot);
	}

	public static void addRobot(Robot robot){
		if(robots.containsKey(robot.getId())){
			handle(new Exception("Duplicated Robot Key " + robot.getId()));
			return;
		}

		robots.put(robot.getId(), robot);
	}

	public static ArrayList<Area> getAreas() {
		return areas;
	}

	public static void addArea(Area area){
		areas.add(area);
	}

	public static Esquina getEsquina(Point e) {
		Esquina esquina = esquinas[e.x][e.y];
		if(esquina == null){
			esquinas[e.x][e.y] = new Esquina();
			esquina = esquinas[e.x][e.y];
		}
		return esquina;
	}

	public static boolean existeEsquina(Point e){
		return !(esquinas[e.x][e.y] == null);
	}

	public static void informar(Object msg, Robot robot){
		workspace.informar(msg, "Robot " + robot.getId());
		System.out.println("Robot "+ robot.getId() + ": " + msg);
	}

	public static void setPapeles(Point e, int cant){
		Esquina esquina = getEsquina(e);
		esquina.setPapeles(cant);
	}

	public static int getPapeles(Point e){
		if(existeEsquina(e)) {
			return esquinas[e.x][e.y].getPapeles();
		}else{
			return 0;
		}
	}

	public static void setFlores(Point e, int cant){
		Esquina esquina = getEsquina(e);
		esquina.setFlores(cant);
	}

	public static int getFlores(Point e){
		if(existeEsquina(e)) {
			return esquinas[e.x][e.y].getFlores();
		}else{
			return 0;
		}
	}

	public static void setObstaculo(Point e, boolean o){
		Esquina esquina = getEsquina(e);
		esquina.setObstaculo(o);
	}

	public static boolean hayObstaculo(Point e){
		if(existeEsquina(e)) {
			return esquinas[e.x][e.y].hayObstaculo();
		}else{
			return false;
		}
	}

	public static synchronized void liberarEsquina(Point e){
		workspace.getCity().drawFreeSquare(e);
	}

	public static synchronized void bloquearEsquina(Point e){
		workspace.getCity().drawBlockedSquare(e);
	}

	public static boolean isRunning() {
		return running;
	}

	public static Workspace getWorkspace() {
		return workspace;
	}

	public static Robot getRobot(int id){
		return robots.get(id);
	}

	public static Enumeration<Robot> getRobots(){
		return robots.elements();
	}

	public static void handle(Exception execption){

		if(workspace != null)
			workspace.mostrarMensaje(execption.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

		System.out.println(execption.getMessage());
		System.exit(0);
	}
}
