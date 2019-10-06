package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicsImagePanel extends JPanel {

    private int x1=-1;
    private int y1=-1;
    private int x2=-1;
    private int y2=-1;

    public GraphicsImagePanel(){
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        addListeners();
    }

    public void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(x1<0){
                    x1=event.getX();
                    y1=event.getY();
                }else if(x2<0){
                    x2 = event.getX();
                    y2 = event.getY();
                    repaint();
                }
            }
        });
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graphics = (Graphics2D)g;
//        drawRectangle(graphics);
//        drawLine(graphics);
        drawCircle(graphics);
        clearCoordinates();
    }

    private void drawRectangle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                g.drawRect(x2, y1, x1-x2, y2-y1);
            }if(x1>x2 && y1>y2){
                g.drawRect(x2, y2, x1-x2, y1-y2);
            }if(x1<x2 && y1<y2){
                g.drawRect(x1, y1, x2-x1, y2-y1);
            }if(x1<x2 && y1>y2){
                g.drawRect(x1, y2, x2-x1, y1-y2);
            }
        }
    }

    private void drawLine(Graphics2D g){
        g.drawLine(x1, y1, x2, y2);
    }

    private void drawCircle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                g.drawOval(x2, y1, x1-x2, y2-y1);
            }if(x1>x2 && y1>y2){
                g.drawOval(x2, y2, x1-x2, y1-y2);
            }if(x1<x2 && y1<y2){
                g.drawOval(x1, y1, x2-x1, y2-y1);
            }if(x1<x2 && y1>y2){
                g.drawOval(x1, y2, x2-x1, y1-y2);
            }
        }
    }

    private void clearCoordinates() {
        x1=-1;
        x2=-1;
        y1=-1;
        y2=-1;
    }
}
