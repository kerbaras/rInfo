package prueba;

import unlp.info.rInfo.Robot;

public class Robot4 extends Robot{

    public Robot4(int id){
        super(id);
    }

    @Override
    public void comenzar() {
        try {
            synchronized (this) {
                wait(10000);
                enviarMensaje(1, "Hola desde 2!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
