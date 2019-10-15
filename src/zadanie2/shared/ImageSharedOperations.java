package zadanie2.shared;

import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImageSharedOperations {

    public static BufferedImage loadImage(String path) {
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

            switch (format){
                //3 in ASCII
                case 51:
                    return readPPM3(image, reader, width, height, maxValue);
                //6 in ASCII
                case 54:
                    readPPM6(image, reader, width, height, maxValue);
                    break;
                default:
                    break;
            }

        } catch (IOException ex) {
            System.out.println("Error has occured during file reading: " + ex.getMessage());
        }
        return image;
    }

    private static void readPPM6(BufferedImage image, BufferedReader reader, int width, int height, int maxValue) {

    }

    private static BufferedImage readPPM3(BufferedImage image, BufferedReader reader, int width, int height, int maxValue) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] buff = new char[512];
        int r = -1, g = -1, b = -1;
        int w=0, h=0;
        try {
            int buffer;
            do {
                buffer = reader.read(buff);
                for (char a: buff) {
                    if (a == '#') {
                        if (buff[a + 1] != '\n') {
                            buff[a + 1] = '#';
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
//                            else if(a < 48 || a > 57){
//                                stringBuilder.setLength(0);
//                            }else if(a < 58){
//                                stringBuilder.append(a);
//                            }
                }
            } while (buffer!=-1);
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static int readValue(StringBuilder stringBuilder, BufferedReader reader) throws IOException {
        int pom;
        do {
            pom = reader.read();
            if(pom == 35){
                reader.readLine();
            }
        } while (pom < 48 || pom > 57);

        while (pom > 47 && pom < 58){
            stringBuilder.append((char)(pom));
            pom=reader.read();
        }

        return Integer.parseInt(stringBuilder.toString());
    }

    public static void saveImage(BufferedImage img, String path) {
        try {
            ImageIO.write(img, "jpg", new File(path));
        } catch (IOException ex) {
            System.out.println("Error has occured during file writing: " + ex.getMessage());
        }
    }
}

