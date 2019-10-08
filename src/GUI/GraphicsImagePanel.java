package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicsImagePanel extends JPanel {

    GraphicsManagementPanel panel;

    private double x1=-1;
    private double y1=-1;
    private double x2=-1;
    private double y2=-1;

    public GraphicsImagePanel(GraphicsManagementPanel panel){
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        addListeners();
        this.panel = panel;
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
        switch (panel.getDrawingFigureLabel().getText()){
            case "Line":
                drawLine(graphics);
                break;
            case "Rectangle":
                drawRectangle(graphics);
                break;
            case "Circle":
                drawCircle(graphics);
                break;
            default:
        }
        panel.getFigures().forEach(graphics::draw);
        clearCoordinates();
    }

    private void drawRectangle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                panel.getFigures().add(new Rectangle2D.Double(x2, y1, x1-x2, y2-y1));
            }if(x1>x2 && y1>y2){
                panel.getFigures().add(new Rectangle2D.Double(x2, y2, x1-x2, y1-y2));
            }if(x1<x2 && y1<y2){
                panel.getFigures().add(new Rectangle2D.Double(x1, y1, x2-x1, y2-y1));
            }if(x1<x2 && y1>y2){
                panel.getFigures().add(new Rectangle2D.Double(x1, y2, x2-x1, y1-y2));
            }
        }
    }

    private void drawLine(Graphics2D g){
        panel.getFigures().add(new Line2D.Double(x1, y1, x2, y2));
    }

    private void drawCircle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                panel.getFigures().add(new Ellipse2D.Double(x2, y1, x1-x2, y2-y1));
            }if(x1>x2 && y1>y2){
                panel.getFigures().add(new Ellipse2D.Double(x2, y2, x1-x2, y1-y2));
            }if(x1<x2 && y1<y2){
                panel.getFigures().add(new Ellipse2D.Double(x1, y1, x2-x1, y2-y1));
            }if(x1<x2 && y1>y2){
                panel.getFigures().add(new Ellipse2D.Double(x1, y2, x2-x1, y1-y2));
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
