package com.example.postearevised.Miscellaneous.Others;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.EXIT_CONFIRMATION_ENUM;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.isConfirmed;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorPrintingError;
import static com.example.postearevised.Miscellaneous.References.FileReference.ERROR_LOG_PATH;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.SYSTEM_LOGO;

public class LogFile {
    public static String errorMessage;

    public static void logError(boolean openError) throws IOException {
        try {
            // Get current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
            String dateTime = dateFormat.format(new Date());

            // Create a FileWriter object with append mode set to true
            FileWriter fileWriter = new FileWriter(ERROR_LOG_PATH, true);

            // Create a BufferedWriter object
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the date and time to the notepad
            bufferedWriter.write(dateTime);
            bufferedWriter.newLine();

            // Write the error message to the notepad
            bufferedWriter.write("Error: " + errorMessage);
            bufferedWriter.newLine();

            // Add two empty lines
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            // Close the BufferedWriter
            bufferedWriter.close();

            // Inform the user
            System.out.println("Error written to notepad successfully!");

            if (openError) {
                // Open the notepad
                Desktop.getDesktop().open(new File(ERROR_LOG_PATH));
            }
        } catch (IOException e) {
            setErrorPrintingError();
            openPrompt();
        }
    }

    private static void openPrompt() throws IOException {
        FXMLLoader loader = new FXMLLoader(LogFile.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }
}
