package unlp.info.rInfo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by matias on 11/2/15.
 */
public class Minimap extends JPanel{

    private Rectangle2D rect;
    private JViewport viewport;
    private BufferedImage bg;

    public Minimap(JViewport viewport, BufferedImage bg){
        setSize(200,200);
        setDoubleBuffered(true);
        this.viewport = viewport;
        this.bg = bg;
        rect = new Rectangle2D.Double(0,0,(int)(viewport.getSize().width / 10), (int)(viewport.getSize().height  / 10));


        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onMouseChange(new Point(e.getX(), e.getY()));
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        viewport.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                rect.setRect(rect.getX(), rect.getY(), e.getComponent().getWidth() / 10, e.getComponent().getHeight() / 10);
                repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

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

    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg.getScaledInstance(200,200, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        g2.setColor(new Color(0xa3a3a3));
        g2.draw(rect);

    }

    public BufferedImage getBufferedImage() {
        return bg;
    }

    public void setBufferedImage(BufferedImage bg) {
        this.bg = bg;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }

    public JViewport getViewport() {
        return viewport;
    }

    public void setViewport(JViewport viewport) {
        this.viewport = viewport;
    }

    public void onMouseChange(Point p){
        int w = viewport.getSize().width / 10, h =  viewport.getSize().height / 10;
        int x = (int)(p.getX() - w / 2), y = (int)(p.getY() - h/2);

        if (x < 0){ x = 0; }

        if (x > 200 - w){ x = 200 - w; }

        if (y < 0){ y = 0; }

        if (y > 200 - h) { y = 200 - h; }

        if (viewport != null) {
            viewport.setViewPosition(new Point((int) (x * 21 / 2 ), (int) (y * 21 / 2)));
        }
        rect.setRect(x, y, w, h);
        repaint();
    }
}
