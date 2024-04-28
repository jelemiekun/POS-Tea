package com.example.postearevised.Miscellaneous.Others;

import java.awt.*;
import java.util.Objects;

import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;

public class Resolution {
    public static boolean showResolutionTooLowMessage = false;
    private static final int[] lowestUsableResolution = {1870, 1000};

    public static void setScreenResolution(boolean isMain, boolean isLogout) {
        isTaskBarHidden();
        (isMain ? mainStage : (isLogout ? loginFromMainSceneStage : loginRegisterStage)).setWidth(screenResolution[0]);
        (isMain ? mainStage : (isLogout ? loginFromMainSceneStage : loginRegisterStage)).setHeight(screenResolution[1]);
        setStageStyle(isLogout);
        System.out.println("Screen resolution: " + screenResolution[0] + "x" + screenResolution[1]);
    }

    public static void setStageStyle(boolean isLogout) {
        (isLogout ? loginFromMainSceneStage : loginRegisterStage).getScene().getStylesheets().clear();
        (isLogout ? loginFromMainSceneStage : loginRegisterStage).getScene().getStylesheets().add(Objects.requireNonNull(Resolution.class.getResource(LIGHT_ENUM.getCssURL())).toExternalForm());
    }

    private static void isTaskBarHidden() {
        // Get the screen size
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        // Get the bounds of the usable screen area
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        // Calculate the taskbar size
        int taskbarWidth = screenSize.width - bounds.width;
        int taskbarHeight = screenSize.height - bounds.height;

        // Calculate the viewable screen size
        int screenWidth = screenSize.width - taskbarWidth;
        int screenHeight = screenSize.height - taskbarHeight;

        // Check if taskbar is Hidden
        boolean taskbarHidden = (screenSize.width != bounds.width || screenSize.height != bounds.height);
        taskbarHidden = !taskbarHidden;

        // Prints if taskbar is hidden
        System.out.println("Is taskbar hidden? " + taskbarHidden);

        setScreenResolution(screenWidth, screenHeight);
        checkIfResolutionTooSmall(screenWidth, screenHeight);
    }

    private static void setScreenResolution(int screenWidth, int screenHeight) {
        screenResolution[0] = screenWidth;
        screenResolution[1] = screenHeight;
    }

    private static void checkIfResolutionTooSmall(int screenWidth, int screenHeight) {
        showResolutionTooLowMessage = screenWidth < lowestUsableResolution[0] || screenHeight < lowestUsableResolution[1];
    }
}
