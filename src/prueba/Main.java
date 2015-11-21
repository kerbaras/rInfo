package prueba;

import unlp.info.rInfo.*;
import unlp.info.rInfo.areas.*;

public class Main {
    public static void main(String[] args){

        Ciudad.setFlores(1, 53, 20);
        Ciudad.setFlores(12, 30, 20);
        Robot1 r1 = new Robot1(1);
        Robot1 r2 = new Robot1(2);
        Robot1 r3 = new Robot1(3);

        AreaC ciudad = new AreaC(1,1,10,10);
        AreaP campo = new AreaP(11,11,20,20);
        AreaPC plaza = new AreaPC(20,30, 40,40);

        r1.asignarArea(ciudad);
        r2.asignarArea(campo);
        r3.asignarArea(plaza);
        r3.asignarArea(ciudad);

        r1.iniciar(5,20);
        r2.iniciar(2,30);
        r3.iniciar(10,15);
    }
}
