package prueba;

import unlp.info.rInfo.*;
import unlp.info.rInfo.areas.*;
import unlp.info.rInfo.gui.City;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        main2();
    }

    public static void main5(){
        Robot2  r1 = new Robot2(1),
                r2 = new Robot2(2),
                r3 = new Robot2(3),
                r4 = new Robot2(4),
                r5 = new Robot2(5);

        //Ciudad.setFlores(2, 10, 10);
        r1.iniciar(0, 12);
        //r2.iniciar(2, 12);
        r3.iniciar(4, 12);
        r4.iniciar(6, 12);
        try{
            Object object = new Object();
            synchronized (object) {
                object.wait(500);
                r2.iniciar(2, 12);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //r5.iniciar(8, 12);
    }

    public static void main4(){
        Robot r1 = new Robot(1) {
            @Override
            public void comenzar() {
                mover();
                mover();
            }
        }, r2 = new Robot(2) {
            @Override
            public void comenzar() {
                derecha();
                mover();
                mover();
            }
        };

        AreaC ciudad = new AreaC(1,1,10,10);
        r1.asignarArea(ciudad);
        r2.asignarArea(ciudad);

        r1.iniciar(4,1);
        r2.iniciar(2,2);

    }

    public static void main3(){
        Robot r1 = new Robot3(1), r2 = new Robot4(2), r3 = new Robot5(3);


        AreaC ciudad = new AreaC(1,1,10,10);
        AreaP campo = new AreaP(11,11,20,20);

        r1.asignarArea(ciudad);
        r2.asignarArea(campo);
        r3.asignarArea(campo);

        r2.iniciar(2,30);
        r3.iniciar(11,30);
        r1.iniciar(5,20);
    }

    public static void main2(){

        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(100) + 100; i++) {
            Ciudad.setFlores(rnd.nextInt(99), rnd.nextInt(99), rnd.nextInt(10));
            Ciudad.setPapeles(rnd.nextInt(99), rnd.nextInt(99), rnd.nextInt(10));
        }
        
        Ciudad.setFPS(5);

        /*Robot1 r1 = new Robot1(1);
        Robot1 r2 = new Robot1(2);
        Robot1 r3 = new Robot1(3);*/
        Robot2 r4 = new Robot2(4);
        Robot2 r5 = new Robot2(5);
        Robot2 r6 = new Robot2(6);
        Robot2 r7 = new Robot2(7);
        Robot2 r8 = new Robot2(8);
        Robot2 r9 = new Robot2(9);

        AreaC ciudad = new AreaC(1,1,10,10);
        AreaP campo = new AreaP(11,11,20,20);
        AreaPC plaza = new AreaPC(20,30, 40,40);

        /*r1.asignarArea(ciudad);
        r2.asignarArea(campo);
        r3.asignarArea(plaza);
        r3.asignarArea(ciudad);

        r1.iniciar(5,20);
        r2.iniciar(2,30);
        r3.iniciar(10, 15);*/


        r4.iniciar(0, 60);
        r5.iniciar(2, 60);
        r6.iniciar(4, 60);
        r7.iniciar(6,60);
        r8.iniciar(8,60);
        r9.iniciar(10,60);

    }
}
