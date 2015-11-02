package unlp.info.rInfo;

public class Robot1 extends Robot {

	public Robot1(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		for (int x = 0; x < 10; x++){
			derecha();
			for (int i = 0; i < x; i++) {
				mover();
			}
			derecha();
			mover();
			mover();
			derecha();
			mover();
			derecha();
			mover();
			mover();
		}

	}

}
