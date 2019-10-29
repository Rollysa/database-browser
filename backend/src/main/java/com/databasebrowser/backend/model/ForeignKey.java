package com.databasebrowser.backend.model;

public class ForeignKey {

    private String foreignKeyName;
    private String primaryKeyTableName;
    private String primaryKeyColumnName;
    private String foreignKeyTableName;
    private String foreignKeyColumnName;

    public ForeignKey() {
    }

    public ForeignKey(String foreignKeyName, String primaryKeyTableName, String primaryKeyColumnName, String foreignKeyTableName, String foreignKeyColumnName) {
        this.foreignKeyName = foreignKeyName;
        this.primaryKeyTableName = primaryKeyTableName;
        this.primaryKeyColumnName = primaryKeyColumnName;
        this.foreignKeyTableName = foreignKeyTableName;
        this.foreignKeyColumnName = foreignKeyColumnName;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public String getPrimaryKeyTableName() {
        return primaryKeyTableName;
    }

    public void setPrimaryKeyTableName(String primaryKeyTableName) {
        this.primaryKeyTableName = primaryKeyTableName;
    }

    public String getPrimaryKeyColumnName() {
        return primaryKeyColumnName;
    }

    public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
        this.primaryKeyColumnName = primaryKeyColumnName;
    }

    public String getForeignKeyTableName() {
        return foreignKeyTableName;
    }

    public void setForeignKeyTableName(String foreignKeyTableName) {
        this.foreignKeyTableName = foreignKeyTableName;
    }

    public String getForeignKeyColumnName() {
        return foreignKeyColumnName;
    }

    public void setForeignKeyColumnName(String foreignKeyColumnName) {
        this.foreignKeyColumnName = foreignKeyColumnName;
    }
}
