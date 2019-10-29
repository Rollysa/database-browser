package com.databasebrowser.backend.model;

public class Column {

    private String columnName;
    private String typeName;
    private int columnSize;
    private String isNullable;
    private String isAutoIncrement;

    public Column() {
    }

    public Column(String columnName, String typeName, int columnSize, String isNullable, String isAutoIncrement) {
        this.columnName = columnName;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.isNullable = isNullable;
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(String isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }
}
