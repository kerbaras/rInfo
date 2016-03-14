package unlp.info.rInfo.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;
import java.io.File;

public class IDEApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane editorPane;
	private JTextArea textArea;
	private int linesCount;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	    	
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDEApplication frame = new IDEApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IDEApplication() {
		setTitle("rInfo - IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 463);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnfile = new JMenu("File");
		menuBar.add(mnfile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnfile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnfile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnfile.add(separator);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnfile.add(mntmClose);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNew = new JButton(new ImageIcon(getClass().getResource("../gui/resources/new.png")));
		btnNew.setToolTipText("New");
		toolBar.add(btnNew);
		
		JButton btnOpen = new JButton(new ImageIcon(getClass().getResource("../gui/resources/open.png")));
		btnOpen.setToolTipText("Open");
		toolBar.add(btnOpen);
		
		JButton btnSave = new JButton(new ImageIcon(getClass().getResource("../gui/resources/save.png")));
		btnSave.setToolTipText("Save");
		toolBar.add(btnSave);
		
		JButton btnSaveAll = new JButton(new ImageIcon(getClass().getResource("../gui/resources/save_all.png")));
		btnSaveAll.setToolTipText("Save All");
		toolBar.add(btnSaveAll);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar.add(toolBar_1);
		
		JButton btnRun = new JButton(new ImageIcon(getClass().getResource("../gui/resources/run.png")));
		btnRun.setToolTipText("Run");
		toolBar_1.add(btnRun);
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prueba.Main.main(null);
				
			}
		});

		
		JButton btnDebug = new JButton(new ImageIcon(getClass().getResource("../gui/resources/debug.png")));
		btnDebug.setToolTipText("Debug");
		toolBar_1.add(btnDebug);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setBackground(SystemColor.control);
		textArea.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 1, Color.LIGHT_GRAY));
		textArea.setText("  1.");
		scrollPane.setRowHeaderView(textArea);
		
		editorPane = new JEditorPane();

		editorPane.setFont(new Font("Monospaced", Font.PLAIN, 13));
		editorPane.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				setLineNumbers();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setLineNumbers();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		scrollPane.setViewportView(editorPane);
		
		JTree tree = new JTree();
		splitPane.setLeftComponent(tree);
	}

	protected void setLineNumbers() {
		Element root = editorPane.getDocument().getDefaultRootElement();
        int i, untilLine = root.getElementCount();
        int chars = (untilLine > 99) ? ("" + untilLine).length() : 3;
        String t = "         1".substring(10 - chars) + ".";
        String tmp;

        if (untilLine != this.linesCount) {
            this.linesCount = untilLine;
            for (i = 2; i <= untilLine; i++) {
                tmp = "          " + i;
                t += "\n" + tmp.substring(tmp.length() - chars) + ".";
            }
            this.textArea.setText(t);
            this.textArea.setCaretPosition(0);
        }
	}

}
