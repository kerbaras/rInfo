package unlp.info.rInfo.ide;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

class LinePainter implements Highlighter.HighlightPainter, MouseListener, MouseMotionListener {

	private JTextComponent component;
    private Color color;
    private Rectangle lastView;
    
	public LinePainter(JTextComponent component, Color color) {
		this.component = component;
		this.color = color;
		
		component.addCaretListener((CaretEvent e) -> resetHighlight());
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        
        try {
        	component.getHighlighter().addHighlight(0, 0, this);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void resetHighlight() {
		SwingUtilities.invokeLater(() -> {
			try {
                int offset = component.getCaretPosition();
                Rectangle currentView = component.modelToView(offset);

                //  Remove the highlighting from the previously highlighted line

                if (lastView != null && lastView.y != currentView.y) {
                    component.repaint(0, lastView.y, component.getWidth(), lastView.height);
                    lastView = currentView;
                }
            } catch (BadLocationException e) {
            	e.printStackTrace();
            }
		});
	}


	@Override
	public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
		try {
            Rectangle r = c.modelToView(c.getCaretPosition());
            g.setColor(color);
            g.fillRect(0, r.y, c.getWidth(), r.height);

            if (this.lastView == null) {
                this.lastView = r;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		resetHighlight();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		resetHighlight();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
