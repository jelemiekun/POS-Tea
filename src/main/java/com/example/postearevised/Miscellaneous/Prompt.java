package com.example.postearevised.Miscellaneous;

import javafx.scene.image.Image;

import java.util.Objects;

public class Prompt {
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

    public static void setGoBackConfirmation() {
        promptHeaderText = "Are you sure you want to go back?";
        promptContentText = "Any unsaved changes will be lost!";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/yes.png")));
        btnCenterImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/no.png")));
        btnRightImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/no.png")));
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setAccountCreatedConfirmation() {
        promptHeaderText = "Account Successfully Registered!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnCenterImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnRightImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }

    public static void setConfirmLogout() {
        promptHeaderText = "Are you sure you want to log out?";
        promptContentText = "Press 'Logout' to log out, or press 'Stay' to remain logged in.";
        promptBtnLeftVisible = true;
        promptBtnCenterVisible = false;
        promptBtnRightVisible = true;
        btnLeftImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/Logout/logout.png")));
        btnCenterImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/Logout/logout.png")));
        btnRightImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/Logout/stay.png")));
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setResetPasswordSuccessfully() {
        promptHeaderText = "Password reset is successful!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnCenterImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnRightImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }

    public static void setInternetRequired() {
        promptHeaderText = "Internet Connection Required";
        promptContentText = "It seems that you're currently offline.\nPlease connect to the internet to continue.";
        promptBtnLeftVisible = false;
        promptBtnCenterVisible = true;
        promptBtnRightVisible = false;
        btnLeftImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnCenterImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnRightImageReference = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png")));
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }
}
