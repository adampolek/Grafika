package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GraphicsMenuBar extends JMenuBar {

    private String value = "";
    private boolean isMoveEnabled = false;
    private JMenu draw = new JMenu("Draw");
    private JMenuItem line = new JMenuItem("Line");
    private JMenuItem rectangle = new JMenuItem("Rectangle");
    private JMenuItem circle = new JMenuItem("Circle");

    private JMenu move = new JMenu("Move");
    private JMenuItem toggleMove = new JMenuItem("Change Moving Status");

    public GraphicsMenuBar(){
        draw.add(line);
        draw.add(rectangle);
        draw.add(circle);
        this.add(draw);
        move.add(toggleMove);
        this.add(move);
    }

    public void addActionListenerToToggleMove(ActionListener listener){
        toggleMove.addActionListener(listener);
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setMoveEnabled(boolean moveEnabled) {
        isMoveEnabled = moveEnabled;
    }

    public boolean isMoveEnabled() {
        return isMoveEnabled;
    }
}
