package unlp.info.rInfo.gui;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class SideBar extends JPanel {

	private Programa program;
	private Minimap minimapa;
	
	public SideBar(Programa program, Minimap minimapa){
		this.program = program;
		setLayout(null);
		
		JLabel lblMinimapa = new JLabel("Minimapa:");
		lblMinimapa.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 12));
		lblMinimapa.setText("Minimapa:");
		lblMinimapa.setBounds(12, 12, 200, 15);
		add(lblMinimapa);

		this.minimapa = minimapa;
		this.minimapa.setLocation(12, 39);
		add(this.minimapa);
		
		int i = 0;
		for (Robot robot : program.getRobots()) {
			JPanel aux = new JPanel();
			aux.setBorder(new TitledBorder(null, robot.getNombre(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
			aux.setBounds(12, minimapa.getY() + minimapa.getHeight() + 20  + (i* 120), 200 - 12, 100);
			add(aux);
			i++;
		}
		
	}
}
