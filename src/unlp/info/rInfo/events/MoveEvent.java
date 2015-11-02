package unlp.info.rInfo.events;

import java.awt.*;

/**
 * Created by matias on 11/2/15.
 */
public class MoveEvent extends ChangePosEvent{
    protected int sentido;

    public MoveEvent(Point posAct, Point posAnt, int sentido) {
        super(posAct, posAnt);
        this.sentido = sentido;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }
}
