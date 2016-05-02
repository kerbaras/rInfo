package unlp.info.rInfo.ide;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

@SuppressWarnings("serial")
public class IDE extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTree tree;
	private JSplitPane splitPane;

	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		IDE ide = new IDE();
		ide.setVisible(true);
	}

	public IDE() {
		super("rInfo - IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setLocationRelativeTo(null);

		setJMenuBar(createMenuBar());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(createToolBar(), BorderLayout.NORTH);

		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		tree = new JTree();
		tree.setVisible(false);
		tree.setModel(null);
		tree.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					loadFile();
			}
		});
		tree.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					loadFile();
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		splitPane.setLeftComponent(new JScrollPane(tree));
		splitPane.setEnabled(false);
		splitPane.setDividerLocation(0);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu mnfile = new JMenu("File");
		menuBar.add(mnfile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener((ActionEvent e) -> newFile());
		mnfile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener((ActionEvent e) -> openFile());
		mnfile.add(mntmOpen);

		JSeparator separator = new JSeparator();
		mnfile.add(separator);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener((ActionEvent e) -> close(tabbedPane.getSelectedIndex()));
		mnfile.add(mntmClose);
		
		JMenuItem mntmCloseAll = new JMenuItem("Close All");
		mntmCloseAll.addActionListener((ActionEvent e) -> closeAll());
		mnfile.add(mntmCloseAll);
		return menuBar;
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();

		JButton btnNew = new JButton(new ImageIcon(getClass().getResource("/resources/new.png")));
		btnNew.addActionListener((ActionEvent e) -> newFile());
		toolBar.add(btnNew);

		JButton btnOpen = new JButton(new ImageIcon(getClass().getResource("/resources/open.png")));
		btnOpen.addActionListener((ActionEvent e) -> openFile());
		toolBar.add(btnOpen);

		JButton btnSave = new JButton(new ImageIcon(getClass().getResource("/resources/save.png")));
		btnSave.setToolTipText("Save");
		btnSave.addActionListener((ActionEvent e) -> save(tabbedPane.getSelectedIndex()));
		toolBar.add(btnSave);

		JButton btnSaveAll = new JButton(new ImageIcon(getClass().getResource("/resources/save_all.png")));
		btnSaveAll.setToolTipText("Save All");
		btnSaveAll.addActionListener((ActionEvent e) -> saveAll());
		toolBar.add(btnSaveAll);

		JToolBar toolBar_1 = new JToolBar();
		toolBar.add(toolBar_1);

		JButton btnRun = new JButton(new ImageIcon(getClass().getResource("/resources/run.png")));
		btnRun.setToolTipText("Run");
		toolBar_1.add(btnRun);
		btnRun.addActionListener((ActionEvent e) -> prueba.Main.main(null));

		JButton btnDebug = new JButton(new ImageIcon(getClass().getResource("/resources/debug.png")));
		btnDebug.setToolTipText("Debug");
		toolBar_1.add(btnDebug);

		return toolBar;
	}

	private void openFile() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.isDirectory()) {
				addDirectoryToTree(file);
				return;
			}
			loadFile(file);
		}
	}

	private void addDirectoryToTree(File file) {
		tree.setModel(new TreeModel() {

			@Override
			public void valueForPathChanged(TreePath path, Object newValue) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeTreeModelListener(TreeModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isLeaf(Object node) {
				// TODO Auto-generated method stub
				return ((FileNode) node).isLeaf();
			}

			@Override
			public Object getRoot() {
				// TODO Auto-generated method stub
				return new FileNode(file);
			}

			@Override
			public int getIndexOfChild(Object parent, Object child) {
				// TODO Auto-generated method stub
				return ((FileNode) parent).getIndex((TreeNode) child);
			}

			@Override
			public int getChildCount(Object parent) {
				// TODO Auto-generated method stub
				return ((FileNode) parent).getChildCount();
			}

			@Override
			public Object getChild(Object parent, int index) {
				// TODO Auto-generated method stub
				return ((FileNode) parent).getChildAt(index);
			}

			@Override
			public void addTreeModelListener(TreeModelListener l) {
				// TODO Auto-generated method stub

			}
		});
		tree.setVisible(true);
		splitPane.setDividerLocation(200);
		splitPane.setEnabled(true);

	}

	private void loadFile() {
		loadFile(((FileNode) tree.getLastSelectedPathComponent()).getFile());
	}

	private void loadFile(File f) {
		if (f.isDirectory())
			return;
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			File fE = ((TextEditor) tabbedPane.getComponentAt(i)).getFile();
			if (fE != null && (fE.getAbsolutePath() == f.getAbsolutePath())) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}
		tabbedPane.addTab(f.getName(), new TextEditor(f));
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}

	private void newFile() {
		tabbedPane.addTab("Untitle", null, new TextEditor());
	}

	private void save(int i) {
		if (i == -1)
			return;
		TextEditor editor = (TextEditor) tabbedPane.getComponentAt(i);
		if (editor.getFile() == null) {
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
				return;
			editor.setFile(fc.getSelectedFile());
			tabbedPane.setTitleAt(i, editor.getFile().getName());
		}

		try {
			editor.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveAll() {
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			save(i);
		}
	}

	private void close(int i) {
		save(i);
		tabbedPane.remove(i);
	}
	
	private void closeAll() {
		saveAll();
		tabbedPane.removeAll();
	}
}
