package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsViewer extends JFrame {

    private GraphicsMenuBar menuBar = new GraphicsMenuBar();
    private GraphicsManagementPanel managementPanel = new GraphicsManagementPanel();
    private GraphicsImagePanel imagePanel = new GraphicsImagePanel(managementPanel);

    public GraphicsViewer(){
        Container view = getContentPane();
        this.setLayout(new BorderLayout());
        this.setTitle("Graphics");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        view.add(menuBar, BorderLayout.PAGE_START);
        view.add(imagePanel, BorderLayout.CENTER);
        view.add(managementPanel, BorderLayout.LINE_END);

        initializeDrawingFigures();

        this.setVisible(true);
    }

    public void initializeDrawingFigures() {
        menuBar.addActionListenerToLineItem(click -> managementPanel.setDrawingFigureLabelText("Line"));
        menuBar.addActionListenerToRectangleItem(click -> managementPanel.setDrawingFigureLabelText("Rectangle"));
        menuBar.addActionListenerToCircleItem(click -> managementPanel.setDrawingFigureLabelText("Circle"));
    }
}
