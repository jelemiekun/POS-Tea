package com.example.postearevised.Miscellaneous.Enums;

public enum DisplayColorsEnum {
    LIGHT_ENUM("light", ""),
    DARK_ENUM("dark", ""),
    RED_ENUM("red", ""),
    ORANGE_ENUM("orange", ""),
    YELLOW_ENUM("yellow", ""),
    GREEN_ENUM("green", "");

    private final String color;
    private final String cssURL;

    DisplayColorsEnum(String color, String cssURL) {
        this.color = color;
        this.cssURL = cssURL;
    }

    public String getColor() {
        return color;
    }

    public String getCssURL() { return cssURL; }
}
