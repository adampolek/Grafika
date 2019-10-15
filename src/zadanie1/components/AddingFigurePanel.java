package zadanie1.components;

import zadanie1.enums.FieldSize;

import javax.swing.*;
import java.awt.*;

public class AddingFigurePanel extends JPanel {

    private JPanel firstSection = new JPanel();
    private JPanel secondSection = new JPanel();
    private JButton accept = new JButton("CREATE");

    private JLabel firstSectionXLabel = new JLabel("X1: ");
    private JTextField firstSectionXTextField = new JTextField();
    private JLabel firstSectionYLabel = new JLabel("Y1: ");
    private JTextField firstSectionYTextField = new JTextField();

    private JLabel secondSectionXLabel = new JLabel("X2: ");
    private JTextField secondSectionXTextField = new JTextField();
    private JLabel secondSectionYLabel = new JLabel("Y2: ");
    private JTextField secondSectionYTextField = new JTextField();

    public AddingFigurePanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(300, 200));
        accept.setSize(FieldSize.HALF_FIELD_WIDTH_SIZE.getValue(), FieldSize.FIELD_HEIGHT_SIZE.getValue());
        createFirstPoint();
        createSecondPoint();
        this.add(firstSection);
        this.add(secondSection);
        this.add(accept);
    }

    private void createSecondPoint() {
        createSection(secondSection, secondSectionXLabel, secondSectionXTextField, secondSectionYLabel, secondSectionYTextField);
    }

    private void createSection(JPanel section, JLabel sectionXLabel, JTextField sectionXTextField, JLabel sectionYLabel, JTextField sectionYTextField) {
        section.setLayout(null);
        sectionXLabel.setBounds(0, 0, 30, 50);
        sectionXTextField.setBounds(40, 0, 100, 50);
        sectionYLabel.setBounds(140, 0, 30, 50);
        sectionYTextField.setBounds(180, 0, 100, 50);
        section.add(sectionXLabel);
        section.add(sectionXTextField);
        section.add(sectionYLabel);
        section.add(sectionYTextField);
    }

    private void createFirstPoint() {
        createSection(firstSection, firstSectionXLabel, firstSectionXTextField, firstSectionYLabel, firstSectionYTextField);
    }

    public JButton getAccept() {
        return accept;
    }

    public void setAccept(JButton accept) {
        this.accept = accept;
    }

    public JTextField getFirstSectionXTextField() {
        return firstSectionXTextField;
    }

    public void setFirstSectionXTextField(JTextField firstSectionXTextField) {
        this.firstSectionXTextField = firstSectionXTextField;
    }

    public JTextField getFirstSectionYTextField() {
        return firstSectionYTextField;
    }

    public void setFirstSectionYTextField(JTextField firstSectionYTextField) {
        this.firstSectionYTextField = firstSectionYTextField;
    }

    public JTextField getSecondSectionXTextField() {
        return secondSectionXTextField;
    }

    public void setSecondSectionXTextField(JTextField secondSectionXTextField) {
        this.secondSectionXTextField = secondSectionXTextField;
    }

    public JTextField getSecondSectionYTextField() {
        return secondSectionYTextField;
    }

    public void setSecondSectionYTextField(JTextField secondSectionYTextField) {
        this.secondSectionYTextField = secondSectionYTextField;
    }
}
