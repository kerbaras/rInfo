package prueba;

import unlp.info.rInfo.Ciudad;

public class Main {
    public static void main(String[] args){

        Ciudad.setFlores(1, 53, 20);
        Ciudad.setFlores(12, 30, 20);
        Robot1 r1 = new Robot1(1);
        Robot1 r2 = new Robot1(2);
        Robot2 r3 = new Robot2(3);
        Robot2 r4 = new Robot2(4);
        Robot2 r5 = new Robot2(5);
        Robot2 r6 = new Robot2(6);
        Robot2 r7 = new Robot2(7);
        Robot2 r8 = new Robot2(8);
        r1.iniciar(5,20);
        r2.iniciar(2,30);
        r3.iniciar(1,53);
        r4.iniciar(3,53);
        r5.iniciar(5,53);
        r6.iniciar(7,53);
        r7.iniciar(9,53);
        r8.iniciar(11,53);
    }
}
