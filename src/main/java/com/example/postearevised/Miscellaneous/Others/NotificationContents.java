package com.example.postearevised.Miscellaneous.Others;

import javafx.scene.image.Image;

import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class NotificationContents {
    public static Image imageViewNotificationReference;
    public static String notificationHeaderReference;
    public static String notificationContentReference;

    //TODO timer loading
    public static void setLoadingTooLong() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Loading taking too long";
        notificationContentReference = "Please wait or refer to the error log for details.";
    }

    public static void setOrderAddedToOrderQueue() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order added";
        notificationContentReference = "Order added to order queue";
    }

    public static void setDeleteHistorySuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Record deleted";
        notificationContentReference = "Record Deleted Successfully";
    }

    public static void setEditProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product edited";
        notificationContentReference = "Edit Successful";
    }

    public static void setAddProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product added";
        notificationContentReference = "Addition Successful";
    }

    public static void setDeleteProductSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product deleted";
        notificationContentReference = "Deletion Successful";
    }

    public static void setOrderCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order cancelled";
        notificationContentReference = "Your order has been cancelled.";
    }

    public static void setOrderCompleted() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Order completed";
        notificationContentReference = "Your order has been successfully completed.";
    }

    public static void setOrderQueueImported() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Imported order detected";
        notificationContentReference = "An imported order has been detected in the order queue.";
    }

    public static void setExportMenuSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu export successful";
        notificationContentReference = "The menu has been successfully exported.";
    }

    public static void setImportMenuSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu import successful";
        notificationContentReference = "The menu has been successfully imported.";
    }

    public static void setImportMenuUnsuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Menu import failed";
        notificationContentReference = "There was an error while importing the menu.";
    }

    public static void setImportExportCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Operation cancelled";
        notificationContentReference = "The menu import/export operation was canceled by the user.";
    }

    public static void setAccountUsersSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Users updated successfully";
        notificationContentReference = "The account users have been successfully updated.";
    }

    public static void setAccountDetailsEditSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account edit successful";
        notificationContentReference = "The account details have been successfully updated.";
    }

    public static void setRecoveryQuestionEditSuccessful() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account edit successful";
        notificationContentReference = "The recovery questions have been successfully updated.";
    }

    public static void setAccountUsersUpdateFailedCSV() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account users update failed";
        notificationContentReference = "There was an error while updating the account users.";
    }

    public static void setAccountUsersUpdateFailedCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account users update failed";
        notificationContentReference = "Operation was cancelled by the user.";
    }

    public static void setAccountDetailsEditFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account edit failed";
        notificationContentReference = "There was an error while updating the account details.";
    }

    public static void setAccountDetailsEditFailedMaxLimitReached() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account edit failed";
        notificationContentReference = "Maximum attempts limit reached.";
    }

    public static void setProductDeletionFailedMaxLimitReached() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product deletion failed";
        notificationContentReference = "Maximum attempts limit reached.";
    }

    public static void setAccountDetailsEditFailedCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account edit failed";
        notificationContentReference = "Operation was cancelled by the user.";
    }

    public static void setProductDeletionCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Product deletion cancelled";
        notificationContentReference = "Operation was cancelled by the user.";
    }

    public static void setRecoveryQuestionEditFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Recovery question edit failed";
        notificationContentReference = "There was an error while updating the recovery questions.";
    }

    public static void setAccountDeletionCancelled() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Operation cancelled";
        notificationContentReference = "Account deletion was unsuccessful.";
    }

    public static void setSwitchUserSuccess() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Switched user";
        notificationContentReference = "User " + usersNames.get(userIndex) + " is now operating the system.";
    }

    public static void setSwitchUserFailed() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Switching user failed";
        notificationContentReference = "Switching to user " + usersNames.get(userIndex) + " failed.";
    }

    public static void setWelcomeUser() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Welcome back!";
        notificationContentReference = "Welcome back, user " + usersNames.get(userIndex) + "!";
    }

    public static void setWelcomeAdmin() {
        imageViewNotificationReference = NOTIFICATION_I_WHITE;
        notificationHeaderReference = "Account setup!";
        notificationContentReference = "Let's get started by touring you through the system.";
    }
}
