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
        promptContentText = "Press 'Log out' to log out, or press 'Stay' to remain logged in.";
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

    public static void setContinueLoginWithoutStayingIn() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Log-in without staying in?";
        promptContentText = "If you log in without staying logged in, you may need internet to log in again.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = CANCEL_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setInternetRequired() {
        setIsConfirmedToFalse();

        iconImageReference = INTERNET_CONNECTION_REQUIRED_ICON;
        promptHeaderText = "Internet Connection Required";
        promptContentText = "It seems that you're currently offline. Please connect to the internet to continue.";
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

    public static void setOrderSuccessful(String orderNumber, String customerName) {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Order Successful";
        promptContentText = "Order no. " + orderNumber +  " is ready to serve customer " + customerName;
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
        promptContentText = "ONCE DELETED THIS RECORD, THERE'S NO GOING BACK. PLEASE BE CERTAIN.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = DELETE_RED_BUTTON;
        btnCenterImageReference = DELETE_RED_BUTTON;
        btnRightImageReference = CANCEL_BUTTON;
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

    public static void setErrorDeleteProduct() {
        setIsConfirmedToFalse();

        iconImageReference = RED_BIN_ICON;
        promptHeaderText = "Product Deletion Error";
        promptContentText = "Failed to remove product(s) from the local database.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }

    public static void setErrorAddProduct() {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Product Adding Error";
        promptContentText = "Failed to add product to the local database.";
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

    public static void setErrorEditProduct() {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Product Editing Error";
        promptContentText = "Failed to update edited product to the local database.";
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

    public static void setExportSuccessful(String filePath) {
        setIsConfirmedToFalse();

        iconImageReference = CHECK_GREEN_ICON;
        promptHeaderText = "Export Successful";
        promptContentText = "The data export was successful! Your files has been saved to " + filePath;
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

    public static void setImportOtherError() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Error Encountered";
        promptContentText = "Error during execution. Check error details for more info.";
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

    public static void setErrorPrintingError() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Error Printing Failed";
        promptContentText = "An error occurred while attempting to print out the error message.";
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
        promptContentText = "Some cells in the imported CSV file contains blank data. How would you like to proceed?";
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
        promptContentText = "The selected file has an invalid data format and cannot be imported.";
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

    public static void setErrorAddingProductCategory() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Error Importing Product";
        promptContentText = "One or more products cannot be add due to error with the category cells. Continue adding products with valid category?";
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
        promptContentText = "The entered amount pay is ₱" + amount + "\". Are you sure you want to proceed with the payment?\"";
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
        promptContentText = "Payment successful. Your change amount is ₱" + change;
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

    public static void setErrorAddingOrderToCSV() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Error Writing Order to local database";
        promptContentText = "Failed to write the order details to the CSV file. Close other apps using file & retry.";
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
        promptContentText = "Failed to create CSV file. Check directory exists & permissions.";
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
        promptContentText = "Failed to read order history from CSV. Ensure correct format & accessibility.";
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