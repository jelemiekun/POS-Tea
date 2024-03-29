package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralReference {
    public static Stage mainStage;
    public static Stage loginRegisterStage;
    public static Stage loginFromMainSceneStage;
    public static Stage productStage;
    public static final String SAMPLE_VIDEO_PATH = "/com/example/postearevised/Medias/Video/Sample Video.mp4";
    public static final ObservableList<String> modeOfPaymentChoices = FXCollections.observableArrayList("Cash", "GCash", "Maya", "Others");
    public static final int MAX_LIMIT_BEFORE_ASKING_TO_RESET_PASSWORD = 5;
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
    public static Product editOrShowSelectedProduct;
    public static boolean isAddingProductsFromImport = true;
    public static DropShadow setDropShadowRightDown() {

        DropShadow labelDropShadowRightDown = new DropShadow();
        labelDropShadowRightDown.setRadius(20);
        labelDropShadowRightDown.setOffsetX(0);
        labelDropShadowRightDown.setOffsetY(0);
        labelDropShadowRightDown.setColor(Color.rgb(0, 0, 0, 1));

        return labelDropShadowRightDown;
    }

    public static LocalDateTime localDateTime;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - hh:mm:ss a");

    public static DateTimeFormatter yearMonthDayFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd");
    public static DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy MMMM");
    public static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
    public static DateTimeFormatter transactionIDFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
}
