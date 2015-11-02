package unlp.info.rInfo.gui;

import unlp.info.rInfo.events.ChangePosEvent;
import unlp.info.rInfo.events.ChangePosListener;
import unlp.info.rInfo.events.MoveEvent;
import unlp.info.rInfo.events.MoveListener;

import java.awt.*;
import java.util.ArrayList;

public abstract class Robot extends Thread {
	public static final int	NORTE 	= 0,
                            ESTE 	= 1,
                            SUR 	= 2,
							OESTE 	= 3,
                            FPS     = 15,
                            INTERVAL = 1000/FPS;

	protected String nombre;
	protected Color color;
	protected Point pos = new Point(0,99);
	protected int Bolsa;
	protected int sentido = NORTE;
    protected ArrayList<MoveListener> moveListeners = new ArrayList<MoveListener>();
    protected ArrayList<ChangePosListener> changePosListeners = new ArrayList<ChangePosListener>();
	
	public Robot(){
		setColor(new Color(0xFF3232));
	}
	
	public Robot(String nombre){
        this();
		this.nombre = nombre;
	}

    protected void mover(){
        Point posAnt = (Point)pos.clone();
        switch (sentido){
            case NORTE:
                pos.setLocation(pos.getX(), pos.getY() - 1);
                break;
            case SUR:
                pos.setLocation(pos.getX(), pos.getY() + 1);
                break;

            case OESTE:
                pos.setLocation(pos.getX() - 1 , pos.getY());
                break;
            case ESTE:
                pos.setLocation(pos.getX() + 1, pos.getY());
                break;
        }
        dispatchMoveListeners(this, pos, posAnt, sentido);
        try{
            sleep(INTERVAL);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    protected void derecha(){
        if(sentido == 3){
            sentido = NORTE;
        }else{
            sentido += 1;
        }
    }

    protected void izquierda(){
        if(sentido == NORTE){
            sentido = OESTE;
        }else{
            sentido -= 1;
        }
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getBolsa() {
		return Bolsa;
	}

	public void setBolsa(int bolsa) {
		Bolsa = bolsa;
	}

    public int getSentido() {
        return sentido;
    }

    public void addMoveListener(MoveListener moveListener){
        moveListeners.add(moveListener);
        System.out.println("hola");
    }

    public ArrayList<MoveListener> getMoveListeners(){
        return moveListeners;
    }

    public void dispatchMoveListeners(Robot robot, Point posAct, Point posAnt, int sentido){
        MoveEvent event = new MoveEvent(posAct, posAnt, sentido);
        if (moveListeners != null) {
            for (MoveListener listener : moveListeners) {
                listener.onMove(robot, event);
            }
        }
        dispatchChangePosListeners(robot, posAct, posAnt);
    }

    public void addChangePosListener(ChangePosListener changePosListener){
        changePosListeners.add(changePosListener);
    }

    public ArrayList<ChangePosListener> getChangePosListeners(){
        return changePosListeners;
    }

    public void dispatchChangePosListeners(Robot robot, Point posAct, Point posAnt){
        ChangePosEvent event = new ChangePosEvent(posAct, posAnt);
        if(changePosListeners != null) {
            for (ChangePosListener listener : changePosListeners) {
                listener.onChangePos(robot, event);
            }
        }
    }

    public void draw(Graphics g){
        Color bc = g.getColor();
        int x = ((pos.x * 2 * City.SCALE)- 1), y = ((pos.y * 2 * City.SCALE ) - 1);
        g.setColor(color);
        g.fillOval(x, y, 10, 10);
        g.setColor(Color.black);

        switch (sentido){
            case Robot.NORTE:
                g.fillOval(x + 2, y + 2, 3, 3);
                g.fillOval(x + 5, y + 2, 3, 3);
                break;
            case Robot.SUR:
                g.fillOval(x + 2, y + 5, 3, 3);
                g.fillOval(x + 5, y + 5, 3, 3);
                break;
            case Robot.ESTE:
                g.fillOval(x + 5, y + 2, 3, 3);
                g.fillOval(x + 5, y + 5, 3, 3);
                break;
            case Robot.OESTE:
                g.fillOval(x + 2, y + 2, 3, 3);
                g.fillOval(x + 2, y + 5, 3, 3);
                break;
        }
        g.setColor(bc);
    }
}
