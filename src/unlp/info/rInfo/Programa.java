package unlp.info.rInfo;

import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.GRobot;
import unlp.info.rInfo.gui.Workspace;
import java.awt.*;
import java.util.ArrayList;

public final class Programa{

	private static ArrayList<Robot> robots = new ArrayList<Robot>();
	private static ArrayList<Area> areas = new ArrayList<Area>();
	private static Boolean[][] esquinasBloqueadas = new Boolean[100][100];
	private static Integer[][] papeles = new Integer[100][100];
	private static Integer[][] flores = new Integer[100][100];
	private static Boolean[][] obstaculos = new Boolean[100][100];
	private static Workspace workspace;

	public static synchronized void registrarRobot(Robot robot, GRobot guiRobot){
		if (workspace == null){
			workspace = new Workspace();
			workspace.setVisible(true);
		}
		addRobot(robot);
		workspace.registrarRobot(guiRobot);
	}

	public static ArrayList<Robot> getRobots() {
		return robots;
	}

	public static void addRobot(Robot robot){
		robots.add(robot);
	}

	public static ArrayList<Area> getAreas() {
		return areas;
	}

	public static void addArea(Area area){
		areas.add(area);
	}

	public static synchronized boolean isEsquinaBlocked(Point esquina){
		Boolean esq = getEsquina(esquina);
		return  (esq == null) ? false : esq;
	}

	public static synchronized void bloquearEsquina(Point esquina){
		Boolean esq = getEsquina(esquina);
		esq = true;
	}

	public static synchronized void liberarEsquina(Point esquina){
		Boolean esq = getEsquina(esquina);
		esq = false;
		esq.notify();
	}

	public static Boolean getEsquina(Point esquina) {
		Boolean esq = esquinasBloqueadas[esquina.x][esquina.y];
		if(esq == null){
			esquinasBloqueadas[esquina.x][esquina.y] = new Boolean(false);
		}
		return esquinasBloqueadas[esquina.x][esquina.y];
	}

	public static void informar(Object msg, Robot robot){
		workspace.informar(msg, "Robot " + robot.getId());
		System.out.println("Robot "+ robot.getId() + ": " + msg);
	}

	public static void setPapeles(Point esquina, int cant){
		papeles[esquina.x][esquina.y] = cant;
	}

	public static int getPapeles(Point esquina){
		return (papeles[esquina.x][esquina.y]  == null) ? 0 : papeles[esquina.x][esquina.y];
	}

	public static void setFlores(Point esquina, int cant){
		flores[esquina.x][esquina.y] = cant;
	}

	public static int getFlores(Point esquina){
		return (flores[esquina.x][esquina.y]  == null) ? 0 : flores[esquina.x][esquina.y];
	}

	public static void setObstaculos(Point esquina, boolean cant){
		obstaculos[esquina.x][esquina.y] = cant;
	}

	public static boolean getObstaculos(Point esquina){
		return (obstaculos[esquina.x][esquina.y]  == null) ? false : obstaculos[esquina.x][esquina.y];
	}
}
