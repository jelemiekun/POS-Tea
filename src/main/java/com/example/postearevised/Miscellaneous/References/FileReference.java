package com.example.postearevised.Miscellaneous.References;

import static com.example.postearevised.Miscellaneous.References.AccountReference.*;

public class FileReference {
    public static final String DIRECTORY_PATH;
    public static String DIRECTORY_PATH_ACCOUNTS;
    public static String DIRECTORY_PATH_CSV;
    public static String DIRECTORY_PATH_RECEIPT;
    public static String DIRECTORY_PATH_PRODUCT_IMAGES;
    public static String CSV_FILE_PATH_ACCOUNTS;
    public static String CSV_FILE_PATH_PRODUCTS;
    public static String CSV_FILE_PATH_ORDER_QUEUE;
    public static String CSV_FILE_PATH_ORDER_HISTORY;
    public static String TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY;
    public static String TEXT_PATH_ORDER_RECEIPT_STORE_COPY;
    public static String ERROR_LOG_PATH;

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            // windows os
            DIRECTORY_PATH = System.getenv("APPDATA") + "\\POS_Tea";
            CSV_FILE_PATH_ACCOUNTS = DIRECTORY_PATH + "\\accounts.csv";
        } else {
            // other os
            DIRECTORY_PATH = System.getProperty("user.home") + "/POS_Tea";
            CSV_FILE_PATH_ACCOUNTS = DIRECTORY_PATH + "/accounts.csv";


        }
    }

    public static void setPaths() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            DIRECTORY_PATH_ACCOUNTS = DIRECTORY_PATH + "\\accounts" + "\\" + accountContactReference;
            DIRECTORY_PATH_CSV = DIRECTORY_PATH_ACCOUNTS + "\\CSVs";
            DIRECTORY_PATH_RECEIPT = DIRECTORY_PATH_ACCOUNTS + "\\receipts";
            CSV_FILE_PATH_PRODUCTS = DIRECTORY_PATH_CSV + "\\products.csv";
            CSV_FILE_PATH_ORDER_QUEUE = DIRECTORY_PATH_CSV + "\\orderQueue.csv";
            CSV_FILE_PATH_ORDER_HISTORY = DIRECTORY_PATH_CSV + "\\orderHistory.csv";
            DIRECTORY_PATH_PRODUCT_IMAGES = DIRECTORY_PATH_ACCOUNTS + "\\product images";
            TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY = DIRECTORY_PATH_RECEIPT + "\\customerReceipt.txt";
            TEXT_PATH_ORDER_RECEIPT_STORE_COPY = DIRECTORY_PATH_RECEIPT + "\\storeReceipt.txt";
            ERROR_LOG_PATH = DIRECTORY_PATH_ACCOUNTS + "\\error_log.txt";
        } else {
            DIRECTORY_PATH_ACCOUNTS = DIRECTORY_PATH + "/accounts" + "/" + accountContactReference;
            DIRECTORY_PATH_CSV = DIRECTORY_PATH_ACCOUNTS + "/CSVs";
            DIRECTORY_PATH_RECEIPT = DIRECTORY_PATH_ACCOUNTS + "/receipts";
            CSV_FILE_PATH_PRODUCTS = DIRECTORY_PATH_CSV + "/products.csv";
            CSV_FILE_PATH_ORDER_QUEUE = DIRECTORY_PATH_CSV + "/orderQueue.csv";
            CSV_FILE_PATH_ORDER_HISTORY = DIRECTORY_PATH_CSV + "/orderHistory.csv";
            DIRECTORY_PATH_PRODUCT_IMAGES = DIRECTORY_PATH_ACCOUNTS + "/product images";
            TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY = DIRECTORY_PATH_RECEIPT + "/customerReceipt.txt";
            TEXT_PATH_ORDER_RECEIPT_STORE_COPY = DIRECTORY_PATH_RECEIPT + "/storeReceipt.txt";
            ERROR_LOG_PATH = DIRECTORY_PATH_ACCOUNTS + "/error_log.txt";
        }
    }
}
