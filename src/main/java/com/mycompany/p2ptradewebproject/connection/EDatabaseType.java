package com.mycompany.p2ptradewebproject.connection;

public enum EDatabaseType {
    MYSQL("database_mysql");

    private final String propertiesFile;

    EDatabaseType(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public String getPropertiesFile() {
        return propertiesFile;
    }
}
