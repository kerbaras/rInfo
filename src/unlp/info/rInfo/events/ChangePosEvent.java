package unlp.info.rInfo.events;

import java.awt.*;

/**
 * Created by matias on 11/2/15.
 */
public class ChangePosEvent {
    protected Point posAnt, posAct;

    public ChangePosEvent(Point posAct, Point posAnt) {
        this.posAct = posAct;
        this.posAnt = posAnt;
    }

    public Point getPosAnt() {
        return posAnt;
    }

    public void setPosAnt(Point posAnt) {
        this.posAnt = posAnt;
    }

    public Point getPosAct() {
        return posAct;
    }

    public void setPosAct(Point posAct) {
        this.posAct = posAct;
    }
}
