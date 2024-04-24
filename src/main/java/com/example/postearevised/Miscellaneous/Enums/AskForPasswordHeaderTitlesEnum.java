package com.example.postearevised.Miscellaneous.Enums;

public enum AskForPasswordHeaderTitlesEnum {
    USERS_ENUM("Edit Users"),
    ACCOUNT_DETAILS_ENUM("Edit Account Details"),
    RECOVERY_QUESTIONS_ENUM("Edit Questions"),
    DELETE_ACCOUNT_ENUM("Delete Account"),
    USERS_SELECTION_ENUM("User");

    private final String headerTitle;

    AskForPasswordHeaderTitlesEnum(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }
}
