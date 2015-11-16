package unlp.info.rInfo.gui;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class SideBar extends JPanel {

	private Minimap minimapa;
	private int pos;
	
	public SideBar(Minimap minimapa){
		setLayout(null);
		
		JLabel lblMinimapa = new JLabel("Minimapa:");
		lblMinimapa.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 12));
		lblMinimapa.setText("Minimapa:");
		lblMinimapa.setBounds(12, 12, 200, 15);
		add(lblMinimapa);

		this.minimapa = minimapa;
		this.minimapa.setLocation(12, 35);
		add(this.minimapa);

		pos = minimapa.getHeight() + minimapa.getY();
	}

	public synchronized void registrarRobot(GRobot robot){
		RobotPane aux = new RobotPane(robot);
		aux.setBorder(new TitledBorder(null, "Robot " + robot.getId(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		add(aux);
		aux.setLocation(12, pos + 20);
		pos += 140;
		// suscribe event handler
	}
}
