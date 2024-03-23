package com.example.postearevised.Miscellaneous.Others;

import javafx.scene.image.Image;

import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class NotificationContents {
    public static Image imageViewNotificationReference;
    public static String notificationHeaderReference;
    public static String notificationContentReference;

    public static void setLoadingTooLong() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Loading Taking Too Long";
        notificationContentReference = "Please wait or refer to the error log for details.";
    }

    public static void setReceiptPrinting() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Receipt Printing";
        notificationContentReference = "Printing receipt...";
    }

    public static void setDeleteHistorySuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Record Deleted";
        notificationContentReference = "Record Deleted Successfully";
    }

    public static void setEditProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product Edited";
        notificationContentReference = "Edit Successful";
    }

    public static void setAddProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product Added";
        notificationContentReference = "Addition Successful";
    }

    public static void setDeleteProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product Deleted";
        notificationContentReference = "Deletion Successful";
    }
}
