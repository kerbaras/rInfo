package unlp.info.rInfo;

import unlp.info.rInfo.areas.*;
import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.Workspace;

public class Programa extends Thread {

	private Robot[] robots;
	private Area[] areas;
	
	
	public static void main(String[] args) {
		
		Robot[] robots = new Robot[2];
		Area[] areas = new Area[1];
		
		robots[0] = new Robot1("Robot1");
		robots[1] = new Robot1("Robot2");

		areas[0] = new AreaP(0,0, 99,99);
		

		Programa program = new Programa();
		program.setRobots(robots);
		program.setAreas(areas);
		program.run();
		
	}
	
	public Programa(){
		this.robots = null;
		this.areas = null;
	}
	
	@Override
	public void run(){
		Workspace workspace = new Workspace(this);
		workspace.setVisible(true);
		
		for (Robot robot : robots) {
			(new Thread(robot)).start();
		}
	}
	
	

	public Robot[] getRobots() {
		return robots;
	}

	public void setRobots(Robot[] robots) {
		this.robots = robots;
	}

	public Area[] getAreas() {
		return areas;
	}

	public void setAreas(Area[] areas) {
		this.areas = areas;
	}

}
