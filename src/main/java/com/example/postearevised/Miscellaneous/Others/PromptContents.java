package com.example.postearevised.Miscellaneous.Others;

import com.example.postearevised.Miscellaneous.References.GeneralReference;
import javafx.scene.image.Image;

import java.util.Objects;

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
        iconImageReference = QUESTION_MARK_ICON;
    }

    public static void setAccountCreatedConfirmation() {
        setIsConfirmedToFalse();

        promptHeaderText = "Account Successfully Registered!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_GREEN_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
        iconImageReference = CHECK_GREEN_BUTTON;
    }

    public static void setConfirmLogout() {
        setIsConfirmedToFalse();

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
        iconImageReference = QUESTION_MARK_ICON;
    }

    public static void setResetPasswordSuccessfully() {
        setIsConfirmedToFalse();

        promptHeaderText = "Password reset is successful!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_GREEN_BUTTON;
        btnCenterImageReference = OKAY_GREEN_BUTTON;
        btnRightImageReference = OKAY_GREEN_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
        iconImageReference = CHECK_GREEN_BUTTON;
    }

    public static void setInternetRequired() {
        setIsConfirmedToFalse();

        promptHeaderText = "Internet Connection Required";
        promptContentText = "It seems that you're currently offline.\nPlease connect to the internet to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = OKAY_BLACK_BUTTON;
        btnCenterImageReference = OKAY_BLACK_BUTTON;
        btnRightImageReference = OKAY_BLACK_BUTTON;
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
        iconImageReference = INTERNET_CONNECTION_REQUIRED_ICON;
    }
}
