package unlp.info.rInfo.events;

public class StateChangeEvent {
	private String state;

	
	public StateChangeEvent(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
