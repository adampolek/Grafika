package components;

import enums.FieldSize;

import javax.swing.*;
import java.awt.*;

public class DoubleTextField extends JPanel{

    private JTextField xField = new JTextField();
    private JTextField yField = new JTextField();
    private JLabel xLabel = new JLabel();
    private JLabel yLabel = new JLabel();

    public DoubleTextField(){
        this.setLayout(new FlowLayout());
        setButtonSizes();
    }

    private void setButtonSizes() {
        xField.setSize(FieldSize.FIELD_SIZE_100.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        yField.setSize(FieldSize.FIELD_SIZE_100.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        xLabel.setSize(FieldSize.FIELD_HEIGHT_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        yLabel.setSize(FieldSize.FIELD_HEIGHT_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
    }

    public JTextField getxField() {
        return xField;
    }

    public void setxField(JTextField xField) {
        this.xField = xField;
    }

    public JTextField getyField() {
        return yField;
    }

    public void setyField(JTextField yField) {
        this.yField = yField;
    }

    public JLabel getxLabel() {
        return xLabel;
    }

    public void setxLabel(JLabel xLabel) {
        this.xLabel = xLabel;
    }

    public JLabel getyLabel() {
        return yLabel;
    }

    public void setyLabel(JLabel yLabel) {
        this.yLabel = yLabel;
    }
}
