package com.example.postearevised.Miscellaneous.Others;

import java.awt.*;
import java.io.IOException;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;

public class Resolution {
    public static void setScreenResolution(boolean isMain, boolean isLogout) {
        isTaskBarHidden();
        (isMain ? mainStage : (isLogout ? loginFromMainSceneStage : loginRegisterStage)).setWidth(screenResolution[0]);
        (isMain ? mainStage : (isLogout ? loginFromMainSceneStage : loginRegisterStage)).setHeight(screenResolution[1]);
        System.out.println(screenResolution[0] + "x" + screenResolution[1]);
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
    }

    static {
        try {
            // Run the command to restart Windows Explorer
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start explorer.exe");
            builder.redirectErrorStream(true);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setScreenResolution(int screenWidth, int screenHeight) {
        screenResolution[0] = screenWidth;
        screenResolution[1] = screenHeight;
    }
}
