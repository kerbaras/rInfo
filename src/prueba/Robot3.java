package prueba;

import unlp.info.rInfo.Mensaje;
import unlp.info.rInfo.Robot;

public class Robot3 extends Robot{

    public Robot3(int id){
        super(id);
    }

    @Override
    public void comenzar() {
        Mensaje mensaje = esperarMensaje(3);
        System.out.println(mensaje.getMsj());
    }
}
