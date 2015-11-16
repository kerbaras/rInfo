package unlp.info.rInfo.events;

import unlp.info.rInfo.gui.GRobot;

public interface ChangeDirectionListener {
    void onChangeDirection(GRobot robot, ChangeDirectionEvent event);
}
