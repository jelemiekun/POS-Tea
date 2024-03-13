package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.Product;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GeneralReference {
    public static Stage mainStage;
    public static Stage loginRegisterStage;
    public static Stage loginFromMainSceneStage;
    public static final int INPUT_LIMIT_TO_ELEVEN = 11;
    public static final int OTP_LENGTH = 4;
    public static final int PASSWORD_LIMIT = 128;
    public static final int NAME_LIMIT = 16;
    public static final int ONE_SECOND = 1000;
    public static final int FINISH_COUNTDOWN = 1;
    public static final Color dropShadowColor = Color.rgb(150,150,150);
    public static int countdownTimer; //ms
    public static int[] screenResolution = new int[2];
    public static Thread countdown;
    public static int deleteProductSelectedCounter = 0;
    public static boolean trueAddProductFalseDeleteProduct = false;
    public static Product editOrShowSelectedProduct;
}
