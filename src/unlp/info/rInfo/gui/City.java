package unlp.info.rInfo.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import unlp.info.util.Lista;


@SuppressWarnings("serial")
public class City extends JScrollPane{
	
	static int ANCHO 	= 100,
				ALTO	= 100;
	
	private Robot[] robots;
	private JPanel map;
	
	
	public City(){
		map = new JPanel();
		Dimension dimension = new Dimension(100000, 100000);
		add(map);
		setViewportView(map);
		drawMap();
	}
	
	public void drawMap(){
		Graphics g = map.getGraphics();
		drawSquares(g);
	
		g.dispose();
	}
	
	public void drawSquares(Graphics g){
		g.setColor(new Color(0xA0A0A0));
		for (int i = 0; i<100; i++){
			for(int j = 0; j<100; j++){
				g.fillRect(i * 21 + 10, j * 21 + 10, 10, 10);
			}
		}
	}
	
	public void setRobots(Robot[] robots){
		this.robots = robots;
	}
	
	public Robot[] getRobots(){
		return robots;
	}

}
