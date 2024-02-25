package com.example.postearevised.Miscellaneous.Enums;

import javafx.scene.paint.Color;

public enum PasswordColors {
    White("", Color.web("#ffffff")),
    Weak("Weak", Color.web("#ff2b2b")),
    Fair("Fair", Color.web("#ffc629")),
    Good("Good", Color.web("#28ff19")),
    Strong("Strong", Color.web("#098d00"));

    private final String text;
    private final Color color;

    PasswordColors(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
