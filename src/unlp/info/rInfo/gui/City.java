package unlp.info.rInfo.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import unlp.info.rInfo.Programa;
import unlp.info.rInfo.events.ChangeDirectionEvent;
import unlp.info.rInfo.events.ChangePosEvent;
import unlp.info.rInfo.events.MoveEvent;

@SuppressWarnings("serial")
public class City extends JPanel {

    static int ANCHO = 100,
            ALTO = 100,
            SCALE = 10,
            WIDTH = 1991,
            HEIGHT = 1991;

    private BufferedImage buffer, mapBuffer, blockBuffer, resourceBuffer, pathBuffer, robotBuffer;
    private Image flor = new ImageIcon(getClass().getResource("./resources/flor.png")).getImage();
    private Image papel = new ImageIcon(getClass().getResource("./resources/papel.png")).getImage();
    private Image obstaculo = new ImageIcon(getClass().getResource("./resources/obstaculo.png")).getImage();
    private boolean needPaint = true;

    public City() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setDoubleBuffered(true);
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        mapBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        blockBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        resourceBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        pathBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        robotBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawMap(mapBuffer.getGraphics());
        drawThread thread = new drawThread(this);
        thread.start();
    }

    public void registrarRobot(GRobot robot){
        drawRobot(robot);
        robot.addChangePosListener( (GRobot r, ChangePosEvent event)->drawRobot(robot, event.getPosAnt()) );
        robot.addMoveListener((GRobot r, MoveEvent event) -> drawPath(event.getPosAnt(), event.getPosAct(), r.getColor()));
        robot.addChangeDirectionListener((GRobot r, ChangeDirectionEvent event)->drawRobot(robot));
    }

    public void paint(Graphics g) {
        if(g != null) {
            draw();
            g.setColor(new Color(255,255,255));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(buffer, 0, 0, this);
            Programa.getWorkspace().getMinimap().repaint();
        }
    }

    public void drawMap(Graphics g) {
        drawAreas(g);
        drawSquares(g);
    }

    public void drawAreas(Graphics g) {
        for (Area area : Programa.getAreas()) {
            area.draw(g, SCALE);
        }
    }

    public void drawSquares(Graphics g) {
        Color squareColor = new Color(0xA0A0A0);
        g.setColor(squareColor);
        for (int i = 0; i < ANCHO; i++) {
            for (int j = 0; j < ALTO; j++) {
                Area area = getArea(i, j);
                if (area != null) {
                    if (g.getColor() != area.getColor()) {
                        g.setColor(area.getColor());
                    }
                } else {
                    if (g.getColor() != squareColor) {
                        g.setColor(squareColor);
                    }
                }

                if(!(j == 99 || i == 99))
                    g.fillRect(i * 2 * SCALE + 10, j * 2 * SCALE + 10, SCALE, SCALE);
                drawResource(new Point(i, j));
            }
        }
    }

    public synchronized void drawBlockedSquare(Point e){
        Graphics2D g2 = (Graphics2D)blockBuffer.getGraphics();
        g2.setColor(new Color(255,0,0,100));
        g2.fillRect(e.x * 2 * SCALE, e.y * 2 * SCALE, 10, 10);
        needPaint = true;
    }

    public synchronized void drawFreeSquare(Point e){
        Graphics2D g2 = (Graphics2D)blockBuffer.getGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(e.x * 2 * SCALE, e.y * 2 * SCALE, 10, 10);
        g2.setComposite(AlphaComposite.SrcOver);
        needPaint = true;
    }

    public void drawRobot(GRobot robot) {
        drawRobot(robot, robot.getPos());
    }

    public synchronized void drawRobot(GRobot robot, Point posAnt) {
        Graphics2D g2 = (Graphics2D) robotBuffer.getGraphics();
        int x = posAnt.x * 2 * SCALE, y = posAnt.y * 2 * SCALE;
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(x, y, 11, 11);
        g2.setComposite(AlphaComposite.SrcOver);

        robot.draw(g2);
        needPaint = true;
    }

    public synchronized void drawPath(Point p1, Point p2, Color color){
        Graphics g = pathBuffer.getGraphics();
        g.setColor(color);
        g.drawLine((p1.x * 2 * SCALE) + 5, p1.y * 2 * SCALE + 5, p2.x * 2 * SCALE + 5, p2.y * 2 * SCALE + 5);
        g.drawLine((p1.x * 2 * SCALE) + 4, p1.y * 2 * SCALE + 4, p2.x * 2 * SCALE + 4, p2.y * 2 * SCALE + 4);
        g.drawLine((p1.x * 2 * SCALE) + 6, p1.y * 2 * SCALE + 6, p2.x * 2 * SCALE + 6, p2.y * 2 * SCALE + 6);
        needPaint = true;
    }

    public synchronized void draw(){
        if(needPaint) {

            Graphics2D g2 = (Graphics2D) buffer.getGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fillRect(0, 0, WIDTH, HEIGHT);
            g2.setComposite(AlphaComposite.SrcOver);

            g2.drawImage(mapBuffer, 0, 0, this);
            g2.drawImage(blockBuffer, 0, 0, this);
            g2.drawImage(resourceBuffer, 0, 0, this);
            g2.drawImage(pathBuffer, 0, 0, this);
            g2.drawImage(robotBuffer, 0, 0, this);
            needPaint = false;
        }
    }

    public synchronized void drawResource(Point p){
        Image img = null;
        if (Programa.hayObstaculo(p))
            img = obstaculo;
        else if(Programa.getFlores(p) > 0)
            img = flor;
        else if (Programa.getPapeles(p) > 0)
            img = papel;

        Graphics2D g2 = (Graphics2D)resourceBuffer.getGraphics();

        int x = p.x * 2 * SCALE, y = p.y * 2 * SCALE;
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(x, y, 10, 10);
        g2.setComposite(AlphaComposite.SrcOver);

        if(img != null){
            g2.drawImage(img, x, y, this);
        }
        needPaint = true;
    }

    public Area getArea(Point p) {
        for (Area area : Programa.getAreas()) {
            if (area.hasPoint(p)) {
                return area;
            }
        }
        return null;
    }

    public Area getArea(int x, int y) {
        return getArea(new Point(x, y));
    }

    public BufferedImage getMapBuffer() {
        return buffer;
    }

    class drawThread extends Thread{
        City city;

        public drawThread (City city){
            this.city = city;
        }

        public void run() {
            while (Programa.isRunning()){
                try {
                    city.repaint();
                    sleep(1000 / 60);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
