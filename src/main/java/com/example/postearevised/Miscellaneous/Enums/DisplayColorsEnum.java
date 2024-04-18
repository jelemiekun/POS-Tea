package com.example.postearevised.Miscellaneous.Enums;

public enum DisplayColorsEnum {

    // element.setId("ID");

    LIGHT_ENUM("light", "/com/example/postearevised/Styles/Lightmode.css"),
    DARK_ENUM("dark", "/com/example/postearevised/Styles/Darkmode.css"),
    BEIGE_ENUM("beige", "/com/example/postearevised/Styles/Beigemode.css"),
    BLUE_ENUM("blue", "/com/example/postearevised/Styles/Bluemode.css"),
    CREAM_ENUM("cream", "/com/example/postearevised/Styles/Creammode.css"),
    GREEN_ENUM("green", "/com/example/postearevised/Styles/Greenmode.css");

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
