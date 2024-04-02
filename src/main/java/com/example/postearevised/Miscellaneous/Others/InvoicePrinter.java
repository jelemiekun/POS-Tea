package com.example.postearevised.Miscellaneous.Others;

import java.awt.*;
import java.awt.print.*;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class InvoicePrinter {

    public static void printPOSReceipt(String content) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();

        Paper paper = pageFormat.getPaper();
        paper.setSize(3.125 * 72, paper.getHeight());
        pageFormat.setPaper(paper);

        printerJob.setPrintable((graphics, pageFormat1, pageIndex) -> {
            if (pageIndex != 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat1.getImageableX(), pageFormat1.getImageableY());

            Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
            graphics.setFont(font);

            String[] lines = content.split("\n");
            int y = 0;
            for (String line : lines) {
                graphics.drawString(line, 0, y);
                y += graphics.getFontMetrics().getHeight();
            }

            return Printable.PAGE_EXISTS;
        }, pageFormat);

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                errorMessage = e.getMessage();
                logError(true);
            }
        }
    }
}
