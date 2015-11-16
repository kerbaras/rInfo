package unlp.info.rInfo.gui;

import unlp.info.rInfo.*;
import unlp.info.rInfo.events.*;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GRobot implements Runnable {
	public static final int	NORTE 	= 0,
                            ESTE 	= 1,
                            SUR 	= 2,
							OESTE 	= 3,
                            FPS     = 10,
                            INTERVAL = 1000/FPS;

	protected int id;
	protected Color color;
	protected Point pos = new Point(0,99);
	protected int sentido = NORTE;
    protected ArrayList<ChangeDirectionListener> changeDirectionListeners = new ArrayList<ChangeDirectionListener>();
    protected ArrayList<ChangePosListener> changePosListeners = new ArrayList<ChangePosListener>();
    protected ArrayList<MoveListener> moveListeners = new ArrayList<MoveListener>();
    protected ArrayList<ChangeStateListener> changeStateListener = new ArrayList<ChangeStateListener>();
    private Thread thread;
    private boolean running = false;
    private Robot robot;

	
	public GRobot(Robot robot){
        this.robot = robot;
	}
	
	public GRobot(int id, Robot robot){
        this(robot);
		this.id = id;
	}

    public void run(){
        this.running = true;
        robot.comenzar();
    }

    public void mover(){
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
            thread.sleep(INTERVAL);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void derecha(){
        if(sentido == 3){
            sentido = NORTE;
        }else{
            sentido += 1;
        }

        dispatchChangeDirectionListeners(this, sentido);
    }

    public void boot(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
        dispatchChangeStateListeners(this, "Ejecutandose");
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

    public int getSentido() {
        return sentido;
    }

    public void addMoveListener(MoveListener listener){
        moveListeners.add(listener);
    }

    public void addChangePosListener(ChangePosListener listener){
        changePosListeners.add(listener);
    }

    public void addChangeDirectionListener(ChangeDirectionListener listener) { 
    	changeDirectionListeners.add(listener); 
    }

    public void addChangeStateListener(ChangeStateListener listener) { 
    	changeStateListener.add(listener); 
    }

    public ArrayList<ChangePosListener> getChangePosListeners(){
        return changePosListeners;
    }

    public void dispatchChangePosListeners(GRobot robot, Point posAct, Point posAnt){
        ChangePosEvent event = new ChangePosEvent(posAct, posAnt);
        if(changePosListeners != null) {
            for (ChangePosListener listener : changePosListeners) {
                listener.onChangePos(robot, event);
            }
        }
    }

    public void dispatchMoveListeners(GRobot robot, Point posAct, Point posAnt, int sentido){
        MoveEvent event = new MoveEvent(posAct, posAnt, sentido);
        if (moveListeners != null) {
            for (MoveListener listener : moveListeners) {
                listener.onMove(robot, event);
            }
        }
        dispatchChangePosListeners(robot, posAct, posAnt);
    }

    public void dispatchChangeDirectionListeners(GRobot robot, int sentido){
        ChangeDirectionEvent event = new ChangeDirectionEvent(sentido);
        if (changeDirectionListeners != null) {
            for (ChangeDirectionListener listener : changeDirectionListeners) {
                listener.onChangeDirection(robot, event);
            }
        }
    }

    public void dispatchChangeStateListeners(GRobot robot, String state){
    	StateChangeEvent event = new StateChangeEvent(state);
        if (changeStateListener != null) {
            for (ChangeStateListener listener : changeStateListener) {
                listener.onStateChange(robot, event);
            }
        }
    }

    public void draw(Graphics g) {
        Color bc = g.getColor();
        int x = ((pos.x * 2 * City.SCALE) - 1), y = ((pos.y * 2 * City.SCALE) - 1);
        g.setColor(color);
        g.fillOval(x, y, 10, 10);
        g.setColor(Color.black);
        g.drawOval(x, y, 10, 10);

        switch (sentido) {
            case GRobot.NORTE:
                g.fillOval(x + 2, y + 1, 3, 3);
                g.fillOval(x + 6, y + 1, 3, 3);
                //g.drawLine(x + 5, y, x + 5, y + 5);
                //g.drawLine(x, y + 5, x + 10, y + 5);
                break;
            case GRobot.SUR:
                g.fillOval(x + 2, y + 6, 3, 3);
                g.fillOval(x + 6, y + 6, 3, 3);
                //g.drawLine(x + 5, y + 5, x + 5, y + 10);
                //g.drawLine(x, y + 5, x + 10, y + 5);
                break;
            case GRobot.ESTE:
                g.fillOval(x + 6, y + 1, 3, 3);
                g.fillOval(x + 6, y + 6, 3, 3);
                //g.drawLine(x + 5, y + 5, x + 10, y + 5);
                //g.drawLine(x + 5, y, x + 5, y + 10);
                break;
            case GRobot.OESTE:
                g.fillOval(x + 1, y + 1, 3, 3);
                g.fillOval(x + 1, y + 6, 3, 3);
                //g.drawLine(x, y + 5, x + 5, y + 5);
                //g.drawLine(x + 5, y, x + 5, y + 10);
                break;
        }
        g.setColor(bc);
    }
}
