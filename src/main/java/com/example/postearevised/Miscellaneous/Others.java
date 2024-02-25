package com.example.postearevised.Miscellaneous;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Others {
    public static boolean isInternetRequired = false;
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            return false;
        }
    }

}
