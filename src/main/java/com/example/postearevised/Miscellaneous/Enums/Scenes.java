package com.example.postearevised.Miscellaneous.Enums;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.MENU_ENUM;

public enum Scenes {
    LOGIN_ENUM("Login", "/com/example/postearevised/Scenes/Main/LoginRegisterForgotPass.fxml"),
    Register("Register", "/com/example/postearevised/Scenes/Main/LoginRegisterForgotPass.fxml"),
    MAIN_ENUM(MENU_ENUM.getName(), "/com/example/postearevised/Scenes/Main/Main.fxml"),
    EXIT_CONFIRMATION_ENUM("", "/com/example/postearevised/Scenes/Additional/Prompt.fxml"),
    TERMS_AND_CONDITION_ENUM("Terms And Condition", "/com/example/postearevised/Scenes/Additional/TermsAndCondition.fxml"),
    PRODUCT_ENUM("Product", "/com/example/postearevised/Scenes/Additional/Product.fxml"),
    PRODUCT_ORDER_LIST("Order Summary", "/com/example/postearevised/Scenes/Additional/ProductOrderList.fxml"),
    DELETE_HISTORY("Delete History", "/com/example/postearevised/Scenes/Additional/DeleteHistory.fxml");

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
