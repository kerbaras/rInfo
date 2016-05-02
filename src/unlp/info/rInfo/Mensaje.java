package unlp.info.rInfo;

public class Mensaje {
    private Robot robot;
    private Object msj;

    public Mensaje(Robot robot, Object msj){
        this.robot = robot;
        this.msj = msj;
    }

    public Object getMsj() {
        return msj;
    }

    public Robot getRobot() {
        return robot;
    }
}
