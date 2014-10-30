package CT13_swing;

/**
 * Created by tencent on 14/10/30.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingDemo {
    public static void main(String args[]){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        final JFrame frame = new JFrame("Swing Demo");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
        Container container = frame.getContentPane();
        JPanel panel = new JPanel(new FlowLayout());
        container.add(panel);
        final JLabel label = new JLabel("hello");
        panel.add(label);

        JButton btn = new JButton("click me");
        btn.setToolTipText("点我嘛");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                label.setText("you clicked button!");
            }
        });
        panel.add(btn);

        //图标按钮
        JButton iconBtn = new JButton(new ImageIcon("/3.png"));

        panel.add(iconBtn);

        frame.setSize(800, 600);
        frame.setLocation(300, 200);
        frame.setVisible(true);
    }
}
