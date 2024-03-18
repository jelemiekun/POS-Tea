package com.example.postearevised.Miscellaneous.Enums;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.LOGIN_ENUM;

public enum MainPane {
    MENU_ENUM("Menu",1),
    DASHBOARD_ENUM("Dashboard",2),
    ORDER_LIST_ENUM("Order List",3),
    ORDER_HISTORY_ENUM("Order History",4),
    SETTINGS_ENUM("Settings",5),
    LOGOUT_ENUM(LOGIN_ENUM.getTITLE(),6);

    private final String name;
    private final int paneNumber;

    MainPane(String name, int paneNumber) {
        this.name = name;
        this.paneNumber = paneNumber;
    }

    public int getPaneNumber() {
        return paneNumber;
    }

    public String getName() {
        return name;
    }
}
