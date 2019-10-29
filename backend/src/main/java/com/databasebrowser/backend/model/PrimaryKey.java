package com.databasebrowser.backend.model;

public class PrimaryKey {

    private String primaryKeyColumnName;
    private String primaryKeyName;

    public PrimaryKey() {
    }

    public PrimaryKey(String primaryKeyColumnName, String primaryKeyName) {
        this.primaryKeyColumnName = primaryKeyColumnName;
        this.primaryKeyName = primaryKeyName;
    }

    public String getPrimaryKeyColumnName() {
        return primaryKeyColumnName;
    }

    public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
        this.primaryKeyColumnName = primaryKeyColumnName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }
}
