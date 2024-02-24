package com.example.postearevised.Miscellaneous.Enums;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.Login;

public enum MainPane {
    Menu("Menu",1),
    Dashboard("Dashboard",2),
    OrderList("Order List",3),
    OrderHistory("Order History",4),
    AboutUs("About Us",5),
    Settings("Settings",6),
    Logout(Login.getTITLE(),7);

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
