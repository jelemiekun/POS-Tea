package com.example.postearevised.Miscellaneous;

import javafx.scene.image.Image;

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
        promptBtnRightVisible = true;
        promptBtnCenterVisible = false;
        btnLeftImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/yes.png"));
        btnRightImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/no.png"));
        btnCenterImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/no.png"));
        btnLeftBoolean = true;
        btnCenterBoolean = false;
        btnRightBoolean = false;
    }

    public static void setAccountCreatedConfirmation() {
        promptHeaderText = "Account Successfully Registered!";
        promptContentText = "Please log-in to continue.";
        promptBtnLeftVisible = false;
        promptBtnRightVisible = false;
        promptBtnCenterVisible = true;
        btnLeftImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png"));
        btnRightImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png"));
        btnCenterImageReference = new Image(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Buttons/okay green.png"));
        btnLeftBoolean = true;
        btnCenterBoolean = true;
        btnRightBoolean = true;
    }
}
