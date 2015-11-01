package unlp.info.rInfo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import unlp.info.rInfo.Programa;


@SuppressWarnings("serial")
public class City extends JPanel{
	
	static int ANCHO 	= 100,
				ALTO	= 100,
				SCALE 	= 10;

	private Programa program;
	
	
	public City(Programa program){
		this.program = program;
		Dimension dimension = new Dimension(2010, 2010);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
	}
	
	public void paint(Graphics g){
		drawMap(g);
	}
	
	public void drawMap(Graphics g){
		drawAreas(g);
		drawSquares(g);
	}
	
	public void drawAreas(Graphics g){
		for (Area area : program.getAreas()) {
			area.draw(g, SCALE);
		}
	}
		
	public void drawSquares(Graphics g){
		Color squareColor = new Color(0xA0A0A0);
		g.setColor(squareColor);
		for (int i = 0; i<ANCHO; i++){
			for(int j = 0; j< ALTO; j++){
				Area area = getArea(i,j);
				if(area != null){
					if(g.getColor() != area.getColor()){
						g.setColor(area.getColor());
					}
				}else{
					if(g.getColor() != squareColor){
						g.setColor(squareColor);
					}
				}
				g.fillRect(i * 2 * SCALE + 10, j * 2 * SCALE + 10, 1 * SCALE, 1 * SCALE);
			}
		}
	}
	
	public Area getArea(Point p){
		for (Area area : program.getAreas()) {
			if(area.hasPoint(p)){
				return area;
			}
		}
		return null;
	}
	
	public Area getArea(int x, int y){
		return getArea(new Point(x,y));
	}
	
	

}
