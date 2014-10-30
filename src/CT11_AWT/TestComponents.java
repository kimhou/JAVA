package CT11_AWT;

import com.sun.script.util.ScriptEngineFactoryBase;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by tencent on 14/10/29.
 * AWT组件DEMO
 */
public class TestComponents extends Frame {
    public static void main(String args[]){
        final TestComponents frame = new TestComponents();
        frame.setSize(800, 600);
        frame.setLocation(200, 200);
        frame.setLayout(new BorderLayout());
        frame.init();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
    }

    void init(){

        final Panel content = new Panel(new FlowLayout());
        add(content);

        //标签
        Label label = new Label("I'm a lable");
        label.setBackground(Color.blue);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        content.add(label);

        //按钮
        final Button btn = new Button("我是一个按钮");
        btn.setFont(new Font("宋体", Font.PLAIN, 14));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                btn.setLabel("你点了我吧!现在点不了了吧!!");
                btn.setEnabled(false);
            }
        });

        content.add(btn);

        //复选框
        content.add(new Checkbox("复选框", null, true));

        //单选框
        CheckboxGroup cbg = new CheckboxGroup();
        content.add(new Checkbox("1", cbg, true));
        content.add(new Checkbox("2", cbg, false));
        content.add(new Checkbox("3", cbg, false));

        //文本输入框
        TextField t1 = new TextField(10);
        TextField t2 = new TextField("hello");
        TextField t3 = new TextField("hello gay", 20);
        t3.setEditable(false);
        content.add(t1);
        content.add(t2);
        content.add(t3);

        TextArea ta1 = new TextArea();
        ta1.setText("I'm ta1");
        TextArea ta2 = new TextArea("ta2: asdfasdf");
        final TextArea ta3 = new TextArea("ta3: ddd",4,50);
        content.add(ta1);
        content.add(ta2);
        content.add(ta3);

        //滚动条
        Scrollbar bar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 100);
        content.add(bar);

        //对话框
        Button dialogBtn = new Button("File Dialog");
        final Frame tempF = this;
        dialogBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                FileDialog fd = new FileDialog(tempF, "file dialog");
                fd.setVisible(true);
                ta3.setText(fd.getFile());
            }
        });
        content.add(dialogBtn);

        //菜单
        MenuBar mb = new MenuBar();
        Menu menu1 = new Menu("menu1");
        menu1.add(new MenuItem("item1"));
        menu1.add(new MenuItem("item2"));
        menu1.add(new MenuItem("item3"));
        mb.add(menu1);
        mb.add(new Menu("menu2"));
        setMenuBar(mb);
        mb.setHelpMenu(new Menu("help"));
        Menu exit = new Menu("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("kdsajflsf");
                System.exit(0);
            }
        });
        mb.add(exit);

        final PopupMenu pop = new PopupMenu("pop menu");

        pop.add("menu1");
        pop.add("menu2");
        pop.add(new MenuItem("item3"));
        ta3.add(pop);

        ta3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                System.out.println("released");
                super.mousePressed(mouseEvent);
                //if(mouseEvent.isPopupTrigger()){
                    pop.show(ta3, mouseEvent.getX(), mouseEvent.getY());
                //}
            }
        });


        content.setVisible(true);

        //画布

        MyCanvas cv = new MyCanvas();
        cv.repaint(0, 0, 100, 100);

        final Frame f = new Frame();
        f.setSize(400, 400);
        f.add(cv);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                f.setVisible(false);
                f.dispose();
            }
        });
        f.setVisible(true);

        //绘图
        Button graphicsButton = new Button("Frame绘图");
        graphicsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                GraphicFrame gf = new GraphicFrame("绘图");
                gf.setVisible(true);
            }
        });
        content.add(graphicsButton);
    }
}

//canvas 绘图
class MyCanvas extends Canvas{

    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.drawRect(100, 100, 100, 100);
        g.fillRect(200, 200, 100, 100);
    }
}

//frame 绘图
class GraphicFrame extends Frame{
    public GraphicFrame(String title){
        super(title);
        setSize(400, 300);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                setVisible(false);
                dispose();
            }
        });
    }

    public void paint(Graphics g){
        //文字
        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Hi, I',m a drawing string!", 0, 50);
        //线
        g.drawLine(0, 50, 400, 60);
        //矩形
        g.drawRect(10, 65, 50, 50);
        g.drawRoundRect(70, 65, 50, 50, 30, 30);
        g.fillRect(130, 65, 50,50);
        g.fill3DRect(190, 65, 50, 50, true);

        //椭圆
        g.fillOval(10, 120, 100, 50);
        //圆弧
        g.fillArc(110, 120, 100, 50, 0, 90);

        int[] xP = {10, 60, 120}, yP = {200, 250, 200};

        g.drawPolyline(xP, yP, 3);
        int[] xP2 = {150, 180, 300};
        g.drawPolygon(xP2, yP, 3);

        int[] xP3 = {310, 350, 400};
        g.fillPolygon(xP3, yP, 3);


    }
}
