package components;

import enums.FieldSize;

import javax.swing.*;

public class AddingFigurePanel extends JPanel {

    private DoubleTextField startingPoint = new DoubleTextField();
    private DoubleTextField endPoint = new DoubleTextField();
    private JButton accept = new JButton("CREATE");

    public AddingFigurePanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(300, 200);
        add(startingPoint);
        add(endPoint);
        accept.setSize(FieldSize.HALF_FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        add(accept);
    }

    public DoubleTextField getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(DoubleTextField startingPoint) {
        this.startingPoint = startingPoint;
    }

    public DoubleTextField getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(DoubleTextField endPoint) {
        this.endPoint = endPoint;
    }

    public JButton getAccept() {
        return accept;
    }

    public void setAccept(JButton accept) {
        this.accept = accept;
    }
}
