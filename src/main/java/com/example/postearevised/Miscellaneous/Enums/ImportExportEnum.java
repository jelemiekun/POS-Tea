package com.example.postearevised.Miscellaneous.Enums;

public enum ImportExportEnum {
    IMPORT_EXPORT_ENUM("Import/Export Menu"),
    IMPORT_ENUM("Import Menu"),
    EXPORT_ENUM("Export Menu");

    private final String importOperation;

    ImportExportEnum(String importOperation) {
        this.importOperation = importOperation;
    }

    public String getImportOperation() {
        return importOperation;
    }
}
