package com.example.postearevised.Miscellaneous.Enums;

public enum DisplayColorsEnum {

    // element.setId("ID");

    LIGHT_ENUM("light", "/com/example/postearevised/Styles/Lightmode.css"),
    DARK_ENUM("dark", "/com/example/postearevised/Styles/Darkmode.css"),
    RED_ENUM("red", "/com/example/postearevised/Styles/Lightmode.css"),
    ORANGE_ENUM("orange", "/com/example/postearevised/Styles/Lightmode.css"),
    YELLOW_ENUM("yellow", "/com/example/postearevised/Styles/Lightmode.css"),
    GREEN_ENUM("green", "/com/example/postearevised/Styles/Lightmode.css");

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
