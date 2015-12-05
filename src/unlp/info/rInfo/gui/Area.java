package unlp.info.rInfo.gui;

import unlp.info.rInfo.Programa;
import unlp.info.rInfo.Robot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Area {
	private Color color;
	private ArrayList<Robot> robots = new ArrayList<>();
	private Point from, to;
	private boolean registrada;

	public Area(Color color) {
		this.color = color;
	}

	public Area(Rectangle rect, Color color) {
		this(new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.height), color);
	}

	public Area(Point from, Point to, Color color) {
		this(color);
		this.from = from;
		this.to = to;
	}

	public Area(int x1, int y1, int x2, int y2, Color color) {
		this(new Point(x1, y1), new Point(x2, y2), color);
	}

	public Area(int x, int y, Dimension dim, Color color) {
		this(new Rectangle(new Point(x, y), dim), color);
	}

	public void draw(Graphics g, int scale) {
		int x, y, w, h;
		x = (from.x * 2 * scale) + 5;
		y = from.y * 2 * scale + 5;
		w = ((to.x +1) * 2 * scale) - x + 5;
		h = ((to.y +1) * 2 * scale) - y + 5;
		Color bc = g.getColor();
		g.setColor(color.brighter().brighter().brighter());
		g.fillRect(x, y, w, h);
		g.setColor(color.darker());
		g.drawRect(x, y, w, h);
		g.drawRect(x + 1, y + 1, w, h);
		g.setColor(bc);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Robot> getRobots() {
		return robots;
	}

	public void addRobot(Robot robot) throws Exception{
		if(robots.contains(robot)){
			throw new Exception("El robot ya esta asignado a esta area");
		}
		if(!registrada){
			registrada = true;
			Programa.addArea(this);
		}
		this.robots.add(robot);
	}

	public void removeRobot(Robot robot) {
		this.robots.remove(robot);
	}

	public Point getFrom() {
		return from;
	}

	public void setFrom(Point from) {
		this.from = from;
	}

	public Point getTo() {
		return to;
	}

	public void setTo(Point to) {
		this.to = to;
	}

	public boolean hasPoint(Point p) {
		return ((p.x >= from.x) && (p.x <= to.x) && (p.y >= from.y) && (p.y <= to.y));
	}
}
