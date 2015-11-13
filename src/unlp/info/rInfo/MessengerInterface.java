package unlp.info.rInfo;

/**
 * Created by matias on 04/11/15.
 */
public interface MessengerInterface {

    /*
     * Envio asincrónico de mensajes
     */
    void enviarMensaje(Object mensaje, Robot to);
    void enviarMensaje(int mensaje, Robot to);
    void enviarMensaje(String mensaje, Robot to);
    void enviarMensaje(Robot mensaje, Robot to);

    /*
     * Recivida sincronico de mensajes
     */
    Object getMensaje(Robot from);
    Object getMensaje();
    int getIntMensaje(Robot from);
    int getIntMensaje();
    String getSrtMensaje(Robot from);
    String getSrtMensaje();
    Robot getRobotMensaje(Robot from);
    Robot getRobotMensaje();


    /*
     * Recivida de mensajes asincronica
     */
    void onMessageRecived(Object mensaje, Robot de);
}
