package zadanie1.GUI;

import javax.swing.*;
import java.awt.*;

public class GraphicsViewer extends JFrame {

    private GraphicsMenuBar menuBar = new GraphicsMenuBar();
    private GraphicsImagePanel imagePanel = new GraphicsImagePanel(menuBar);
    private GraphicsManagementPanel managementPanel = new GraphicsManagementPanel(imagePanel);

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
        menuBar.addActionListenerToLineItem(click -> {
            menuBar.setValue("Line");
            managementPanel.setDrawingFigureLabelText("Line");
        });
        menuBar.addActionListenerToRectangleItem(click -> {
            menuBar.setValue("Rectangle");
            managementPanel.setDrawingFigureLabelText("Rectangle");
        });
        menuBar.addActionListenerToCircleItem(click -> {
            menuBar.setValue("Circle");
            managementPanel.setDrawingFigureLabelText("Circle");
        });
        menuBar.addActionListenerToToggleMove(click -> {
            menuBar.setMoveEnabled(!menuBar.isMoveEnabled());
            if(menuBar.isMoveEnabled()){
                managementPanel.setMoveLabel("Moving Enabled");
            }else{
                managementPanel.setMoveLabel("Moving Disabled");
            }
        });
    }
}
