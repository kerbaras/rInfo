package unlp.info.rInfo.areas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import unlp.info.rInfo.gui.Area;

public class AreaPC extends Area {
	public AreaPC() {
		super(new Color(0xEDC307));
	}

	public AreaPC(Rectangle rect) {

		super(rect, new Color(0xEDC307));
	}

	public AreaPC(Point from, Point to) {
		super(from, to, new Color(0xEDC307));
	}

	public AreaPC(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2, new Color(0xEDC307));
	}

	public AreaPC(int x, int y, Dimension dim) {
		super(x, y, dim, new Color(0xEDC307));
		
	}
}
