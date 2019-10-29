package com.databasebrowser.backend.model;

public class TableStatistic {

    private String tableName;
    private int numberOfRecords;
    private int numberOfAttributes;

    public TableStatistic() {
    }

    public TableStatistic(String tableName, int numberOfRecords, int numberOfAttributes) {
        this.tableName = tableName;
        this.numberOfRecords = numberOfRecords;
        this.numberOfAttributes = numberOfAttributes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public void setNumberOfAttributes(int numberOfAttributes) {
        this.numberOfAttributes = numberOfAttributes;
    }
}
