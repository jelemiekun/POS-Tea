package com.example.postearevised.Miscellaneous.References;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class GeneralReference {
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
}
