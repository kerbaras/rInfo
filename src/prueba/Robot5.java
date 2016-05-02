package prueba;

import unlp.info.rInfo.Robot;

public class Robot5 extends Robot{

    public Robot5(int id){
        super(id);
    }

    @Override
    public void comenzar() {
        try {
            synchronized (this) {
                wait(7000);
                enviarMensaje(1, "Hola desde 3!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
