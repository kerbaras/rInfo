package unlp.info.rInfo.ide;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

class TextEditor extends JScrollPane {
    private JTextArea lines;
    private int linesCount = 1;
    private JEditorPane editor;
    private File file;
    private LinePainter linePainter;

    public TextEditor(){
        lines = new JTextArea();
        lines.setBorder(BorderFactory.createMatteBorder(3,0,0,1, Color.lightGray));
        lines.setBackground(new Color(233, 232, 226, 255));
        lines.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        lines.setEditable(false);
        lines.setText("  1.");
        setRowHeaderView(lines);

        editor = new JEditorPane();
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        getViewport().add(editor);

        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        
        linePainter = new LinePainter(editor, new Color(233, 239, 248));
    }

    public TextEditor (File file){
        this();
        this.file = file;
        if(file.exists() && !file.isDirectory() && file.canRead()){
            try {
                FileInputStream fs = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fs.read(data);
                editor.setText(new String(data, "UTF-8") );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void save() throws IOException{
    	editor.write(new FileWriter(file.getAbsoluteFile()));
    }

    private void setLineNumbers () {
        Element root = this.editor.getDocument().getDefaultRootElement();
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
            this.lines.setText(t);
            this.lines.setCaretPosition(0);
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
