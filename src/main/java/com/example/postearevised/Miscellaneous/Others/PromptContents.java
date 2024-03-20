package com.example.postearevised.Miscellaneous.Others;

import javafx.scene.image.Image;

import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class PromptContents {
    public static boolean isConfirmed = false;

    public static String promptHeaderText;
    public static String promptContentText;
    public static boolean promptBtnLeftVisible;
    public static boolean promptBtnCenterVisible;
    public static boolean promptBtnRightVisible;
    public static Image btnLeftImageReference;
    public static Image btnCenterImageReference;
    public static Image btnRightImageReference;
    public static boolean btnLeftBoolean;
    public static boolean btnCenterBoolean;
    public static boolean btnRightBoolean;
    public static Image iconImageReference;

    public static void setIsConfirmedToFalse() {
        isConfirmed = false;
    }

    public static void setGoBackConfirmation() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Are you sure you want to go back?";
        promptContentText = "Any unsaved changes will be lost!";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = YES_RED_BUTTON;
        btnCenterImageReference = NO_BLACK_BUTTON;
        btnRightImageReference = NO_BLACK_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setAccountCreatedConfirmation() {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Account Successfully Registered!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_GREEN_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setConfirmLogout() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Are you sure you want to log out?";
        promptContentText = "Press 'Log out' to log out, or \npress 'Stay' to remain logged in.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = LOG_OUT_BUTTON;
        btnCenterImageReference = LOG_OUT_BUTTON;
        btnRightImageReference = STAY_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setResetPasswordSuccessfully() {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Password reset is successful!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_GREEN_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setInternetRequired() {
        setIsConfirmedToFalse();

        iconImageReference = INTERNET_CONNECTION_REQUIRED_ICON;
        promptHeaderText = "Internet Connection Required";
        promptContentText = "It seems that you're currently offline.\nPlease connect to the internet to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setOrderSuccessful(String orderNumber) {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Order Successful";
        promptContentText = "Order no. " + orderNumber + " is ready to serve";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setDeleteRecord() {
        setIsConfirmedToFalse();

        iconImageReference = RED_BIN_ICON;
        promptHeaderText = "DELETE RECORD";
        promptContentText = "ONCE DELETED THIS RECORD, THERE'S NO\nGOING BACK. PLEASE BE CERTAIN.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = DELETE_RED;
        btnCenterImageReference = DELETE_RED;
        btnRightImageReference = DELETE_RED;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setDeleteProduct() {
        setIsConfirmedToFalse();

        iconImageReference = RED_BIN_ICON;
        promptHeaderText = "Delete Product";
        promptContentText = "Do you wish to delete this product(s)?";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = YES_RED_BUTTON;
        btnCenterImageReference = NO_BLACK_BUTTON;
        btnRightImageReference = NO_BLACK_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setExportSuccessful(String filePath) {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Export Successful";
        promptContentText = "The data export was successful! Your files has been\nsaved to " + filePath;
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setImportSuccessful() {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Successful";
        promptContentText = "Data has been successfully imported to menu.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setBlankCellsDetected() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Blank Cells Detected";
        promptContentText = "Some cells in the imported CSV file contains\nblank data. How would you like to proceed?";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = ADD_ANYWAY_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = CANCEL_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setInvalidFileFormat() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Invalid File Format";
        promptContentText = "The selected file has an invalid\ndata format and cannot be imported.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorAddingProduct() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Error Importing Product";
        promptContentText = "One or more products cannot be add due to error\nwith the category cells.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = CONTINUE_BUTTON;
        btnCenterImageReference = CANCEL_BUTTON;
        btnRightImageReference = CANCEL_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setProceedPayment(String amount) {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Proceed Payment";
        promptContentText = "The entered amount pay is \n₱" + amount + "\". \nAre you sure you want to proceed with the payment?\"\n";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = YES_BLACK_BUTTON;
        btnCenterImageReference = NO_BLACK_BUTTON;
        btnRightImageReference = NO_BLACK_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setPaymentSuccessful(String change) {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Payment successful";
        promptContentText = "Payment successful.\nYour change amount is ₱" + change;
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setFileInUse() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "File In Use";
        promptContentText = "The process cannot access the file because it is being used by another process. Close other apps using file & retry.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setErrorCreatingCSVFile() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "CSV File creation error";
        promptContentText = "Failed to create CSV file.\nCheck directory exists & permissions.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setErrorReadingOrderHistoryFromCSV() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Order history read error";
        promptContentText = "Failed to read order history from CSV.\nEnsure correct format & accessibility.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }
}