package com.databasebrowser.backend.service;

import com.databasebrowser.backend.model.ColumnStatistic;
import com.databasebrowser.backend.model.TableStatistic;

import java.util.List;

/**
 * Provides statistics about columns and tables using a specified database connection
 */
public interface StatisticsService {
    /**
     * Retrieves statistics that contains minimum, maximum, average and median value about each column of the specific table for a specified database
     * connection
     *
     * @param databaseConnectionId the id of the database connection
     * @param schemaName           the name of the database schema
     * @param tableName            the name of the database table
     * @return the list of ColumnStatistic objects
     */
    List<ColumnStatistic> getColumnsStatisticsByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName);

    /**
     * Retrieves statistics that contains number of records and number of attributes about each table of the specified schemas for a specified database
     * connection
     *
     * @param databaseConnectionId the id of the database connection
     * @param schemaName           the name of the database schema
     * @return the list of TableStatistic objects
     */
    List<TableStatistic> getTablesStatisticsByDatabaseConnection(Long databaseConnectionId, String schemaName);
}
