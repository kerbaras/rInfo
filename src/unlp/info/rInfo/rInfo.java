package unlp.info.rInfo;

import unlp.info.rInfo.areas.*;
import unlp.info.rInfo.gui.Area;
import unlp.info.rInfo.gui.Workspace;

public class rInfo {

	public static void main(String[] args) {
		
		Robot[] robots = new Robot[2];
		Area[] areas = new Area[3];
		
		robots[0] = new Robot1("Robot1");
		robots[1] = new Robot1("Robot2");

		areas[0] = new AreaC(0,0, 6,8);
		areas[1] = new AreaP(7,0 , 12,8);
		areas[2] = new AreaPC(0,9 , 12,13);
		

		Workspace workspace = new Workspace(robots, areas);
		workspace.setVisible(true);
		
	}

}
