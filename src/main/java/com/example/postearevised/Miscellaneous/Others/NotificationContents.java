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

    public static void setOrderAddedToOrderQueue() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order added";
        notificationContentReference = "Order added to order queue";
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

    public static void setOrderCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order cancelled";
        notificationContentReference = "Your order has been cancelled.";
    }

    public static void setOrderCompleted() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order Completed";
        notificationContentReference = "Your order has been successfully completed.";
    }

    public static void setOrderQueueImported() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Imported Order Detected";
        notificationContentReference = "An imported order has been detected in the order queue.";
    }

    public static void setExportMenuSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu Export Successful";
        notificationContentReference = "The menu has been successfully exported.";
    }

    public static void setImportMenuSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu Import Successful";
        notificationContentReference = "The menu has been successfully imported.";
    }

    public static void setImportMenuUnsuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu Import Failed";
        notificationContentReference = "There was an error while importing the menu.";
    }

    public static void setImportExportCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Operation Canceled";
        notificationContentReference = "The menu import/export operation was canceled by the user.";
    }

    public static void setAccountUsersSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account Users Updated Successfully";
        notificationContentReference = "The account users have been successfully updated.";
    }

    public static void setAccountDetailsEditSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account Edit Successful";
        notificationContentReference = "The account details have been successfully updated.";
    }

    public static void setRecoveryQuestionEditSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account Edit Successful";
        notificationContentReference = "The recovery questions have been successfully updated.";
    }

    public static void setAccountUsersUpdateFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account Users Update Failed";
        notificationContentReference = "There was an error while updating the account users.";
    }

    public static void setAccountDetailsEditFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account Edit Failed";
        notificationContentReference = "There was an error while updating the account details.";
    }

    public static void setRecoveryQuestionEditFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Recovery Question Edit Failed";
        notificationContentReference = "There was an error while updating the recovery questions.";
    }

}
