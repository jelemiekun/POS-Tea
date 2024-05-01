package com.example.postearevised.Miscellaneous.Others;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;


public class Internet {
    public static boolean isInternetRequired = true;
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    public static boolean checkConnectivity() {
        boolean proceed;

        if (isInternetRequired) {
            proceed = isInternetAvailable();
        } else {
            return true;
        }

        return proceed;
    }
}
