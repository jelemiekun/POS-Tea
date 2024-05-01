package com.example.postearevised.Miscellaneous.Others;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY;

public class InvoicePrinter {

    public static void printPOSReceipt(String content) {
        try {
            OutputStream outputStream = new FileOutputStream(TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY);

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                String[] lines = content.split("\n");
                int y = (int) pageFormat.getImageableY();
                Graphics2D g2d = (Graphics2D) graphics;
                for (String line : lines) {
                    System.out.println(line);
                    g2d.drawString(line, (float) pageFormat.getImageableX(), y);
                    y += g2d.getFontMetrics().getHeight();
                }

                return Printable.PAGE_EXISTS;
            });

            printerJob.print();

            outputStream.close();
        } catch (PrinterException | IOException ignored) {
        }
    }
}
