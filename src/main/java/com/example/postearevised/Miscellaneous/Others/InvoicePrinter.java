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


        content = Double.valueOf(content.size());

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }
}

public PageFormat getPageFormat(PrinterJob pj) {

    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();

    double bodyHeight = bHeight;
    double headerHeight = 5.0;
    double footerHeight = 5.0;
    double width = cm_to_pp(8);
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));

    pf.setOrientation(PageFormat.PORTRAIT);
    pf.setPaper(paper);

    return pf;
}