package unlp.info.rInfo;

import unlp.info.rInfo.gui.Workspace;

public class rInfo {

	public static void main(String[] args) {
		
		Robot[] robots = new Robot[2];
		robots[0] = new Robot1("Robot1");
		robots[1] = new Robot1("Robot2");
		

		Workspace workspace = new Workspace(robots);
		workspace.setVisible(true);
		
	}

}
