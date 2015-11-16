package unlp.info.rInfo.events;

import unlp.info.rInfo.gui.GRobot;

/**
 * Created by matias on 11/2/15.
 */
public interface MoveListener {
    void onMove(GRobot robot, MoveEvent event);
}
