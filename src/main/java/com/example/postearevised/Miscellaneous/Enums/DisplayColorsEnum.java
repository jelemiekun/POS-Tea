package com.example.postearevised.Miscellaneous.Enums;

public enum DisplayColorsEnum {
    LIGHT_ENUM("light"),
    DARK_ENUM("dark"),
    RED_ENUM("red"),
    ORANGE_ENUM("orange"),
    YELLOW_ENUM("yellow"),
    GREEN_ENUM("green");

    private final String color;

    DisplayColorsEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
