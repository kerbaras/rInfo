package unlp.info.rInfo.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class Workspace extends JFrame{

	private Programa program;
	
	private JPanel contentPane;
	private SideBar sidebar;
	private City city;
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	private Minimap minimapa;
	
	public Workspace(Programa program){
		super("Workspace");
		setSize(800, 700);
        setMinimumSize(new Dimension(350, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.program = program;

		city = new City(program);
		
		initComponents();
	}
	
	private void initComponents(){
		setJMenuBar(createMenuVar());
		setContentPane(createContentPane());
	}
	
	private JMenuBar createMenuVar(){
		JFrame me = this;
		menuBar = new JMenuBar();
		JMenu mnFile = new JMenu("File");
		mnFile.addSeparator();
		JMenuItem btnClose = new JMenuItem("Close");
		btnClose.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { me.dispose(); }
		});
		mnFile.add(btnClose);
		menuBar.add(mnFile);
		
		return menuBar;
	}
	
	private JPanel createContentPane(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();



		scrollPane = new JScrollPane(city);
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        splitPane.setRightComponent(mainPanel);

		minimapa = new Minimap(scrollPane.getViewport(), city.getMapBuffer());

		sidebar = new SideBar(program, minimapa);
		splitPane.setLeftComponent(sidebar);
		splitPane.setDividerLocation(217);
		splitPane.setEnabled(false);

        contentPane.add(splitPane);

		return contentPane;
	}
}
