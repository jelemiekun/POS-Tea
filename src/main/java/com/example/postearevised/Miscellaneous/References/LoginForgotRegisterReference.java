package com.example.postearevised.Miscellaneous.References;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginForgotRegisterReference {
    public static String loginAccount = "";
    public static String loginPassword = "";
    public static String registerGivenName = "";
    public static String registerMiddleName = "";
    public static String registerSurName = "";
    public static String registerEmailOrPhoneNumber = "";
    public static String registerNewPassword = "";
    public static String registerConfirmNewPassword = "";
    public static String forgotPassAccount = "";
    public static String forgotPassGeneratedOTP = "";
    public static String forgotPassOTP = "";
    public static String forgotPassNewPassword = "";
    public static String forgotPassConfirmNewPassword = "";

    public static ObservableList<String> recoveryQuestionsObservableList = FXCollections.observableArrayList(
            "What is your mother's maiden name?",
            "What was the name of your first pet?",
            "In what city were you born?",
            "What is the name of your favorite childhood teacher?",
            "What is the name of your oldest cousin?",
            "What was the make and model of your first car?",
            "What is your favorite movie?",
            "What is the name of your favorite fictional character?",
            "What is the name of the street you grew up on?",
            "What was the name of your elementary school?",
            "What is your favorite book?",
            "What is the name of your best childhood friend?",
            "What is your favorite food?",
            "What is the name of your favorite sports team?",
            "What was the first concert you attended?",
            "What is the name of your favorite musician or band?",
            "What is the name of the company where you had your first job?",
            "What is your favorite vacation destination?",
            "What is the name of your favorite childhood toy?",
            "What is the name of your favorite historical figure?"
    );
}
