package unlp.info.rInfo.areas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import unlp.info.rInfo.gui.Area;

public class AreaP extends Area {
	public AreaP() {
		super(new Color(0x0016FF));
	}

	public AreaP(Rectangle rect) {

		super(rect, new Color(0x0016FF));
	}

	public AreaP(Point from, Point to) {
		super(from, to, new Color(0x0016FF));
	}

	public AreaP(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2, new Color(0x0016FF));
	}

	public AreaP(int x, int y, Dimension dim) {
		super(x, y, dim, new Color(0x0016FF));
		
	}
}
