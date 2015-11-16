package unlp.info.rInfo.events;

/**
 * Created by matias on 11/15/15.
 */
public class ChangeDirectionEvent {
    private int sentido;

    public ChangeDirectionEvent(int sentido) {
        this.sentido = sentido;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }
}
