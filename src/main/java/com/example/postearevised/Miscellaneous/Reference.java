package com.example.postearevised.Miscellaneous;

import javafx.stage.Stage;

public class Reference {
    public static String accountReference = "admin";
    public static String passwordReference = "admin";
    public static String loginAccount = "";
    public static String loginPassword = "";
    public static String registerUsername = "";
    public static String registerEmail = "";
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
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil|co\\.uk|ph)$";
    public static final String REGEX_PHONE_NUMBER = "^09\\d{9}$";
    public static Thread countdown;

}
