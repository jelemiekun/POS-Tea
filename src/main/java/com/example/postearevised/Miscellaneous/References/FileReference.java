package com.example.postearevised.Miscellaneous.References;

public class FileReference {
    public static final String DIRECTORY_PATH;
    public static final String PRODUCTS_CSV_FILE_PATH;
    public static final String ORDER_HISTORY_CSV_FILE_PATH;
    public static final String PRODUCT_IMAGES_PATH;
    public static final String ORDER_RECEIPT_CUSTOMER_COPY_PATH;
    public static final String ORDER_RECEIPT_STORE_COPY_PATH;
    public static final String ERROR_LOG_PATH;

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            // windows os
            DIRECTORY_PATH = System.getenv("APPDATA") + "\\POS_Tea";
            PRODUCTS_CSV_FILE_PATH = DIRECTORY_PATH + "\\products.csv";
            ORDER_HISTORY_CSV_FILE_PATH = DIRECTORY_PATH + "\\orderHistory.csv";
            PRODUCT_IMAGES_PATH = DIRECTORY_PATH + "\\product images";
            ORDER_RECEIPT_CUSTOMER_COPY_PATH = DIRECTORY_PATH + "\\customerReceipt.txt";
            ORDER_RECEIPT_STORE_COPY_PATH = DIRECTORY_PATH + "\\storeReceipt.txt";
            ERROR_LOG_PATH = DIRECTORY_PATH + "\\error_log.txt";
        } else {
            // other os
            DIRECTORY_PATH = System.getProperty("user.home") + "/POS_Tea";
            PRODUCTS_CSV_FILE_PATH = DIRECTORY_PATH + "/products.csv";
            ORDER_HISTORY_CSV_FILE_PATH = DIRECTORY_PATH + "/orderHistory.csv";
            PRODUCT_IMAGES_PATH = DIRECTORY_PATH + "/product images";
            ORDER_RECEIPT_CUSTOMER_COPY_PATH = DIRECTORY_PATH + "/customerReceipt.txt";
            ORDER_RECEIPT_STORE_COPY_PATH = DIRECTORY_PATH + "/storeReceipt.txt";
            ERROR_LOG_PATH = DIRECTORY_PATH + "/error_log.txt";
        }
    }
}
