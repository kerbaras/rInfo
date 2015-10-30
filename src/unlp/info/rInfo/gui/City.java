package unlp.info.rInfo.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import unlp.info.util.Lista;


@SuppressWarnings("serial")
public class City extends JPanel{
	
	static int ANCHO 	= 100,
				ALTO	= 100;
	
	private int[][] esquinas;
	
	private Robot[] robots;
	
	
	public City(){
		setBackground(new Color(0xEEE000));
		this.setSize(600, 600);
	}
	
	public void paintComponents(Graphics g){
		
	}
	
	public void setRobots(Robot[] robots){
		this.robots = robots;
	}
	
	public Robot[] getRobots(){
		return robots;
	}

}
