package com.example.postearevised.Miscellaneous.Others;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.EXIT_CONFIRMATION_ENUM;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorPrintingError;
import static com.example.postearevised.Miscellaneous.References.FileReference.ERROR_LOG_PATH;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.SYSTEM_LOGO;

public class LogFile {
    public static String errorMessage;

    public static void logError(boolean openError) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
            String dateTime = dateFormat.format(new Date());
            FileWriter fileWriter = new FileWriter(ERROR_LOG_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dateTime);
            bufferedWriter.newLine();
            bufferedWriter.write("Error: " + errorMessage);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println("Error written to notepad successfully!");

            if (openError)
                Desktop.getDesktop().open(new File(ERROR_LOG_PATH));
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorPrintingError();
            openPrompt();
        }
    }

    private static void openPrompt() {
        FXMLLoader loader = new FXMLLoader(LogFile.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }
}
