package com.example.postearevised.Miscellaneous.Enums;

public enum ImportExport {
    Import("Import CSV"),
    Export("Export CSV");

    private final String importOperation;

    ImportExport(String importOperation) {
        this.importOperation = importOperation;
    }

    public String getImportOperation() {
        return importOperation;
    }
}
