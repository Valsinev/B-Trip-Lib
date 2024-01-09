package org.example.engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class PrintableImage implements Printable {

    private final BufferedImage image;

    public PrintableImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.drawImage(image, 0, 0, (int) pf.getImageableWidth(), (int) pf.getImageableHeight(), null);

        return PAGE_EXISTS;
    }
}

