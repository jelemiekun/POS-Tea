package com.example.postearevised.Miscellaneous.Enums;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.Menu;

public enum Scenes {
    Login("Login", "/com/example/postearevised/Scenes/Main/LoginRegisterForgotPass.fxml"),
    Register("Register", "/com/example/postearevised/Scenes/Main/LoginRegisterForgotPass.fxml"),
    Main(Menu.getName(), "/com/example/postearevised/Scenes/Main/Main.fxml"),
    ExitConfirmation("Confirm", "/com/example/postearevised/Scenes/Additional/Prompt.fxml"),
    TermsAndCondition("Terms And Condition", "/com/example/postearevised/Scenes/Additional/TermsAndCondition.fxml");

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
