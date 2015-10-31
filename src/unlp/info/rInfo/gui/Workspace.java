package unlp.info.rInfo.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import unlp.info.rInfo.Programa;

@SuppressWarnings("serial")
public class Workspace extends JFrame{

	private JPanel contentPane;
	private SideBar sidebar;
	private City city;
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	
	public Workspace(Programa programa){
		super("Workspace");
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		city = new City(programa.getRobots(), programa.getAreas());
		
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
		sidebar.setBounds(0, 0, 200, 100);
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		return contentPane;
	}
}
