package unlp.info.rInfo.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


@SuppressWarnings("serial")
public class City extends Canvas{
	
	static int ANCHO 	= 100,
				ALTO	= 100,
				SCALE 	= 10;

	private Robot[] robots;
	private Area[] areas;
	
	
	public City(Robot[] robots, Area[] areas){
		this.robots = robots;
		this.areas = areas;
		Dimension dimension = new Dimension(ANCHO * SCALE, ALTO * SCALE);
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
		for (Area area : areas) {
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
	
	public void setRobots(Robot[] robots){
		this.robots = robots;
	}
	
	public Robot[] getRobots(){
		return robots;
	}
	
	public Area getArea(Point p){
		for (Area area : areas) {
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
