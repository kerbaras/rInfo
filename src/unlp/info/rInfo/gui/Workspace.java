package unlp.info.rInfo.gui;

import java.awt.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Workspace extends JFrame{

	private SideBar sidebar;
	private City city;
	private ArrayList<GRobot> robots;
	private Color[] defaultRobotColors = { new Color(0xFF3232), new Color(0x0EAED6), new Color(0xF78F00), new Color(0x3D14F7), new Color(0x2F60D6), new Color(0xD6BE05), new Color(0xF72F72) };
	private Minimap minimap;

	public Workspace(){
		super("Workspace");
		setSize(800, 700);
        setMinimumSize(new Dimension(350, 200));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		robots = new ArrayList<>();

		city = new City();
		
		initComponents();
	}

	public synchronized void registrarRobot(GRobot robot){
		if(robot.getColor() == null){
			if(robots.size() < defaultRobotColors.length){
				robot.setColor(defaultRobotColors[robots.size()]);
			}else{
				Random rnd = new Random();
				robot.setColor(new Color(rnd.nextInt(155) + 100, rnd.nextInt(155) + 100, rnd.nextInt(155) + 100));
			}
		}
		city.registrarRobot(robot);
		sidebar.registrarRobot(robot);
		robots.add(robot);
	}
	
	private void initComponents(){
		setContentPane(createContentPane());
	}
	
	private JPanel createContentPane(){
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);

		JScrollPane scrollPane = new JScrollPane(city);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        splitPane.setRightComponent(mainPanel);

		minimap = new Minimap(scrollPane.getViewport(), city.getMapBuffer());

		sidebar = new SideBar(minimap);
		splitPane.setLeftComponent(sidebar);
		splitPane.setDividerLocation(217);
		splitPane.setEnabled(false);

        contentPane.add(splitPane);

		return contentPane;
	}

	public void mostrarMensaje(Object mensaje, String titulo, int tipo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
	}

	public void informar(Object mensaje, String titulo){
		mostrarMensaje(mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public ArrayList<GRobot> getRobots() {
		return robots;
	}

	public City getCity() {
		return city;
	}

	public Minimap getMinimap() {
		return minimap;
	}
}
