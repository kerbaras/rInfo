package unlp.info.rInfo.gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SideBar extends JPanel {

	private City city;
	
	public SideBar(City city){
		this.setCity(city);
		setLayout(null);
		setBackground(new Color(0xFF0000));
		
		JLabel lblMinimapa = new JLabel("Minimapa");
		
		add(lblMinimapa);
		
		JPanel minimapa = new JPanel();
		minimapa.setBounds(12, 39, 176, 92);
		add(minimapa);
		
		int i = 0;
		for (Robot robot : city.getRobots()) {
			JPanel aux = new JPanel();
			aux.setBorder(new TitledBorder(null, robot.getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
			aux.setBounds(12, 143 + (i* 120), 200 - 12, 100);
			add(aux);
			i++;
		}
		
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
