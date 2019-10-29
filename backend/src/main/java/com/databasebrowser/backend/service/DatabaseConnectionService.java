package com.databasebrowser.backend.service;

import com.databasebrowser.backend.model.DatabaseConnection;

import java.util.List;

/**
 * CRUD operations over a database connection
 */
public interface DatabaseConnectionService {

    /**
     * Creates a new connection in the database
     *
     * @param databaseConnection the connection of the database
     * @return the added database connection
     */
    DatabaseConnection createDatabaseConnection(DatabaseConnection databaseConnection);

    /**
     * Retrieves a specified connection from the database
     *
     * @param databaseConnectionId the id of the database connection
     * @return the database connection
     */
    DatabaseConnection getDatabaseConnectionById(Long databaseConnectionId);

    /**
     * Updates a specified connection in the database
     *
     * @param databaseConnectionId the id of the database connection
     * @param databaseConnection   the connection of the database
     * @return the updated database connection
     */
    DatabaseConnection updateDatabaseConnectionById(Long databaseConnectionId, DatabaseConnection databaseConnection);

    /**
     * Retrieves all connections from the database
     *
     * @return the list of all database connections
     */
    List<DatabaseConnection> getAllDatabaseConnections();

    /**
     * Removes a specified connection in the database
     *
     * @param databaseConnectionId the id of the database connection
     */
    void deleteDatabaseConnectionById(Long databaseConnectionId);
}
