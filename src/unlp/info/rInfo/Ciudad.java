package unlp.info.rInfo;

import java.awt.*;

public final class Ciudad {

    public static synchronized void setFlores(int x, int y, int cant){
        Programa.setFlores(new Point(x, y), cant);
    }

    public static synchronized void setPapeles(int x, int y, int cant){
        Programa.setPapeles(new Point(x, y), cant);
    }

    public static synchronized void ponerObstaculo(int x, int y){
        Programa.setObstaculo(new Point(x, y), true);
    }

    public static synchronized void sacarObstaculo(int x, int y){
        Programa.setObstaculo(new Point(x, y), false);
    }
}
