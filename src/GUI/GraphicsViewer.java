package GUI;

import javax.swing.*;
import java.awt.*;

public class GraphicsViewer extends JFrame {

    private GraphicsMenuBar menuBar = new GraphicsMenuBar();
    private GraphicsImagePanel imagePanel = new GraphicsImagePanel();
    private GraphicsManagementPanel managementPanel = new GraphicsManagementPanel();

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
