package com.example.postearevised.Miscellaneous.Database.CSV.Accounts;

import com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations;
import com.example.postearevised.Objects.Account.Account;
import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;

public class AccountCSV {
    public static void doesStayLoggedInCSVExist() {
        File stayLoggedInFile = new File(CSV_FILE_PATH_STAY_LOGGED_IN);

        if (!stayLoggedInFile.exists()) {
            System.out.println("Directory exists but no CSV file, will now create CSV: " + CSV_FILE_PATH_STAY_LOGGED_IN);
            createStayLoggedInCSVFileIfNotExists();
        } else {
            System.out.println("CSV account file already exists: " + CSV_FILE_PATH_STAY_LOGGED_IN);
            readStayLoggedInCSV();
        }
    }

    public static void readStayLoggedInCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_STAY_LOGGED_IN))) {
            int rowCount = 0;
            String line;
            String dataFirstColumnSecondRow = null;
            String dataSecondColumnSecondRow = null;
            String dataThirdColumnSecondRow = null;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                rowCount++;

                if (rowCount == 2 && columns.length == 3) {
                    dataFirstColumnSecondRow = columns[0];
                    dataSecondColumnSecondRow = columns[1];
                    dataThirdColumnSecondRow = columns[2];
                }
            }

            if (rowCount == 2) {
                if (dataFirstColumnSecondRow != null && dataSecondColumnSecondRow != null && dataThirdColumnSecondRow != null) {
                    loginAccount = dataFirstColumnSecondRow;
                    loginPassword = decryptString(dataSecondColumnSecondRow, dataThirdColumnSecondRow);
                    directLogin = true;
                } else {
                    directLogin = false;
                }
            } else {
                directLogin = false;
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(true);
        }
    }

    private static void createStayLoggedInCSVFileIfNotExists() {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_STAY_LOGGED_IN, true)) {
            writer.write("contact,password,key\n");
            System.out.println("Creating stay logged in csv file: " + CSV_FILE_PATH_STAY_LOGGED_IN);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }

    public static void inputIntoSecondRow(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH_STAY_LOGGED_IN, true))) {
            writer.write(account.getContact() + "," + encryptString(account.getPassword(), account.getKey()) + "," + account.getKey()  + "\n");
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
    }

    public static void loggedOutSoDeleteStayLoggedInDetails() {
        File stayLoggedInFile = new File(CSV_FILE_PATH_STAY_LOGGED_IN);

        if (stayLoggedInFile.exists() && stayLoggedInFile.delete()) {
            createStayLoggedInCSVFileIfNotExists();
        }
    }

    public static void doesAccountCSVExist() {
        createDirectoryIfNotExists(DIRECTORY_PATH);
        createDirectoryIfNotExists(DIRECTORY_PATH_SENSITIVE_DATA);
        createAccountCSVFileIfNotExists();
        hideAccountCSV();
    }

    private static void createAccountCSVFileIfNotExists() {
        File file = new File(CSV_FILE_PATH_ACCOUNTS);
        if (!file.exists()) {
            System.out.println("Directory exists but no CSV file, will now create CSV: " + CSV_FILE_PATH_ACCOUNTS);
            createAccountCSVFile();
        } else {
            System.out.println("CSV account file already exists: " + CSV_FILE_PATH_ACCOUNTS);
            importAccountCSVFile();
        }
    }

    private static void createAccountCSVFile() {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ACCOUNTS, true)) {
            writer.write("contact,password,securityQuestionOne,securityQuestionOneAnswer,securityQuestionTwo,securityQuestionTwoAnswer,firstNames,middleNames,lastNames,displayColor,isShowNotification,isShowGuideMessages,key,userPasswords\n");
            System.out.println("Creating account csv file: " + CSV_FILE_PATH_ACCOUNTS);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }

    private static void importAccountCSVFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_ACCOUNTS))) {

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 13) {
                    ObservableList<String> observableListFirstNames = FXCollections.observableArrayList();
                    ObservableList<String> observableListMiddleNames = FXCollections.observableArrayList();
                    ObservableList<String> observableListLastNames = FXCollections.observableArrayList();

                    String contact = parts[0];
                    String password = parts[1];
                    String securityQuestionOne = parts[2];
                    String securityQuestionOneAnswer = parts[3];
                    String securityQuestionTwo = parts[4];
                    String securityQuestionTwoAnswer = parts[5];
                    String[] firstNames = parts[6].split("/");
                    String[] middleNames = parts[7].split("/");
                    String[] lastNames = parts[8].split("/");

                    for (int i = 0; i < firstNames.length; i++) {
                        middleNames[i] = middleNames[i].isEmpty() || middleNames[i].equals(".") ? "" : middleNames[i];
                    }

                    observableListFirstNames.addAll(Arrays.asList(firstNames));
                    observableListMiddleNames.addAll(Arrays.asList(middleNames));
                    observableListLastNames.addAll(Arrays.asList(lastNames));

                    String displayColor = parts[9];
                    boolean isShowNotification = Boolean.parseBoolean(parts[10]);
                    boolean isShowGuideMessages = Boolean.parseBoolean(parts[11]);
                    String key = parts[12];

                    Account account = new Account(contact, decryptString(password, key),
                            securityQuestionOne, decryptString(securityQuestionOneAnswer, key),
                            securityQuestionTwo, decryptString(securityQuestionTwoAnswer, key),
                            observableListFirstNames, observableListMiddleNames, observableListLastNames,
                            displayColor, isShowNotification, isShowGuideMessages, key);

                    accountSet.add(account);
                } else if (parts.length == 14){
                    ObservableList<String> observableListFirstNames = FXCollections.observableArrayList();
                    ObservableList<String> observableListMiddleNames = FXCollections.observableArrayList();
                    ObservableList<String> observableListLastNames = FXCollections.observableArrayList();
                    ObservableList<String> observableListUserPasswords = FXCollections.observableArrayList();

                    String contact = parts[0];
                    String password = parts[1];
                    String securityQuestionOne = parts[2];
                    String securityQuestionOneAnswer = parts[3];
                    String securityQuestionTwo = parts[4];
                    String securityQuestionTwoAnswer = parts[5];
                    String[] firstNames = parts[6].split("/");
                    String[] middleNames = parts[7].split("/");
                    String[] lastNames = parts[8].split("/");

                    for (int i = 0; i < firstNames.length; i++) {
                        middleNames[i] = middleNames[i].isEmpty() || middleNames[i].equals(".") ? "" : middleNames[i];
                    }

                    observableListFirstNames.addAll(Arrays.asList(firstNames));
                    observableListMiddleNames.addAll(Arrays.asList(middleNames));
                    observableListLastNames.addAll(Arrays.asList(lastNames));

                    String displayColor = parts[9];
                    boolean isShowNotification = Boolean.parseBoolean(parts[10]);
                    boolean isShowGuideMessages = Boolean.parseBoolean(parts[11]);
                    String key = parts[12];
                    String[] userPasswords = parts[13].split("/");

                    for (String userPassword : userPasswords) {
                        observableListUserPasswords.add(decryptString(userPassword, key));
                    }

                    Account account = new Account(contact, decryptString(password, key),
                            securityQuestionOne, decryptString(securityQuestionOneAnswer, key),
                            securityQuestionTwo, decryptString(securityQuestionTwoAnswer, key),
                            observableListFirstNames, observableListMiddleNames, observableListLastNames,
                            displayColor, isShowNotification, isShowGuideMessages, key, observableListUserPasswords);

                    accountSet.add(account);
                }
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorReadingAccountFromCSV();
            OrderHistoryAndOrderQueueCSVOperations.openPrompt();
        }
    }

    public static boolean addAccountToAccountCSV(Account account) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ACCOUNTS, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(account.getContact()).append(",");
            sb.append(encryptString(account.getPassword(), account.getKey())).append(",");
            sb.append(account.getSecurityQuestionOne()).append(",");
            sb.append(encryptString(account.getSecurityQuestionOneAnswer(), account.getKey())).append(",");
            sb.append(account.getSecurityQuestionTwo()).append(",");
            sb.append(encryptString(account.getSecurityQuestionTwoAnswer(), account.getKey())).append(",");
            sb.append(concatenateNames(account.getFirstNames())).append(",");
            sb.append(concatenateNames(account.getMiddleNames())).append(",");
            sb.append(concatenateNames(account.getLastNames())).append(",");
            sb.append(account.getDisplayColor()).append(",");
            sb.append(account.isShowNotification()).append(",");
            sb.append(account.isShowGuideMessages()).append(",");
            sb.append(account.getKey()).append("\n");

            writer.write(sb.toString());
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingAccount();
            openPrompt();
            return false;
        }
    }

    private static String concatenateNames(ObservableList<String> names) {
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            name = name.isEmpty() ? "." : name;
            sb.append(name).append("/");
        }

        return sb.toString();
    }

    private static String concatenatePasswords(ObservableList<String> passwords, String key) {
        StringBuilder sb = new StringBuilder();
        for (String password: passwords) {
            sb.append(encryptString(password, key)).append("/");
        }

        return sb.toString();
    }

    public static boolean updateAccountToAccountCSV(Account oldAccount, Account newAccount) {
        try {
            File inputFile = new File(CSV_FILE_PATH_ACCOUNTS);
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));

            String line;
            String oldAccountDetails = oldAccount.getContact();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String accountDetails = fields[0];

                if (accountDetails.equals(oldAccountDetails)) {
                    StringBuilder sb = new StringBuilder();

                    sb.append(newAccount.getContact()).append(",");
                    sb.append(encryptString(newAccount.getPassword(), newAccount.getKey())).append(",");
                    sb.append(newAccount.getSecurityQuestionOne()).append(",");
                    sb.append(encryptString(newAccount.getSecurityQuestionOneAnswer(), newAccount.getKey())).append(",");
                    sb.append(newAccount.getSecurityQuestionTwo()).append(",");
                    sb.append(encryptString(newAccount.getSecurityQuestionTwoAnswer(), newAccount.getKey())).append(",");
                    sb.append(concatenateNames(newAccount.getFirstNames())).append(",");
                    sb.append(concatenateNames(newAccount.getMiddleNames())).append(",");
                    sb.append(concatenateNames(newAccount.getLastNames())).append(",");
                    sb.append(newAccount.getDisplayColor()).append(",");
                    sb.append(newAccount.isShowNotification()).append(",");
                    sb.append(newAccount.isShowGuideMessages()).append(",");
                    sb.append(newAccount.getKey()).append(",");
                    sb.append(concatenatePasswords(newAccount.getUserPasswords(), newAccount.getKey())).append("\n");

                    writer.write(sb.toString());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
            System.out.println("Gumagana");
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }


    public static boolean deleteAccountFromCSV(Account accountToDelete) {
        try {
            File inputFile = new File(CSV_FILE_PATH_ACCOUNTS);
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            String accountDetailsToDelete = accountToDelete.getContact();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String accountDetails = fields[0];

                if (!accountDetails.equals(accountDetailsToDelete)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return false;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temporary file.");
                return false;
            }

            System.out.println("Account deleted successfully.");
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(true);
            return false;
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
        promptStage = new Stage();

        promptStage.initModality(Modality.WINDOW_MODAL);
        // login or loginFromMain stage
        promptStage.initOwner(loginRegisterStage.getScene().getWindow());

        promptStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        promptStage.showAndWait();
    }


    public static String encryptString(String input, String key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            logError(true);
            return null;
        }
    }

    public static String decryptString(String encryptedInput, String key) {
        try {
            if (encryptedInput == null || key == null) {
                return null;
            }

            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
            System.out.println(new String(decryptedBytes));
            return new String(decryptedBytes);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            logError(true);
            return null;
        }
    }

    private static void hideAccountCSV() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            // windows os
            File csvFile = new File(DIRECTORY_PATH_SENSITIVE_DATA);

            if (csvFile.exists()) {
                try {
                    String[] cmd = {"cmd", "/c", "attrib", "+h", "+r", "+s", DIRECTORY_PATH_SENSITIVE_DATA};

                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    errorMessage = e.getMessage();
                    logError(false);
                    setErrorHidingAccountCSV();
                    openPrompt();
                }
            }
        } else {
            // other os
            File csvFile = new File(DIRECTORY_PATH_SENSITIVE_DATA, ".accounts.csv");

            File hiddenCsvFile = new File(DIRECTORY_PATH_SENSITIVE_DATA, ".accounts.csv");
            if (csvFile.exists()) {
                if (csvFile.renameTo(hiddenCsvFile)) {
                    System.out.println("Account csv hidden successfully.");
                } else {
                    System.out.println("Failed to hide account csv.");
                }
            }
        }
    }

}
