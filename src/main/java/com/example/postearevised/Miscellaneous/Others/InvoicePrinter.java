package com.example.postearevised.Miscellaneous.Others;

import javax.print.*;
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
        Dialog dialog = getDialog();
        dialog.setAlwaysOnTop(true);

        // Hide the dialog
        dialog.setVisible(false);

        // Set print attributes
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM));
        attributes.add(OrientationRequested.PORTRAIT);
        attributes.add(new Copies(1));

        // Get the default print service
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        if (printService != null) {
            // Create a new print job
            DocPrintJob printJob = printService.createPrintJob();

            try {
                // Create a printable object
                Printable printable = (graphics, pageFormat, pageIndex) -> {
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
                };

                // Create a Doc object
                Doc doc = new SimpleDoc(printable, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);

                // Print the document with specified attributes
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

        dialog.dispose();
    }

    private static Dialog getDialog() {
        Dialog dialog = new Dialog((Frame) null, "Print", false);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialog.dispose();
            }
        });

        return dialog;
    }
}
