package unlp.info.rInfo.gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class SideBar extends JPanel {

	private Programa program;
	
	
	public SideBar(Programa program){
		this.program = program;
		setLayout(null);
		
		JLabel lblMinimapa = new JLabel("Minimapa");
		
		add(lblMinimapa);
		
		JPanel minimapa = new JPanel();
		minimapa.setBounds(12, 39, 176, 92);
		add(minimapa);
		
		int i = 0;
		for (Robot robot : program.getRobots()) {
			JPanel aux = new JPanel();
			aux.setBorder(new TitledBorder(null, robot.getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
			aux.setBounds(12, 143 + (i* 120), 200 - 12, 100);
			add(aux);
			i++;
		}
		
	}
}
