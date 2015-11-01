package unlp.info.rInfo;

public interface Listener {
	public void handle(Robot robot, String data);
	public void handle(Robot robot, boolean data);
	public void handle(Robot robot, int data);
}
