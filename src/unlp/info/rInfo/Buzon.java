package unlp.info.rInfo;

import java.util.ArrayList;

public class Buzon {
    private ArrayList<Mensaje> mensajes;

    public Buzon(){
        mensajes = new ArrayList<Mensaje>();
    }

    public synchronized void newMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
        notifyAll();
    }

    public synchronized Mensaje esperar(Robot robot){
        int i=0; Mensaje mensaje;
        try {
            synchronized (this) {
                while (true) {
                    while ((mensajes.isEmpty()) || (i == mensajes.size()))
                        wait();

                    mensaje = mensajes.get(i++);
                    if(mensaje.getRobot() == robot) {
                        mensajes.remove(i - 1);
                        return mensaje;
                    }
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }

    public synchronized Mensaje esperar(){
        Mensaje mensaje;
        try {
            synchronized (this) {
                while (mensajes.isEmpty()) {
                    wait();
                }
                mensaje = mensajes.get(0);
                mensajes.remove(0);
                return mensaje;
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }
}
