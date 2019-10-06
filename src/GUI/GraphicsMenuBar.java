package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class GraphicsMenuBar extends JMenuBar {

    private JMenu draw = new JMenu("Draw");
    private JMenuItem line = new JMenuItem("Line");
    private JMenuItem rectangle = new JMenuItem("Rectangle");
    private JMenuItem circle = new JMenuItem("Circle");

    public GraphicsMenuBar(){
        draw.add(line);
        draw.add(rectangle);
        draw.add(circle);
        this.add(draw);
    }

    public void addActionListenerToLineItem(ActionListener listener){
        line.addActionListener(listener);
    }

    public void addActionListenerToRectangleItem(ActionListener listener){
        rectangle.addActionListener(listener);
    }

    public void addActionListenerToCircleItem(ActionListener listener){
        circle.addActionListener(listener);
    }
}
