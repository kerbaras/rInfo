package unlp.info.rInfo.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Minimap extends JPanel{

    private Rectangle2D rect;
    private JViewport viewport;
    private BufferedImage bg;

    public Minimap(JViewport viewport, BufferedImage bg){
        setSize(200,200);
        setDoubleBuffered(true);
        this.viewport = viewport;
        this.bg = bg;
        rect = new Rectangle2D.Double(0,0,viewport.getSize().width / 10, viewport.getSize().height  / 10);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onMouseChange(new Point(e.getX(), e.getY()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                onMouseChange(new Point(e.getX(), e.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        viewport.addChangeListener((ChangeEvent e) -> {
            Point pos = viewport.getViewPosition();
            Dimension dim = viewport.getSize();

            if (!(new Dimension((int) rect.getWidth(), (int) rect.getHeight()).equals(dim) &&
                    new Point((int) rect.getX(), (int) rect.getY()).equals(pos))) {
                rect.setRect(pos.x * 2 / 20, pos.y * 2 / 20, (int) (dim.getWidth() / 10), (int) (dim.getHeight() / 10));
                repaint();
            }
        });

    }

    public void paint(Graphics g){
        g.clearRect(0,0,200,200);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg.getScaledInstance(200,200, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        g2.setColor(new Color(0xa3a3a3));
        g2.draw(rect);

    }

    public void onMouseChange(Point p){
        int w = viewport.getSize().width / 10, h =  viewport.getSize().height / 10;
        int x = (int)(p.getX() - w / 2), y = (int)(p.getY() - h/2);

        if (x < 0) x = 0;
        else if (x > 200 - w) x = 200 - w;

        if (y < 0) y = 0;
        else if (y > 200 - h) y = 200 - h;

        viewport.setViewPosition(new Point(x * 10, y * 10));
    }
}
