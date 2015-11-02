package unlp.info.rInfo;

import java.awt.*;

public class Robot2 extends Robot {

    public Robot2(String nombre) {
        super(nombre);
    }

    @Override
    public void run(){
        setColor(Color.blue);
        derecha();
        mover();
        derecha();
        mover();
    }

}
