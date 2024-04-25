package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Account.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class AccountReference {
    public static Account accountReference;
    public static Account oldAccountReference;
    public static Set<Account> accountSet = new HashSet<>();
    public static String accountContactReference = "";
    public static boolean showGuideMessagesReference = true;
    public static ObservableList<String> fullNames = FXCollections.observableArrayList();
    public static ObservableList<String> usersNames = FXCollections.observableArrayList();
    public static int userIndex;
    public static int userIndexCopy;
}
