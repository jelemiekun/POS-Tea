package com.example.postearevised.Miscellaneous.Others;

import javafx.scene.image.Image;

import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class NotificationContents {
    public static Image imageViewNotificationReference;
    public static String notificationHeaderReference;
    public static String notificationContentReference;

    public static void setReceiptPrinting() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Receipt Printing";
        notificationContentReference = "Printing receipt...";
    }
}
