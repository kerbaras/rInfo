package unlp.info.rInfo.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class City extends JPanel {

    static int ANCHO = 100,
            ALTO = 100,
            SCALE = 10;

    private BufferedImage mapBuffer;
    private Workspace workspace;

    public City(Workspace workspace) {
        this.workspace = workspace;
        Dimension dimension = new Dimension(2011, 2011);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setDoubleBuffered(true);
        mapBuffer = new BufferedImage(2011, 2011, BufferedImage.TYPE_INT_ARGB);
        drawMap(mapBuffer.getGraphics());
        repaint();
    }

    public void paint(Graphics g) {
        if(g != null) {
            g.clearRect(0,0,2011,2011);
            g.drawImage(mapBuffer, 0, 0, this);
            drawRobots(g);
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
                g.fillRect(i * 2 * SCALE + 10, j * 2 * SCALE + 10, SCALE, SCALE);
            }
        }
    }

    public void drawRobots(Graphics g) {
        for (GRobot robot : workspace.getRobots()){
            robot.draw(g);
        }
    }

    public void drawPath(Point p1, Point p2, Color color){
        Graphics g = mapBuffer.getGraphics();
        g.setColor(color);
        g.drawLine((p1.x * 2 * SCALE) + 5, p1.y * 2 * SCALE + 5, p2.x * 2 * SCALE + 5, p2.y * 2 * SCALE + 5);
        g.drawLine((p1.x * 2 * SCALE) + 4, p1.y * 2 * SCALE + 4, p2.x * 2 * SCALE + 4, p2.y * 2 * SCALE + 4);
        g.drawLine((p1.x * 2 * SCALE) + 6, p1.y * 2 * SCALE + 6, p2.x * 2 * SCALE + 6, p2.y * 2 * SCALE + 6);
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
        return mapBuffer;
    }
}
