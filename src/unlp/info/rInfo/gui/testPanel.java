package unlp.info.rInfo.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class testPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public testPanel() {
		setLayout(null);
		
		JLabel lblMinimap = new JLabel("Minimap");
		lblMinimap.setBounds(12, 12, 59, 15);
		add(lblMinimap);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 39, 176, 92);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 143, 176, 102);
		add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Robot1", TitledBorder.LEFT, TitledBorder.TOP, null, null));

	}
}
