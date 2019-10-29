package com.databasebrowser.backend.service;

import com.databasebrowser.backend.model.Table;

import java.util.List;

/**
 * Browses the structure and the data in the database using a specified database connection
 */
public interface BrowserService {
    /**
     * Retrieves a list of schemas for a specified database connection
     *
     * @param databaseConnectionId the id of the database connection
     * @return the list of schemas
     */
    List<String> getSchemasByDatabaseConnection(Long databaseConnectionId);

    /**
     * Retrieves a list of tables for a specified database connection
     *
     * @param databaseConnectionId the id of the database connection
     * @param schemaName           the name of the database schema
     * @return the list of tables
     */
    List<String> getTablesByDatabaseConnection(Long databaseConnectionId, String schemaName);

    /**
     * Retrieves a list of columns for a specified database connection
     *
     * @param databaseConnectionId the id of the database connection
     * @param schemaName           the name of the database schema
     * @param tableName            the name of the database table
     * @return the list of columns
     */
    List<String> getColumnsByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName);

    /**
     * Retrieves a Table object that includes information about the table's columns and their data types, primary keys and foreign keys
     * for a specified database connection
     *
     * @param databaseConnectionId the id of the database connection
     * @param schemaName           the name of the database schema
     * @param tableName            the name of the database table
     * @return the Table object
     */
    Table getTablePreviewByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName);
}
