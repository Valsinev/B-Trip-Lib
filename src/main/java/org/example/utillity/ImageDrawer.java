package org.example.utillity;

import org.example.constants.Config;
import org.example.engine.ImgData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageDrawer {

    public static BufferedImage drawDataOnBackgroundImg(List<ImgData> imgData, BufferedImage backgroundImage) {
        // Create a new BufferedImage with the same dimensions as the blankImage
        BufferedImage resultImage = new BufferedImage(
                backgroundImage.getWidth(),
                backgroundImage.getHeight(),
                backgroundImage.getType());

        Graphics g = resultImage.getGraphics();

        // Copy the content of the blankImage to the new image
        g.drawImage(backgroundImage, 0, 0, null);

        // Your drawing logic here
        g.setFont(new Font(Config.SHRIFT_TYPE, Config.FONT_STYLE, Config.SHRIFT_SIZE));
        g.setColor(Config.FONT_COLOR);

        for (ImgData data : imgData) {
            g.drawString(data.data(), data.xCord(), data.yCord());
        }

        g.dispose();

        return resultImage;
    }
}



