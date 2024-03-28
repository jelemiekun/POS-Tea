package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations;
import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ImportCSV {
    public static int importProductsFromCSV(String filePath, boolean fromImport) {

        // 0- do nothing, 1- successful, 2- invalid file format, 3- other unexpected errors

        ObservableList<Product> importedProducts = FXCollections.observableArrayList();
        ObservableList<Product> importedMilkTeas = FXCollections.observableArrayList();
        ObservableList<Product> importedCoolers = FXCollections.observableArrayList();
        ObservableList<Product> importedCoffees = FXCollections.observableArrayList();
        ObservableList<Product> importedIceCandyCups = FXCollections.observableArrayList();
        ObservableList<Product> importedAppetizers = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = reader.readLine(); // Read the header
            if (headerLine != null) {
                String[] fields = headerLine.split(",");
                if (fields.length != 21) {
                    return 2;
                }
            } else {
                return 2;
            }

            boolean repeatCategory = true;
            boolean repeatBlankCells = true;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");


                String productName = fields[0];
                String productDescription = fields[1];
                String productCategory = fields[2];
                String imagePath = fields[3];
                String availability = fields[4];
                System.out.println("Image Path: " + imagePath);

                if (fromImport) {
                    if (repeatCategory) {
                        if (!isValidCategory(productCategory)) {
                            repeatCategory = false;
                            setErrorAddingProductCategory();
                            if (!openPrompt())
                                return 0;
                        }
                    }

                    if (repeatBlankCells) {
                        if (hasBlankCells(fields)) {
                            repeatBlankCells = false;
                            setBlankCellsDetected();
                            if (!openPrompt())
                                return 0;
                        }
                    }
                }

                File imageFile = new File(imagePath);
                if (!imageFile.exists() || imageFile.length() == 0) {
                    imagePath = "/com/example/postearevised/Product Media/no image/no image.png";
                }

                Product product = null;

                switch (productCategory) {
                    case "Milk Tea":
                        double milkTeaSmallPrice = Double.parseDouble(fields[5]);
                        double milkTeaMediumPrice = Double.parseDouble(fields[6]);
                        double milkTeaLargePrice = Double.parseDouble(fields[7]);
                        String milkTeaAddOnsOne = fields[8];
                        double milkTeaAddOnsOnePrice = Double.parseDouble(fields[9]);
                        String milkTeaAddOnsTwo = fields[10];
                        double milkTeaAddOnsTwoPrice = Double.parseDouble(fields[11]);
                        product = new MilkTea(productName, productDescription, imagePath, productCategory,
                                milkTeaSmallPrice, milkTeaMediumPrice, milkTeaLargePrice,
                                milkTeaAddOnsOne, milkTeaAddOnsOnePrice, milkTeaAddOnsTwo, milkTeaAddOnsTwoPrice);
                        product.getCheckBox().setSelected(Boolean.parseBoolean(availability));
                        importedMilkTeas.add(product);
                        break;
                    case "Coolers":
                        double coolersSmallPrice = Double.parseDouble(fields[12]);
                        double coolersMediumPrice = Double.parseDouble(fields[13]);
                        double coolersLargePrice = Double.parseDouble(fields[14]);
                        String coolersAddOnsOne = fields[15];
                        double coolersAddOnsOnePrice = Double.parseDouble(fields[16]);
                        String coolersAddOnsTwo = fields[17];
                        double coolersAddOnsTwoPrice = Double.parseDouble(fields[18]);
                        product = new Coolers(productName, productDescription, imagePath, productCategory,
                                coolersSmallPrice, coolersMediumPrice, coolersLargePrice,
                                coolersAddOnsOne, coolersAddOnsOnePrice, coolersAddOnsTwo, coolersAddOnsTwoPrice);
                        product.getCheckBox().setSelected(Boolean.parseBoolean(availability));
                        importedCoolers.add(product);
                        break;
                    case "Coffee":
                        double coffeePrice = Double.parseDouble(fields[19]);
                        product = new Coffee(productName, productDescription, imagePath, productCategory, coffeePrice);
                        product.getCheckBox().setSelected(Boolean.parseBoolean(availability));
                        importedCoffees.add(product);
                        break;
                    case "Ice Candy Cups":
                        double iceCandyCupsPrice = Double.parseDouble(fields[20]);
                        product = new IceCandyCups(productName, productDescription, imagePath, productCategory, iceCandyCupsPrice);
                        product.getCheckBox().setSelected(Boolean.parseBoolean(availability));
                        importedIceCandyCups.add(product);
                        break;
                    case "Appetizers":
                        double appetizerPrice = Double.parseDouble(fields[21]);
                        product = new Appetizer(productName, productDescription, imagePath, productCategory, appetizerPrice);
                        product.getCheckBox().setSelected(Boolean.parseBoolean(availability));
                        importedAppetizers.add(product);
                        break;
                    default:
                        System.err.println("Invalid product category: " + productCategory);
                        break;
                }

                if (product != null) {
                    importedProducts.add(product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            errorMessage = e.getMessage();
            logError(false);
            return 3;
        }

        addImportedListsToSystemLists(importedProducts, importedMilkTeas, importedCoolers, importedCoffees, importedIceCandyCups, importedAppetizers, fromImport);
        return 1;
    }

    private static boolean isValidCategory(String category) {
        return category.equals("Milk Tea") || category.equals("Coolers") || category.equals("Coffee") || category.equals("Ice Candy Cups") || category.equals("Appetizers");
    }

    private static boolean hasBlankCells(String[] fields) {
        for (String field : fields) {
            if (field.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }



    private static void addImportedListsToSystemLists(ObservableList<Product> importedProducts, ObservableList<Product> importedMilkTeas,
                                                      ObservableList<Product> importedCoolers, ObservableList<Product> importedCoffees,
                                                      ObservableList<Product> importedIceCandyCups, ObservableList<Product> importedAppetizers,
                                                      boolean fromImport) {
        allProductObservableList.addAll(importedProducts);

        for (Product product : importedProducts) {
            if (product.getCheckBox().isSelected())
                availableAllProductObservableList.add(product);
            else
                unavailableAllProductObservableList.add(product);
        }

        for (Product product : importedMilkTeas) {
            if (product instanceof MilkTea milkTea) {
                if (milkTea.getCheckBox().isSelected())
                    availableMilkTeaObservableList.add(milkTea);
                else
                    unavailableMilkTeaObservableList.add(milkTea);
            }
        }

        for (Product product : importedCoolers) {
            if (product instanceof Coolers coolers) {
                if (coolers.getCheckBox().isSelected())
                    availableCoolersObservableList.add(coolers);
                else
                    unavailableCoolersObservableList.add(coolers);
            }
        }

        for (Product product : importedCoffees) {
            if (product instanceof Coffee coffee) {
                if (coffee.getCheckBox().isSelected())
                    availableCoffeeObservableList.add(coffee);
                else
                    unavailableCoffeeObservableList.add(coffee);
            }
        }

        for (Product product : importedIceCandyCups) {
            if (product instanceof IceCandyCups iceCandyCups) {
                if (iceCandyCups.getCheckBox().isSelected())
                    availableIceCandyCupsObservableList.add(iceCandyCups);
                else
                    unavailableIceCandyCupsObservableList.add(iceCandyCups);
            }
        }

        for (Product product : importedAppetizers) {
            if (product instanceof Appetizer appetizer) {
                if (appetizer.getCheckBox().isSelected())
                    availableAppetizerObservableList.add(appetizer);
                else
                    unavailableAppetizerObservableList.add(appetizer);
            }
        }

        if (fromImport) {
            for (Product product : importedProducts) {
                addProductToCSV(product);
            }
        }
    }

    public static boolean openPrompt() {
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
        newStage.initOwner(mainStage.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }
}
