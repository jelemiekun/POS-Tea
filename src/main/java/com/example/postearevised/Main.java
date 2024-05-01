package com.example.postearevised;

import com.example.postearevised.Miscellaneous.Others.MainExtends;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> MainExtends.main(args));
        thread.setName("Main Thread");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
    }
}