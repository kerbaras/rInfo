package unlp.info.rInfo.events;

import unlp.info.rInfo.gui.GRobot;

public interface ChangeStateListener {
	void onStateChange(GRobot r, StateChangeEvent event);
}
