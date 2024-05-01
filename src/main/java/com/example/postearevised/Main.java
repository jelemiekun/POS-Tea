package com.example.postearevised;

import com.example.postearevised.Miscellaneous.Others.MainExtends;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class Main {
    public static void main(String[] args) {
        Thread mainThread = new Thread(() -> MainExtends.main(args));
        mainThread.setName("Main Thread");
        mainThread.start();

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            errorMessage = e.getMessage();
            logError(true);
        }

        System.exit(0);
    }
}