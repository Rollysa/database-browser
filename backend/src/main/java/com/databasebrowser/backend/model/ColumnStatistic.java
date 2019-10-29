package com.databasebrowser.backend.model;

public class ColumnStatistic {

    private String columnName;
    private double min;
    private double max;
    private double avg;
    private double medium;

    public ColumnStatistic() {
    }

    public ColumnStatistic(String columnName, double min, double max, double avg, double medium) {
        this.columnName = columnName;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.medium = medium;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }
}
