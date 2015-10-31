package unlp.info.rInfo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Area {
	protected Color color;
	protected Robot[] robots;
	protected Point from, to;

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
		Color bc = g.getColor();
		g.setColor(color.brighter().brighter().brighter().brighter());
		g.fillRect(from.x * 2 * scale + 5, from.y * 2 * scale + 5, (to.x - from.x) * 2 * scale + 20, (to.y - from.y) * 2 * scale + 20);
		g.setColor(color.darker());
		g.drawRect(from.x * 2 * scale + 5, from.y * 2 * scale + 5, (to.x - from.x) * 2 * scale + 20, (to.y - from.y) * 2 * scale + 20);
		g.setColor(bc);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Robot[] getRobots() {
		return robots;
	}

	public void setRobots(Robot[] robots) {
		this.robots = robots;
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
		if ((p.x >= from.x) && (p.x <= to.x) && (p.y >= from.y) && (p.y <= to.y)) {
			return true;
		} else {
			return false;
		}
	}
}
