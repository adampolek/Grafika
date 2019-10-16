package zadanie2.shared;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ImageSharedOperations {

    int countChars = 2;

    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Integer> values = new ArrayList<>();
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int format;
            //read file format: P3 or P6
            do {
                format = reader.read();
            } while (format != 80);
            format = reader.read();

            //read file width

            int width = readValue(stringBuilder, reader);

            stringBuilder.setLength(0);

            //read file height

            int height = readValue(stringBuilder, reader);

            stringBuilder.setLength(0);

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //read max value

            int maxValue = readValue(stringBuilder, reader);

            stringBuilder.setLength(0);

            switch (format) {
                //3 in ASCII
                case 51:
                    return readPPM3(image, reader, width, height, maxValue);
                //6 in ASCII
                case 54:
                    readPPM6(image, file, width, height, maxValue);
                    break;
                default:
                    break;
            }

        } catch (IOException ex) {
            System.out.println("Error has occured during file reading: " + ex.getMessage());
        }
        return image;
    }

    private BufferedImage readPPM6(BufferedImage image, File file, int width, int height, int maxValue) {
        try {
            BufferedInputStream inputBytes = new BufferedInputStream(new FileInputStream(file));
            inputBytes.skip(countChars);
            int buffer=-2;
            int r, g, b;
            int w = 0, h = 0;
            do {
                if (buffer == -2) {
                    r = inputBytes.read();
                } else {
                    r = buffer;
                }
                g = inputBytes.read();
                b = inputBytes.read();
                try {
                    image.setRGB(w, h, (r * 255)/maxValue << 16 | (g * 255)/maxValue << 8 | (b * 255)/maxValue);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                buffer = inputBytes.read();
                w++;
                if(w == width){
                    w=0;
                    h++;
                    if(h == height){
                        break;
                    }
                }
            } while (buffer != -1);
            inputBytes.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static BufferedImage readPPM3(BufferedImage image, BufferedReader reader, int width, int height, int maxValue) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] buff = new char[512];
        int r = -1, g = -1, b = -1;
        int i = -1;
        int w=0, h=0;
        try {
            int buffer;
            do {
                buffer = reader.read(buff);
                for (char a: buff) {
                    i++;
                    if (a == '#') {
                        if (buff[i + 1] != '\n') {
                            buff[i + 1] = '#';
                        }
                    } else if (a != '\t' && a != '\n' && a != ' ' && a != '\0') {
                        stringBuilder.append(a);
                    } else if (a == '\u0000') {
                        break;
                    } else if ( !stringBuilder.toString().equals("")){
                        if (r < 0) {
                            r = Integer.parseInt(stringBuilder.toString());
                        } else if (g < 0) {
                            g = Integer.parseInt(stringBuilder.toString());
                        } else if (b < 0) {
                            b = Integer.parseInt(stringBuilder.toString());
                            image.setRGB( w, h, (r * 255)/maxValue << 16 | (g * 255)/maxValue << 8 | (b * 255)/maxValue);
                            w++;
                            if(w == width){
                                w=0;
                                h++;
                                if(h == height){
                                    break;
                                }
                            }
                            r = -1;
                            g = -1;
                            b = -1;
                        }
                        stringBuilder.setLength(0);
                    }
                }
            } while (buffer!=-1);
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private int readValue(StringBuilder stringBuilder, BufferedReader reader) throws IOException {
        int pom;
        do {
            pom = reader.read();
            countChars++;
            if(pom == 35){
                do{
                    pom =reader.read();
                    countChars++;
                } while (pom != 10);
            }
        } while (pom < 48 || pom > 57);

        while (pom > 47 && pom < 58){
            stringBuilder.append((char) (pom));
            pom = reader.read();
            countChars++;
        }

        return Integer.parseInt(stringBuilder.toString());
    }

    public void saveImage(BufferedImage img, String path) {
        try {
            ImageIO.write(img, "jpg", new File(path));
        } catch (IOException ex) {
            System.out.println("Error has occured during file writing: " + ex.getMessage());
        }
    }

    public BufferedImage convertIconToImage(ImageIcon icon){
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return image;
    }
}

