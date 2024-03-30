package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations;
import com.example.postearevised.Objects.Products.*;

import java.io.*;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorCreatingCSVFile;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class ProductsCSVOperations {
    public static void createProductsCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("productName," +
                    "productDescription," +
                    "productCategory," +
                    "imagePath," +
                    "isAvailable," +
                    "milkTeaSmallPrice," +
                    "milkTeaMediumPrice," +
                    "milkTeaLargePrice," +
                    "milkTeaAddOnsOne," +
                    "milkTeaAddOnsOnePrice," +
                    "milkTeaAddOnsTwo," +
                    "milkTeaAddOnsTwoPrice," +
                    "coolersSmallPrice," +
                    "coolersMediumPrice," +
                    "coolersLargePrice," +
                    "coolersAddOnsOne," +
                    "coolersAddOnsOnePrice," +
                    "coolersAddOnsTwo," +
                    "coolersAddOnsTwoPrice," +
                    "coffeePrice," +
                    "iceCandyCupsPrice," +
                    "appetizerPrice\n");
            System.out.println("Creating products csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            OrderHistoryAndOrderQueueCSVOperations.openPrompt();
        }
    }

    public static boolean addProductToCSV(Product product) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_PRODUCTS, true)) {
            StringBuilder sb = new StringBuilder();

            switch (product.getCategory()) {
                case "Milk Tea":
                    MilkTea milkTea = (MilkTea) product;
                    sb.append(milkTea.getProductName()).append(",");
                    sb.append(milkTea.getProductDescription()).append(",");
                    sb.append(milkTea.getCategory()).append(",");
                    sb.append(milkTea.getImagePath()).append(",");
                    sb.append(milkTea.getCheckBox().isSelected()).append(",");


                    sb.append(milkTea.getSmallPrice()).append(",");
                    sb.append(milkTea.getMediumPrice()).append(",");
                    sb.append(milkTea.getLargePrice()).append(",");
                    sb.append(milkTea.getAddOnsOne()).append(",");
                    sb.append(milkTea.getAddOnsOnePrice()).append(",");
                    sb.append(milkTea.getAddOnsTwo()).append(",");
                    sb.append(milkTea.getAddOnsTwoPrice()).append("\n");
                    break;
                case "Coolers":
                    Coolers coolers = (Coolers) product;
                    sb.append(coolers.getProductName()).append(",");
                    sb.append(coolers.getProductDescription()).append(",");
                    sb.append(coolers.getCategory()).append(",");
                    sb.append(coolers.getImagePath()).append(",");
                    sb.append(coolers.getCheckBox().isSelected()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(coolers.getSmallPrice()).append(",");
                    sb.append(coolers.getMediumPrice()).append(",");
                    sb.append(coolers.getLargePrice()).append(",");
                    sb.append(coolers.getAddOnsOne()).append(",");
                    sb.append(coolers.getAddOnsOnePrice()).append(",");
                    sb.append(coolers.getAddOnsTwo()).append(",");
                    sb.append(coolers.getAddOnsTwoPrice()).append("\n");
                    break;
                case "Coffee":
                    Coffee coffee = (Coffee) product;
                    sb.append(coffee.getProductName()).append(",");
                    sb.append(coffee.getProductDescription()).append(",");
                    sb.append(coffee.getCategory()).append(",");
                    sb.append(coffee.getImagePath()).append(",");
                    sb.append(coffee.getCheckBox().isSelected()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(coffee.getPrice()).append("\n");
                    break;
                case "Ice Candy Cups":
                    IceCandyCups iceCandyCups = (IceCandyCups) product;
                    sb.append(iceCandyCups.getProductName()).append(",");
                    sb.append(iceCandyCups.getProductDescription()).append(",");
                    sb.append(iceCandyCups.getCategory()).append(",");
                    sb.append(iceCandyCups.getImagePath()).append(",");
                    sb.append(iceCandyCups.getCheckBox().isSelected()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(iceCandyCups.getPrice()).append("\n");
                    break;
                case "Appetizers":
                    Appetizer appetizer = (Appetizer) product;
                    sb.append(appetizer.getProductName()).append(",");
                    sb.append(appetizer.getProductDescription()).append(",");
                    sb.append(appetizer.getCategory()).append(",");
                    sb.append(appetizer.getImagePath()).append(",");
                    sb.append(appetizer.getCheckBox().isSelected()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(appetizer.getPrice());
                    break;
                default:
                    // Handle if category is not recognized
                    break;
            }
            writer.write(sb.toString());

            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    public static boolean editProductInCSV(Product oldProduct, Product newProduct) {
        try {
            File inputFile = new File(CSV_FILE_PATH_PRODUCTS);
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            String oldProductDetails = oldProduct.getProductName() + "," +
                    oldProduct.getProductDescription() + "," +
                    oldProduct.getCategory() + "," +
                    oldProduct.getImagePath();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String productDetails = fields[0] + "," + fields[1] + "," + fields[2] + "," + fields[3];

                if (productDetails.equals(oldProductDetails)) {
                    StringBuilder sb = new StringBuilder();
                    switch (newProduct.getCategory()) {
                        case "Milk Tea":
                            MilkTea milkTea = (MilkTea) newProduct;
                            sb.append(milkTea.getProductName()).append(",");
                            sb.append(milkTea.getProductDescription()).append(",");
                            sb.append(milkTea.getCategory()).append(",");
                            sb.append(milkTea.getImagePath()).append(",");
                            sb.append(milkTea.getCheckBox().isSelected()).append(",");
                            sb.append(milkTea.getSmallPrice()).append(",");
                            sb.append(milkTea.getMediumPrice()).append(",");
                            sb.append(milkTea.getLargePrice()).append(",");
                            sb.append(milkTea.getAddOnsOne()).append(",");
                            sb.append(milkTea.getAddOnsOnePrice()).append(",");
                            sb.append(milkTea.getAddOnsTwo()).append(",");
                            sb.append(milkTea.getAddOnsTwoPrice());

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            break;
                        case "Coolers":
                            Coolers coolers = (Coolers) newProduct;
                            sb.append(coolers.getProductName()).append(",");
                            sb.append(coolers.getProductDescription()).append(",");
                            sb.append(coolers.getCategory()).append(",");
                            sb.append(coolers.getImagePath()).append(",");
                            sb.append(coolers.getCheckBox().isSelected()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");

                            sb.append(coolers.getSmallPrice()).append(",");
                            sb.append(coolers.getMediumPrice()).append(",");
                            sb.append(coolers.getLargePrice()).append(",");
                            sb.append(coolers.getAddOnsOne()).append(",");
                            sb.append(coolers.getAddOnsOnePrice()).append(",");
                            sb.append(coolers.getAddOnsTwo()).append(",");
                            sb.append(coolers.getAddOnsTwoPrice()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            break;
                        case "Coffee":
                            Coffee coffee = (Coffee) newProduct;
                            sb.append(coffee.getProductName()).append(",");
                            sb.append(coffee.getProductDescription()).append(",");
                            sb.append(coffee.getCategory()).append(",");
                            sb.append(coffee.getImagePath()).append(",");
                            sb.append(coffee.getCheckBox().isSelected()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");

                            sb.append(coffee.getPrice()).append(",");

                            sb.append(",");
                            sb.append(",");
                            break;
                        case "Ice Candy Cups":
                            IceCandyCups iceCandyCups = (IceCandyCups) newProduct;
                            sb.append(iceCandyCups.getProductName()).append(",");
                            sb.append(iceCandyCups.getProductDescription()).append(",");
                            sb.append(iceCandyCups.getCategory()).append(",");
                            sb.append(iceCandyCups.getImagePath()).append(",");
                            sb.append(iceCandyCups.getCheckBox().isSelected()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");

                            sb.append(iceCandyCups.getPrice()).append(",");

                            sb.append(",");
                            break;
                        case "Appetizers":
                            Appetizer appetizer = (Appetizer) newProduct;
                            sb.append(appetizer.getProductName()).append(",");
                            sb.append(appetizer.getProductDescription()).append(",");
                            sb.append(appetizer.getCategory()).append(",");
                            sb.append(appetizer.getImagePath()).append(",");
                            sb.append(appetizer.getCheckBox().isSelected()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            sb.append(",");

                            sb.append(appetizer.getPrice());
                            break;
                    }
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

    public static boolean editProductAvailabilityInCSV(Product product) {
        File tempFile = null;

        try {
            File inputFile = new File(CSV_FILE_PATH_PRODUCTS);
            tempFile = new File(DIRECTORY_PATH_CSV + File.separator + "temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 6) {
                    continue;
                }
                String productName = fields[0];
                String productDescription = fields[1];
                String category = fields[2];
                String imagePath = fields[3];

                if (productName.equals(product.getProductName())
                        && productDescription.equals(product.getProductDescription())
                        && category.equals(product.getCategory())
                        && imagePath.equals(product.getImagePath())) {
                    fields[4] = String.valueOf(product.getCheckBox().isSelected());
                }

                String modifiedLine = String.join(",", fields);
                writer.write(modifiedLine);
                writer.newLine();
            }

            reader.close();
            writer.close();

            if(!inputFile.delete()) {
                System.out.println("Failed to delete current csv file");
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                return false;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Failed to rename temporary file to original file.");
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                return false;
            }

            System.out.println("Gumagana");
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            if (tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file.");
            }
            return false;
        }
    }


    public static boolean deleteProductInCSV(List<Product> productListToDelete) {
        boolean success = false;
        File tempFile = null;

        try {
            tempFile = new File(DIRECTORY_PATH_CSV + File.separator + "temp.csv");
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter writer = new BufferedWriter(fw);

            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_PRODUCTS));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                 boolean deleteRow = false;

                for (Product product : productListToDelete) {
                    if (fields.length >= 4 && fields[0].equals(product.getProductName()) &&
                            fields[1].equals(product.getProductDescription()) &&
                            fields[2].equals(product.getCategory()) &&
                            fields[3].equals(product.getImagePath())) {
                        deleteRow = true;

                        if (!product.getImagePath().startsWith("/com/example/")) {
                            File productImage = new File(product.getImagePath());
                            if (!productImage.delete()) {
                                System.out.println("Failed to delete product image");
                                if (tempFile.exists() && !tempFile.delete()) {
                                    System.err.println("Failed to delete temporary file.");
                                }
                            }
                        }

                        break;
                    }
                }

                if (!deleteRow) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();

            File currentFile = new File(CSV_FILE_PATH_PRODUCTS);

            if (!currentFile.delete()) {
                System.out.println("Failed to delete current products.csv file");
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                return false;
            }

            File originalFile = new File(CSV_FILE_PATH_PRODUCTS);
            if (!tempFile.renameTo(originalFile)) {
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                System.out.println("Failed to rename temporary file to original file");
                return false;
            }


            success = true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            if (!success && tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file.");
            }
        }
        return success;
    }


}
