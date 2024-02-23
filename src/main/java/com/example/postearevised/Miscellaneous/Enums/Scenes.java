package com.example.postearevised.Miscellaneous.Enums;

import static com.example.postearevised.Miscellaneous.Enums.Pane.Menu;

public enum Scenes {
    Login("Main", "/com/example/postearevised/Scenes/Main/LoginRegister.fxml"),
    Register("Register", "/com/example/postearevised/Scenes/Main/LoginRegister.fxml"),
    Main(Menu.getName(), "/com/example/postearevised/Scenes/Main/Main.fxml"),;

    private final String TITLE;
    private final String URL;
    Scenes(String TITLE, String URL) {
        this.TITLE = TITLE;
        this.URL = URL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getURL() {
        return URL;
    }
}
