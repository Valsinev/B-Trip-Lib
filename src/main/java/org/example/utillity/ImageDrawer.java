package org.example.utillity;

import org.example.engine.ImgData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageDrawer {

    public static BufferedImage drawDataOnBackgroundImg(List<ImgData> imgData, BufferedImage backgroundImage, Font font, Color fontColor) {
        // Create a new BufferedImage with the same dimensions as the blankImage
        BufferedImage resultImage = new BufferedImage(
                backgroundImage.getWidth(),
                backgroundImage.getHeight(),
                backgroundImage.getType());

        Graphics2D g = resultImage.createGraphics();

        try {
            // Copy the content of the blankImage to the new image
            g.drawImage(backgroundImage, 0, 0, null);

            // Your drawing logic here
            g.setFont(font);
            g.setColor(fontColor);

            for (ImgData data : imgData) {
                g.drawString(data.data(), data.xCord(), data.yCord());
            }
        } finally {
            // Dispose of the Graphics2D object
            g.dispose();
        }

        return resultImage;
    }
}



