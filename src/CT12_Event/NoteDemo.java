package CT12_Event;

import java.awt.*;
import java.io.*;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by tencent on 14/10/30.
 */
public class NoteDemo extends Frame implements ActionListener{
    //菜单
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("文件");
    MenuItem newFileMenuItem = new MenuItem("新建");
    MenuItem openMenuItem = new MenuItem("打开");
    MenuItem saveMenuItem = new MenuItem("保存");
    MenuItem saveAnotherItem = new MenuItem("另存为");
    TextArea textArea = new TextArea();
    FileDialog fileDialog = new FileDialog(this, "打开文件", FileDialog.LOAD);
    String currentFileName;


    public static void main(String args[]){
        NoteDemo f = new NoteDemo("记事本");
        f.init();
        f.setVisible(true);
    }
    public NoteDemo(String title){
        super(title);

    }
    public void init(){
        final NoteDemo frame = this;
        setLayout(new BorderLayout());
        frame.setSize(800, 600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
        //菜单
        newFileMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        saveAnotherItem.addActionListener(this);
        fileMenu.add(newFileMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAnotherItem);
        menuBar.add(fileMenu);
        setMenuBar(menuBar);

        add(BorderLayout.CENTER, textArea);

    }

    //事件处理
    public void actionPerformed(ActionEvent e){
        Object target = e.getSource();
        if(target == saveAnotherItem){
            currentFileName = null;
        }
        if(target == newFileMenuItem){
            textArea.setText("");
            currentFileName = null;
        }else if(target == openMenuItem){
            fileDialog.setVisible(true);
            String fileName = fileDialog.getDirectory() + fileDialog.getFile();
            if(fileName != null){
                textArea.setText("");
                readFile(fileName);
                currentFileName = fileName;
            }
        }else if(target == saveMenuItem || target == saveAnotherItem){
            if(currentFileName != null){
                writeFile(currentFileName);
            }else{
                FileDialog fd = new FileDialog(this, "save file", FileDialog.SAVE);
                fd.setVisible(true);
                String fileName = fd.getDirectory() + fd.getFile();
                if(fileName != null){
                    writeFile(fileName);
                }
            }
        }
    }

    void readFile(String fileName){
        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            int size = (int)file.length();
            int charsRead = 0;
            char[] content = new char[size];
            while(fr.ready()){
                charsRead += fr.read(content, charsRead, size - charsRead);
            }
            fr.close();
            textArea.setText(new String(content, 0, charsRead));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void writeFile(String fileName){
        try{
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            fw.write(textArea.getText());
            fw.close();
            currentFileName = fileName;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
