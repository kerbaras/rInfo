package unlp.info.rInfo.ide;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.TreeNode;

public class FileNode implements TreeNode {
	
	private File file;
	private FileNode parent;
	private Enumeration<FileNode> efiles;
	private Vector<FileNode> files;
	
	public FileNode(File file){
		this.file = file;
		if(file.isDirectory() && file.listFiles() != null){
			files = new Vector<FileNode>();
			for(File f : file.listFiles()){
				files.add(new FileNode(f, this));
			}
			efiles = new Enumeration<FileNode>() {
				@Override
				public boolean hasMoreElements() {
					// TODO Auto-generated method stub
					return files.iterator().hasNext();
				}

				@Override
				public FileNode nextElement() {
					// TODO Auto-generated method stub
					return files.iterator().next();
				}
			};
		}
	}
	
	public FileNode(File file, FileNode parent){
		this(file);
		this.parent = parent;
	}
	
	public String toString() {
		return file.getName();
	};

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return files.get(childIndex);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return (file.listFiles() != null) ? file.listFiles().length : 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return file.isDirectory();
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return !file.isDirectory();
	}

	@Override
	public Enumeration children() {
		if(!file.isDirectory())
			return null;
		return efiles;
	}
	
	public File getFile() {
		return file;
	}
}
