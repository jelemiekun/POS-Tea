package com.example.postearevised.Miscellaneous.Database.CSV.Accounts;

import com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class AccountCSV {
    public static void doesAccountCSVExist() {
        createDirectoryIfNotExists(DIRECTORY_PATH);
        createAccountCSVFileIfNotExists();
    }

    private static void createAccountCSVFileIfNotExists() {
        File file = new File(CSV_FILE_PATH_ACCOUNTS);
        if (!file.exists()) {
            System.out.println("Directory exists but no CSV file, will now create CSV: " + CSV_FILE_PATH_ACCOUNTS);
            createAccountCSVFile();
        } else {
            System.out.println("CSV account file already exists: " + CSV_FILE_PATH_ACCOUNTS);
            // method to import csv file
        }
    }

    public static void createAccountCSVFile() {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ACCOUNTS)) {
            writer.write("contact,password,securityQuestionOne,securityQuestionOneAnswer,securityQuestionTwo,securityQuestionTwoAnswer,firstNames,middleNames,lastNames,displayColor,isShowNotification,isShowGuideMessages\n");
            System.out.println("Creating account csv file: " + CSV_FILE_PATH_ACCOUNTS);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }

    private static void openPrompt() {
        FXMLLoader loader = new FXMLLoader(OrderHistoryAndOrderQueueCSVOperations.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterStage.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }
}
