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

    public static void setOutOfMemoryError() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Out Of Memory";
        promptContentText = "Our application has encountered a memory error. Please close other applications to free up memory, and then restart the application.";
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

    public static void setForgotPassword() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Forgot password?";
        promptContentText = "Click yes if you wish to reset your password.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = YES_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
    }

    public static void setGoBackConfirmation() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Are you sure you want to go back?";
        promptContentText = "Any unsaved changes will be lost!";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = NO_BLACK_BUTTON;
        btnRightImageReference = YES_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
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

    public static void setUserConfirmPassword() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Confirm Password";
        promptContentText = "Click 'yes' to confirm registration of password for the admin user.\nNOTE: THIS CANNOT BE CHANGED.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_GREEN_BUTTON;
        btnRightImageReference = YES_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }

    public static void setErrorAccountCreation() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Error creating account";
        promptContentText = "Creation failed. CSV in use or try again later.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorResettingPassword() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Error resetting password";
        promptContentText = "Operation failed. CSV in use or try again later.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorFailedToUpdateAccountToCSV() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Edit Failed!";
        promptContentText = "Operation failed. CSV in use or try again later.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorFailedToUpdateAccountUserCancelled() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Edit Failed!";
        promptContentText = "Operation was cancelled by the user.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorChangingPasswordMaximumAttemptReached() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Maximum attempt reached!";
        promptContentText = "Maximum attempt limit reached. Please try again.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
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

    public static void setOrderCancellation() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Cancel order?";
        promptContentText = "Are you sure you want to cancel this order?\nThis action cannot be undone.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = NO_BLACK_BUTTON;
        btnRightImageReference = YES_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
    }

    public static void setAddEditProductBlankFields() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Incomplete Information";
        promptContentText = "Cannot proceed because one or more fields are blank.";
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

    public static void setAddEditProductNameBlank() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Product name empty!";
        promptContentText = "Please input the name of your product.";
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

    public static void setAddProductDuplicateError() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Duplicate Product Found";
        promptContentText = "Cannot add product because a product with the same name and category already exists.";
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

    public static void setEditProductDuplicateError() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Duplicate Product Found";
        promptContentText = "Cannot edit product because a product with the same name and category already exists.";
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
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = YES_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
    }

    public static void setErrorDeleteRecord() {
        setIsConfirmedToFalse();

        iconImageReference = RED_BIN_ICON;
        promptHeaderText = "Record Deletion Error";
        promptContentText = "Failed to delete record(s) from the local database.";
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

    public static void setErrorAddOrder() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Order Adding Error";
        promptContentText = "Failed to add order.";
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

    public static void setErrorAddProduct() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
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

        iconImageReference = EX_RED_ICON;
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

    public static void setImportingProductsDuplicateFound() {
        setIsConfirmedToFalse();

        iconImageReference = QUESTION_MARK_ICON;
        promptHeaderText = "Duplicate Products Found";
        promptContentText = "Some of the products you are trying to import already exist in the list. Do you want to add the remaining non-existing products?";
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
        promptContentText = "The entered amount pay is ₱" + amount + ". Are you sure you want to proceed with the payment?\"";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = YES_BLACK_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
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

    public static void setErrorReadingAccountFromCSV() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Account CSV read error";
        promptContentText = "Failed to read account from CSV. Ensure correct format & accessibility.";
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

    public static void setErrorHidingAccountCSV() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Failed to hide account CSV";
        promptContentText = "Failed to hide account CSV. Ensure correct format & accessibility.";
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

    public static void setErrorSettingStyles() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Failed to change system style.";
        promptContentText = "Failed to update the new selected display style to the CSV file. Close other apps using file & retry.";
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

    public static void setErrorSettingStylesUnknownColor() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Failed to change system style.";
        promptContentText = "Failed to update the new selected display style because system receive or detected an unknown color. Please try again.";
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

    public static void setSystemManualPDFNotFound() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "File not found!";
        promptContentText = "There was an error while opening System Manual.pdf\nIt might have been deleted or removed from the original file path.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = DELETE_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setSystemManualPDFErrorReading() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Cannot read file!";
        promptContentText = "There was an error while opening System Manual.pdf\nIt might have been deleted or removed from the original file path.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = DELETE_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = true;
        btnRightBoolean = false;
    }

    public static void setErrorCreatingAccount() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Failed to create account.";
        promptContentText = "Failed to create an account. Please try again later.";
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

    public static void setErrorAccountDeletion() {
        setIsConfirmedToFalse();

        iconImageReference = EX_RED_ICON;
        promptHeaderText = "Failed to delete account.";
        promptContentText = "Failed to delete the account. Please try again later.";
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

    public static void setDeleteAccount1() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Delete account?";
        promptContentText = "Do you want to delete your account?";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = YES_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
    }

    public static void setDeleteAccount2() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Confirm Account Deletion";
        promptContentText = "This action cannot be undone. All data associated with this account will be permanently deleted. Continue deleting your account?";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = CANCEL_CAPS_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = DELETE_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = true;
    }

    public static void setDeleteAccount3AccountDeleted() {
        setIsConfirmedToFalse();

        iconImageReference = TRIANGLE_RED_ICON;
        promptHeaderText = "Account Successfully Deleted.";
        promptContentText = "Click 'OKAY' to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = NO_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = DELETE_RED_BUTTON;
        btnLeftBoolean = false;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }
}