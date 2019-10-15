package zadanie1.GUI;

import zadanie1.components.AddingFigurePanel;
import zadanie1.enums.FieldSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GraphicsManagementPanel extends JPanel {

    private GraphicsImagePanel imagePanel;

    private JLabel infoLabel = new JLabel("Now Drawing: ");
    private JLabel drawingFigureLabel = new JLabel("Nothing");
    private JLabel moveLabel = new JLabel("Moving Disabled");
    private AddingFigurePanel addingPanel = new AddingFigurePanel();

    public GraphicsManagementPanel(GraphicsImagePanel imagePanel){
        this.setPreferredSize(new Dimension(300, getHeight()));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY);

        this.imagePanel = imagePanel;

        infoLabel.setBorder(BorderFactory.createEmptyBorder(FieldSize.MARGIN.getValue(), FieldSize.MARGIN.getValue(), 10, FieldSize.MARGIN.getValue()));
        infoLabel.setMaximumSize(new Dimension(FieldSize.FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue()));
        infoLabel.setFont(infoLabel.getFont().deriveFont(24F));

        drawingFigureLabel.setBorder(BorderFactory.createEmptyBorder(FieldSize.MARGIN.getValue(), FieldSize.MARGIN.getValue(), 10, 10));
        drawingFigureLabel.setMaximumSize(new Dimension(FieldSize.FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue()));
        drawingFigureLabel.setFont(infoLabel.getFont().deriveFont(20F));

        moveLabel.setBorder(BorderFactory.createEmptyBorder(FieldSize.MARGIN.getValue(), FieldSize.MARGIN.getValue(), 10, 10));
        moveLabel.setMaximumSize(new Dimension(FieldSize.FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue()));
        moveLabel.setFont(infoLabel.getFont().deriveFont(20F));

        this.add(infoLabel);
        this.add(drawingFigureLabel);
        this.add(addingPanel);
        this.add(moveLabel);

        addingPanel.getAccept().addActionListener(event -> {
            double x1 = Double.parseDouble(addingPanel.getFirstSectionXTextField().getText());
            double y1 = Double.parseDouble(addingPanel.getFirstSectionYTextField().getText());
            double x2 = Double.parseDouble(addingPanel.getSecondSectionXTextField().getText());
            double y2 = Double.parseDouble(addingPanel.getSecondSectionYTextField().getText());
            switch (drawingFigureLabel.getText()){
                case "Line":
                    imagePanel.getFigures().add(new Line2D.Double(x1, y1, x2, y2));
                    break;
                case "Rectangle":
                    imagePanel.getFigures().add(new Rectangle2D.Double(x1, y1, x2, y2));
                    break;
                case "Circle":
                    imagePanel.getFigures().add(new Ellipse2D.Double(x1, y1, x2, y2));
                    break;
                default:
            }
            imagePanel.repaint();
        });
    }

    public void setDrawingFigureLabelText(String text){
        drawingFigureLabel.setText(text);
    }

    public void setMoveLabel(String text) {
        moveLabel.setText(text);
    }
}
