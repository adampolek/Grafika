package GUI;

import enums.FieldSize;

import javax.swing.*;
import java.awt.*;

public class GraphicsManagementPanel extends JPanel {

    private JLabel infoLabel = new JLabel("Now Drawing: ");
    private JLabel drawingFigureLabel = new JLabel("Nothing");

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
    }

    public void setDrawingFigureLabelText(String text){
        drawingFigureLabel.setText(text);
    }
}
