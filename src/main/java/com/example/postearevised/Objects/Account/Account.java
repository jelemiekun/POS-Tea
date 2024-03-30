package com.example.postearevised.Objects.Account;

import javafx.collections.ObservableList;

import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.*;

public class Account {
    private String contact;
    private String password;
    private String securityQuestionOne;
    private String securityQuestionOneAnswer;
    private String securityQuestionTwo;
    private String securityQuestionTwoAnswer;
    private ObservableList<String> firstNames;
    private ObservableList<String> middleNames;
    private ObservableList<String> lastNames;
    private String displayColor;
    private boolean isShowNotification;
    private boolean isShowGuideMessages;

    public Account(String contact, String password) {
        this.contact = contact;
        this.password = password;
    }

    public Account(String contact, String password, String securityQuestionOne, String securityQuestionOneAnswer, String securityQuestionTwo, String securityQuestionTwoAnswer, ObservableList<String> firstNames, ObservableList<String> middleNames, ObservableList<String> lastNames) {
        this.contact = contact;
        this.password = password;
        this.securityQuestionOne = securityQuestionOne;
        this.securityQuestionOneAnswer = securityQuestionOneAnswer;
        this.securityQuestionTwo = securityQuestionTwo;
        this.securityQuestionTwoAnswer = securityQuestionTwoAnswer;
        this.firstNames = firstNames;
        this.middleNames = middleNames;
        this.lastNames = lastNames;
        this.displayColor = LIGHT_ENUM.getColor();
    }

    public Account(String contact, String password, String securityQuestionOne, String securityQuestionOneAnswer, String securityQuestionTwo, String securityQuestionTwoAnswer, ObservableList<String> firstNames, ObservableList<String> middleNames, ObservableList<String> lastNames, String displayColor, boolean isShowNotification, boolean isShowGuideMessages) {
        this.contact = contact;
        this.password = password;
        this.securityQuestionOne = securityQuestionOne;
        this.securityQuestionOneAnswer = securityQuestionOneAnswer;
        this.securityQuestionTwo = securityQuestionTwo;
        this.securityQuestionTwoAnswer = securityQuestionTwoAnswer;
        this.firstNames = firstNames;
        this.middleNames = middleNames;
        this.lastNames = lastNames;
        this.displayColor = displayColor;
        this.isShowNotification = isShowNotification;
        this.isShowGuideMessages = isShowGuideMessages;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestionOne() {
        return securityQuestionOne;
    }

    public void setSecurityQuestionOne(String securityQuestionOne) {
        this.securityQuestionOne = securityQuestionOne;
    }

    public String getSecurityQuestionOneAnswer() {
        return securityQuestionOneAnswer;
    }

    public void setSecurityQuestionOneAnswer(String securityQuestionOneAnswer) {
        this.securityQuestionOneAnswer = securityQuestionOneAnswer;
    }

    public String getSecurityQuestionTwo() {
        return securityQuestionTwo;
    }

    public void setSecurityQuestionTwo(String securityQuestionTwo) {
        this.securityQuestionTwo = securityQuestionTwo;
    }

    public String getSecurityQuestionTwoAnswer() {
        return securityQuestionTwoAnswer;
    }

    public void setSecurityQuestionTwoAnswer(String securityQuestionTwoAnswer) {
        this.securityQuestionTwoAnswer = securityQuestionTwoAnswer;
    }

    public ObservableList<String> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(ObservableList<String> firstNames) {
        this.firstNames = firstNames;
    }

    public ObservableList<String> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(ObservableList<String> middleNames) {
        this.middleNames = middleNames;
    }

    public ObservableList<String> getLastNames() {
        return lastNames;
    }

    public void setLastNames(ObservableList<String> lastNames) {
        this.lastNames = lastNames;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public boolean isShowNotification() {
        return isShowNotification;
    }

    public void setShowNotification(boolean showNotification) {
        isShowNotification = showNotification;
    }

    public boolean isShowGuideMessages() {
        return isShowGuideMessages;
    }

    public void setShowGuideMessages(boolean showGuideMessages) {
        isShowGuideMessages = showGuideMessages;
    }
}
