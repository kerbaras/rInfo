package unlp.info.rInfo.gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Workspace extends JFrame{

	private JPanel contentPane;
	private SideBar sidebar;
	private City city;
	private JMenuBar menuBar;
	
	public Workspace(Robot[] robots){
		super("Workspace");
		this.setSize(800, 700);
		
		city = new City();
		city.setRobots(robots);
		
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
			public void actionPerformed(ActionEvent e) {
				me.dispose();
			}
		});
		mnFile.add(btnClose);
		menuBar.add(mnFile);
		
		return menuBar;
	}
	
	private JPanel createContentPane(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		sidebar = new SideBar(city);
		contentPane.add(sidebar, BorderLayout.WEST);
		sidebar.getSize().width = 200;
		
		contentPane.add(city, BorderLayout.CENTER);
		
		return contentPane;
	}
}
