package com.example.postearevised.Miscellaneous.Others;

import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterState;
import java.awt.*;
import java.awt.print.*;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class InvoicePrinter {

    public static void printPOSReceipt(String content) {
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM));
        attributes.add(OrientationRequested.PORTRAIT);
        attributes.add(new Copies(1));

        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        if (printService != null) {
            DocPrintJob printJob = printService.createPrintJob();
            try {
                Doc doc = getDoc(content);
                printJob.print(doc, attributes);
            } catch (PrintException e) {
                errorMessage = "Printer error: " + e.getMessage();
                logError(true);
                System.err.println("Printing error: " + e.getMessage());
            }
        } else {
            errorMessage = "No printer found!";
            logError(true);
            System.err.println("No printer found!");
        }
    }

    private static Doc getDoc(String content) {
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Paper paper = new Paper();
            double width = 3.125 * 72;
            double height = pageFormat.getImageableHeight();
            paper.setSize(width, height);
            pageFormat.setPaper(paper);

            String[] lines = content.split("(?<=\\n)");
            int y = (int) pageFormat.getImageableY();
            Graphics2D g2d = (Graphics2D) graphics;

            Font font = new Font("Consolas", Font.PLAIN, 9);
            g2d.setFont(font);

            for (String line : lines) {
                g2d.drawString(line, (float) pageFormat.getImageableX(), y);
                y += g2d.getFontMetrics().getHeight();
            }

            return Printable.PAGE_EXISTS;
        };

        return new SimpleDoc(printable, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
    }
}
