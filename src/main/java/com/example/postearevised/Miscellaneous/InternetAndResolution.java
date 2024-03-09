package com.example.postearevised.Miscellaneous;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;


public class InternetAndResolution {
    public static boolean isInternetRequired = false;
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            return false;
        }
    }

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

        // Prints if taskbar is hidden
        System.out.println(taskbarHidden);

        setScreenResolution(screenWidth, screenHeight);
    }

    private static void setScreenResolution(int screenWidth, int screenHeight) {
       screenResolution[0] = screenWidth;
       screenResolution[1] = screenHeight;
    }
}
