package prueba;

import unlp.info.rInfo.Robot;

public class Robot2 extends Robot {

    public Robot2(int id) { super(id); }

    private void tomarFlores(){
        while (hayFlorEnLaEsquina()){
            tomarFlor();
        }
    }

    private void recorrer(){
        for (int j=0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {
                int x = getAv(), y = getCa();
                int x1 = x, y1 = y, x2 = x, y2 = y;

                switch (j){
                    case 0:
                        y1 -= 2;
                        x2 += 2;
                        y2 -= 2;
                        break;
                    case 1:
                        x1 += 2;
                        x2 += 2;
                        y2 += 2;
                        break;
                    case 2:
                        y1 += 2;
                        x2 -= 2;
                        y2 += 2;
                        break;
                    case 3:
                        x1 -= 2;
                        x2 -= 2;
                        y2 -= 2;
                        break;
                }
                tomarFlores();
                mover();
                liberarEsquina(x, y);
                tomarFlores();

                bloquearEsquina(x1, y1);
                mover();
                tomarFlores();
                derecha();

                mover();
                liberarEsquina(x1, y1);
                tomarFlores();

                bloquearEsquina(x2, y2);
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
