package zadanie1.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicsImagePanel extends JPanel {

    private GraphicsMenuBar menuBar;
    ArrayList<Shape> figures = new ArrayList<>();
    private Shape shapeToMove = null;
    private Shape movingShape = null;
    AffineTransform transform = new AffineTransform();
    Point punkt1;

    private double x1=-1;
    private double y1=-1;
    private double x2=-1;
    private double y2=-1;

    public GraphicsImagePanel(GraphicsMenuBar menuBar){
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        addListeners();
        this.menuBar = menuBar;
    }

    public void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(!menuBar.isMoveEnabled()) {
                    if (x1 < 0) {
                        x1 = event.getX();
                        y1 = event.getY();
                    } else if (x2 < 0) {
                        x2 = event.getX();
                        y2 = event.getY();
                        repaint();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(menuBar.isMoveEnabled()) {
                    punkt1 = e.getPoint();
                    figures.stream()
                            .filter(shape -> shape.getBounds2D().contains(e.getX(), e.getY()))
                            .findFirst().ifPresent(shape -> movingShape = shape);
                    shapeToMove = movingShape;
                    figures.remove(shapeToMove);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(menuBar.isMoveEnabled()) {
                    transform.setToTranslation(e.getX() - punkt1.getX(), e.getY() - punkt1.getY());
                    movingShape = transform.createTransformedShape(shapeToMove);
                    figures.add(movingShape);
                    repaint();
                    movingShape = null;
                    shapeToMove = null;
                }
            }
        });
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graphics = (Graphics2D)g;
        switch (menuBar.getValue()){
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
        figures.forEach(graphics::draw);
        clearCoordinates();
    }

    private void drawRectangle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                figures.add(new Rectangle2D.Double(x2, y1, x1-x2, y2-y1));
            }if(x1>x2 && y1>y2){
                figures.add(new Rectangle2D.Double(x2, y2, x1-x2, y1-y2));
            }if(x1<x2 && y1<y2){
                figures.add(new Rectangle2D.Double(x1, y1, x2-x1, y2-y1));
            }if(x1<x2 && y1>y2){
                figures.add(new Rectangle2D.Double(x1, y2, x2-x1, y1-y2));
            }
        }
    }

    private void drawLine(Graphics2D g){
        figures.add(new Line2D.Double(x1, y1, x2, y2));
    }

    private void drawCircle(Graphics2D g){
        if(x1>-1 && x2>-1){
            if(x1>x2 && y1<y2){
                figures.add(new Ellipse2D.Double(x2, y1, x1-x2, y2-y1));
            }if(x1>x2 && y1>y2){
                figures.add(new Ellipse2D.Double(x2, y2, x1-x2, y1-y2));
            }if(x1<x2 && y1<y2){
                figures.add(new Ellipse2D.Double(x1, y1, x2-x1, y2-y1));
            }if(x1<x2 && y1>y2){
                figures.add(new Ellipse2D.Double(x1, y2, x2-x1, y1-y2));
            }
        }
    }

    public ArrayList<Shape> getFigures() {
        return figures;
    }

    private void clearCoordinates() {
        x1=-1;
        x2=-1;
        y1=-1;
        y2=-1;
    }
}
