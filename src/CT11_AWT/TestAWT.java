package CT11_AWT;

/**
 * Created by tencent on 14/10/28.
 * Frame, Panel, 布局DEMO
 * FlowLayout 顺序布局
 * BorderLayout 边框布局
 * GridLayout 网格布局
 * CardLayout 卡片布局
 * GridBagLayout 网格包布局
 */
import com.sun.tools.javac.comp.Flow;

import java.awt.*;
import java.awt.event.*;

public class TestAWT{
    public static void main(String args[]){
        new FirstFrame("AWT DEMO");
    }

}

class FirstFrame extends Frame implements MouseListener{
    private Panel contentPanel = new Panel();
    private enum ButtonType{
        flow, border, grid, card, gridBag, OK, Cancel;
        public static ButtonType getButton(String buttonName){
            return valueOf(buttonName);
        }
    }

    public FirstFrame(String str){
        super(str);
        init();
    }
    void init(){
        setSize(800, 600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());

        Panel panel1 = new Panel();
        panel1.setBackground(Color.blue);
        Label title = new Label("这是Frame, 组件, 布局的一个DEMO");
        title.setForeground(Color.white);
        panel1.add(title);
        add(BorderLayout.NORTH, panel1);

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panel.setBackground(Color.blue);
        add(BorderLayout.SOUTH, panel);

        Button b1 = new Button("OK");
        Button b2 = new Button("Cancel");
        b1.addMouseListener(this);
        b2.addMouseListener(this);

        panel.add(b1);
        panel.add(b2);

        Panel pLeft = new Panel(new GridLayout(5, 1));
        pLeft.setBackground(Color.darkGray);

        Button flow = new Button("flow");
        flow.addMouseListener(this);
        pLeft.add(flow);

        Button border = new Button("border");
        border.addMouseListener(this);
        pLeft.add(border);

        Button grid = new Button("grid");
        grid.addMouseListener(this);
        pLeft.add(grid);

        Button card = new Button("card");
        card.addMouseListener(this);
        pLeft.add(card);

        Button gridBag = new Button("gridBag");
        gridBag.addMouseListener(this);
        pLeft.add(gridBag);

        add(BorderLayout.WEST, pLeft);

        contentPanel.setBackground(Color.red);


        add(BorderLayout.CENTER, contentPanel);

        setVisible(true);
        addWindowListener(new MywindowAdapter());
    }
    class MywindowAdapter extends WindowAdapter{
        public void windowClosing(WindowEvent event){
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }

    void showLayout(ButtonType type){
        System.out.println("showLayout method type="+type);
        switch (type){
            case flow:
            case grid:
                contentPanel.removeAll();
                contentPanel.setLayout(type == ButtonType.flow ? new FlowLayout(10) : new GridLayout(2,3));
                contentPanel.add(new Button("1"));
                contentPanel.add(new Button("2"));
                contentPanel.add(new Button("3"));
                contentPanel.add(new Button("4"));
                contentPanel.add(new Button("5"));
                contentPanel.add(new Button("6"));
                break;
            case border:
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                contentPanel.add(BorderLayout.NORTH, new Button("NORTH"));
                contentPanel.add(BorderLayout.SOUTH, new Button("SOUTH"));
                contentPanel.add(BorderLayout.WEST, new Button("WEST"));
                contentPanel.add(BorderLayout.CENTER, new Button("CENTER"));
                contentPanel.add(BorderLayout.EAST, new Button("EAST"));
                break;
            case card:
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                Panel controlPanel = new Panel();
                class CardButtonListener implements MouseListener{
                    public CardLayout cl;
                    public Panel cards;
                    public void mouseClicked(java.awt.event.MouseEvent mouseEvent){}
                    public void mousePressed(java.awt.event.MouseEvent mouseEvent){
                        if (((Button)mouseEvent.getSource()).getLabel() == "prev"){
                             cl.previous(cards);
                        }else{
                             cl.next(cards);
                        }
                    }
                    public void mouseReleased(java.awt.event.MouseEvent mouseEvent){}
                    public void mouseEntered(java.awt.event.MouseEvent mouseEvent){}
                    public void mouseExited(java.awt.event.MouseEvent mouseEvent){}
                }

                CardButtonListener listener = new CardButtonListener();
                CardLayout cl = new CardLayout();
                Panel cards = new Panel(cl);
                listener.cl = cl;
                listener.cards = cards;

                Button prev = new Button("prev");
                prev.addMouseListener(listener);
                Button next = new Button("next");
                next.addMouseListener(listener);
                controlPanel.add(prev);
                controlPanel.add(next);
                contentPanel.add(BorderLayout.NORTH, controlPanel);

                cards.add("1", new Button("1"));
                cards.add("2", new Button("2"));
                cards.add("3", new Button("3"));
                contentPanel.add(BorderLayout.CENTER, cards);


                break;
            case gridBag:
                showGridBagContent();
                break;
        }
        setVisible(true);
    }

    void showGridBagContent(){

        contentPanel.removeAll();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.setLayout(gb);

        Button b1 = new Button("1");
        setConstraints(gbc, 0, 0, 1, 1, 0, 0);
        gb.setConstraints(b1, gbc);
        contentPanel.add(b1);

        Button b2 = new Button("2");
        setConstraints(gbc, 1, 0, 1, 1, 0, 0);
        gb.setConstraints(b2, gbc);
        contentPanel.add(b2);

        Button b3 = new Button("3");
        setConstraints(gbc, 2, 0, 1, 1, 0, 0);
        gb.setConstraints(b3, gbc);
        contentPanel.add(b3);

        Button b4 = new Button("4");
        setConstraints(gbc, 3, 0, 1, 1, 0, 0);
        gb.setConstraints(b4, gbc);
        contentPanel.add(b4);

        Button b5 = new Button("5");
        setConstraints(gbc, 4, 0, 1, 1, 0, 0);
        gb.setConstraints(b5, gbc);
        contentPanel.add(b5);

        Button b6 = new Button("6");
        setConstraints(gbc, 5, 0, 1, 1, 0, 0);
        gb.setConstraints(b6, gbc);
        contentPanel.add(b6);

        Button b7 = new Button("7");
        setConstraints(gbc, 6, 0, 1, 1, 0, 0);
        gb.setConstraints(b7, gbc);
        contentPanel.add(b7);

        Button b8 = new Button("8");
        setConstraints(gbc, 7, 0, 0, 1, 1, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(b8, gbc);
        contentPanel.add(b8);

        Button b21 = new Button("21");
        setConstraints(gbc, 0, 1, 4, 1, 0, 0);
        gb.setConstraints(b21, gbc);
        contentPanel.add(b21);

        Button b22 = new Button("22");
        setConstraints(gbc, 4, 1, 4, 1, 1, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(b22, gbc);
        contentPanel.add(b22);

        Button b23 = new Button("23");
        setConstraints(gbc, 8, 1, 1, 1, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gb.setConstraints(b23, gbc);
        contentPanel.add(b23);

        Button b24 = new Button("24");
        setConstraints(gbc, 9, 1, 0, 1, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gb.setConstraints(b24, gbc);
        contentPanel.add(b24);

        Button b31 = new Button("31");
        setConstraints(gbc, 0, 2, 5, 1, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(b31, gbc);
        contentPanel.add(b31);

        Button b32 = new Button("32");
        setConstraints(gbc, 5, 2, 6, 1, 1, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(b32, gbc);
        contentPanel.add(b32);

        Button b33 = new Button("33");
        setConstraints(gbc, 11, 2, 0, 1, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gb.setConstraints(b33, gbc);
        contentPanel.add(b33);

    }

    void setConstraints(GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight, float weightx, float weighty){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    public void mouseClicked(MouseEvent evt){
        System.out.print("clicked");
    }
    public void mousePressed(java.awt.event.MouseEvent mouseEvent){
        Button target = (Button)mouseEvent.getSource();
        System.out.println("你点击了 "+target.getLabel());
        ButtonType type = ButtonType.getButton(target.getLabel());
        switch (type){
            case OK:
            case Cancel:
                setVisible(false);
                dispose();
                System.exit(0);
                System.out.print("pressed");
                break;
            default:
                showLayout(type);
                break;
        }
    }

    public void mouseReleased(java.awt.event.MouseEvent mouseEvent){
        System.out.print("released");
    }

    public void mouseEntered(java.awt.event.MouseEvent mouseEvent){
        System.out.print("entered");
    }

    public void mouseExited(java.awt.event.MouseEvent mouseEvent){
        System.out.print("exited");
    }
}
