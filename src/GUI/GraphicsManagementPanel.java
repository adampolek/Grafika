package GUI;

import components.AddingFigurePanel;
import enums.FieldSize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsManagementPanel extends JPanel {

    ArrayList<Shape> figures = new ArrayList<>();
    private JLabel infoLabel = new JLabel("Now Drawing: ");
    private JLabel drawingFigureLabel = new JLabel("Nothing");
    private AddingFigurePanel addingPanel = new AddingFigurePanel();

    public GraphicsManagementPanel(){
        this.setPreferredSize(new Dimension(300, getHeight()));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY);

        infoLabel.setBorder(BorderFactory.createEmptyBorder(FieldSize.MARGIN.getValue(), FieldSize.MARGIN.getValue(), 10, FieldSize.MARGIN.getValue()));
        infoLabel.setMaximumSize(new Dimension(FieldSize.FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue()));
        infoLabel.setFont(infoLabel.getFont().deriveFont(24F));

        drawingFigureLabel.setBorder(BorderFactory.createEmptyBorder(FieldSize.MARGIN.getValue(), FieldSize.MARGIN.getValue(), 10, 10));
        drawingFigureLabel.setMaximumSize(new Dimension(FieldSize.FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue()));
        drawingFigureLabel.setFont(infoLabel.getFont().deriveFont(20F));

        this.add(infoLabel);
        this.add(drawingFigureLabel);
        this.add(addingPanel);
    }

    public void setDrawingFigureLabelText(String text){
        drawingFigureLabel.setText(text);
    }

    public JLabel getDrawingFigureLabel() {
        return drawingFigureLabel;
    }

    public ArrayList<Shape> getFigures() {
        return figures;
    }
}
