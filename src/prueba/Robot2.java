package prueba;

import unlp.info.rInfo.Robot;

public class Robot2 extends Robot {

    public Robot2(int id) {super(id); }

    private void recorrer(){
        for (int j=0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {
                mover();
                mover();
                derecha();
                mover();
                mover();

                derecha();
                derecha();
                derecha();
            }
            derecha();
        }
    }
    public void comenzar(){
        recorrer();
    }
}
