package prueba;

import unlp.info.rInfo.Robot;
import java.awt.Color;

public class Robot1 extends Robot {

    public Robot1(int id) {super(id); }
    private void recorrer(){
        for (int i=0; i < 15; i++) {
            mover();
            derecha();
            mover();
            mover();
            derecha();
            mover();
            mover();
            derecha();
            derecha();
        }
    }
    public void comenzar(){
    	bloquearEsquina(99,99);
        recorrer();
        liberarEsquina(99,99);
    }
}
