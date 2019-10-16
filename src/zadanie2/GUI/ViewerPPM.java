package zadanie2.GUI;

import zadanie2.shared.ImageSharedOperations;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewerPPM extends JFrame {

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu files = new JMenu("File");
    private final JMenuItem loadImage = new JMenuItem("Load image");
    private final JMenuItem saveImage = new JMenuItem("Save image");
    private final JLabel imageLabel = new JLabel();

    public ViewerPPM() {
        this.setLayout(new BorderLayout());
        this.setTitle("Podstawy Biometrii");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.menuBar.add(this.files);
        this.files.add(this.loadImage);
        this.files.add(this.saveImage);

        this.add(this.menuBar, BorderLayout.PAGE_START);
        this.add(this.imageLabel, BorderLayout.CENTER);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);
        this.imageLabel.setVerticalAlignment(JLabel.CENTER);
        this.imageLabel.setVisible(true);

        this.loadImage.addActionListener((ActionEvent e) -> {
            JFileChooser imageOpener = new JFileChooser();
            imageOpener.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String fileName = f.getName().toLowerCase();
                    if(fileName.endsWith(".ppm") || fileName.endsWith(".jpg")) {
                        return true;
                    } else return false;
                }

                @Override
                public String getDescription() {
                    return "Image files (.ppm)";
                }
            });

            int returnValue = imageOpener.showDialog(null, "Select image");
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                ImageSharedOperations imageSharedOperations = new ImageSharedOperations();
                String path = imageOpener.getSelectedFile().getPath();
                if(path.endsWith(".ppm")) {
                    BufferedImage img = imageSharedOperations.loadImage(imageOpener.getSelectedFile().getPath());
                    Image image = img.getScaledInstance(img.getWidth() * getWidth() / img.getWidth() - 100
                            , img.getHeight() * getHeight() / img.getHeight() - 100
                            , Image.SCALE_FAST);
                    this.imageLabel.setIcon(new ImageIcon(image));
                } else if(path.endsWith(".jpg") || path.endsWith("jpeg")) {
                    try {
                        BufferedImage img = ImageIO.read(imageOpener.getSelectedFile());
                        this.imageLabel.setIcon(new ImageIcon(img));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else JOptionPane.showMessageDialog(this, "Invalid Format");
            }
        });

        this.saveImage.addActionListener(e -> {
            ImageSharedOperations imageSharedOperations = new ImageSharedOperations();
            BufferedImage image = imageSharedOperations.convertIconToImage((ImageIcon) imageLabel.getIcon());
            imageSharedOperations.saveImage(image, "image.jpg");
        });
    }
}
