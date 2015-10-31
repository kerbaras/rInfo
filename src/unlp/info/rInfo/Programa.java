package unlp.info.rInfo;

import unlp.info.rInfo.areas.*;
import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.Workspace;

public class Programa extends Thread {

	private Robot[] robots;
	private Area[] areas;
	
	
	public static void main(String[] args) {
		
		Robot[] robots = new Robot[2];
		Area[] areas = new Area[3];
		
		robots[0] = new Robot1("Robot1");
		robots[1] = new Robot1("Robot2");

		areas[0] = new AreaC(0,0, 6,8);
		areas[1] = new AreaP(7,0 , 12,8);
		areas[2] = new AreaPC(0,9 , 12,13);
		

		Programa program = new Programa();
		try{
			program.run();
		}catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public Programa(){
		this.robots = null;
		this.areas = null;
	}
	
	@Override
	public void run() throws RuntimeException{
		Workspace workspace = new Workspace(this);
		workspace.setVisible(true);
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
