package com.example.postearevised.Miscellaneous.Others;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import java.awt.*;
import java.awt.print.*;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class InvoicePrinter {

    public static void printPOSReceipt(String content) {
        PrinterJob printerJob = getPrinterJob(content);
        Dialog dialog = new Dialog((Frame) null, "Print", false);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialog.dispose();
            }
        });
        dialog.setAlwaysOnTop(true);

        if (printerJob.printDialog()) {
            try {
                dialog.setVisible(true);
                printerJob.print();
            } catch (PrinterException e) {
                errorMessage = "Printer error: " + e.getMessage();
                logError(true);
                System.err.println(e.getMessage());
            } finally {
                dialog.dispose();
            }
        } else {
            dialog.dispose();
            errorMessage = "Printer error: printing cancelled.";
            logError(true);
            System.err.println("Printer error: printing cancelled.");
        }
    }

    private static PrinterJob getPrinterJob(String content) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Paper paper = new Paper();
            double width = 3.125 * 72;
            double height = pageFormat.getImageableHeight();
            paper.setSize(width, height);
            pageFormat.setPaper(paper);

            String[] lines = content.split("\n");
            int y = (int) pageFormat.getImageableY();
            Graphics2D g2d = (Graphics2D) graphics;
            for (String line : lines) {
                g2d.drawString(line, (float) pageFormat.getImageableX(), y);
                y += g2d.getFontMetrics().getHeight();
            }

            return Printable.PAGE_EXISTS;
        });
        return printerJob;
    }
}
