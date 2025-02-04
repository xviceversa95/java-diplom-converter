package ru.netology.graphics.image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    int maxWidth;
    int maxHeight;
    double maxRatio;
    TextColorSchema schema = new WhiteColorSchema();


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        int userWidth = img.getWidth();
        int userHeight = img.getHeight();

        System.out.println("Исходная ширина:" + userWidth);
        System.out.println("Исходная высота" + userHeight);


        double userRatio = (double)Math.max(userHeight, userWidth)/Math.min(userHeight,userWidth);
        if (userRatio > maxRatio) {
            throw new BadImageSizeException(userRatio, maxRatio);
        }


        int newWidth;
        int newHeight;

        if (userWidth >= userHeight) {
            newWidth = (int)(userWidth/(checkWidth(userWidth)));
            newHeight = (int)(userHeight/(checkWidth(userWidth)));
        } else {
            newWidth = (int)(userWidth/(checkHeight(userHeight)));
            newHeight = (int)(userHeight/(checkHeight(userHeight)));
        }
        System.out.println("Новая ширина:"+ newWidth);
        System.out.println("Новая высота:" + newHeight);




        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();


        int[] pixels = new int [3];
        char [][] charmap = new char[newHeight][newWidth];

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, pixels)[0];
                char c = schema.convert(color);
                charmap[h][w] = c;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (char[] raw : charmap) {
            sb.append('\n');
            for (char element : raw) {
                sb.append(element);
                sb.append(element);
            }
        }
        String result = sb.toString();
        return result;
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    /**
     Проверяем ширину изображения
     */

    public double checkWidth(int width) {
        if (width >= maxWidth) {
            double kf = (double) width/maxWidth;
            return kf;
        } else {
            return 1;
        }
    }

    /**
     Проверяем высоту изображения
    */
    public double checkHeight(int height) {
        if (height >= maxHeight) {
            double kf = (double)height/maxHeight;
            return kf;
        } else {
            return 1;
        }
    }
}
