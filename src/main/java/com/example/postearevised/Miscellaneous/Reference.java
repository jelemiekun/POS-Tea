package com.example.postearevised.Miscellaneous;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class Reference {
    public static String accountReference = "admin";
    public static String passwordReference = "admin";
    public static String loginAccount = "";
    public static String loginPassword = "";
    public static String registerName = "";
    public static String registerEmailOrPhoneNumber = "";
    public static String registerNewPassword = "";
    public static String registerConfirmNewPassword = "";
    public static String forgotPassAccount = "";
    public static String forgotPassGeneratedOTP = "";
    public static String forgotPassOTP = "";
    public static String forgotPassNewPassword = "";
    public static String forgotPassConfirmNewPassword = "";
    /**
     * Remove nasa up pag may database na
     */
    public static Stage mainStage;
    public static Stage loginRegisterStage;
    public static Stage loginFromMainSceneStage;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil|co\\.uk|ph)$";
    public static final String REGEX_PHONE_NUMBER = "^09\\d{9}$";
    public static final String REGEX_NAME = "^[a-zA-Z ]+$";
    public static final String REGEX_FIRST_SIX_ARE_NUMBERS = "^\\d{6}.*";
    public static final int INPUT_LIMIT_TO_ELEVEN = 11;
    public static final int OTP_LENGTH = 4;
    public static final String REGEX_DIGITS_ONLY = "\\d*";
    public static int countdownTimer; //ms
    public static int[] screenResolution = new int[2];
    public static final int PASSWORD_LIMIT = 128;
    public static final int NAME_LIMIT = 16;
    public static final int ONE_SECOND = 1000;
    public static final int FINISH_COUNTDOWN = 1;
    public static final Color dropShadowColor = Color.rgb(150,150,150);
    public static Thread countdown;

    /**
     * Images
     */

    public static final Image mainMenuIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/Menu button.png")));
    public static final Image mainMenuSelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/Menu button selected.png")));
    public static final Image mainDashboardIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/dashboard.png")));
    public static final Image mainDashboardSelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/dashboard selected.png")));
    public static final Image mainOrderListIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/order queue.png")));
    public static final Image mainOrderListSelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/order queue selected.png")));
    public static final Image mainOrderHistoryIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/order history.png")));
    public static final Image mainOrderHistorySelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/order history selected.png")));
    public static final Image mainSettingsIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/settings.png")));
    public static final Image mainSettingsSelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/settings selected.png")));
    public static final Image mainLogoutIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/logout button.png")));
    public static final Image mainLogoutSelectedIcon = new Image(Objects.requireNonNull(Reference.class.getResourceAsStream("/com/example/postearevised/Medias/Icons/Left Panel/logout button selected.png")));
}
