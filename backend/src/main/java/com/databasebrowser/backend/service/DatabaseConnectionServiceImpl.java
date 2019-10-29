package com.databasebrowser.backend.service;

import com.databasebrowser.backend.model.DatabaseConnection;
import com.databasebrowser.backend.respository.DatabaseConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(BrowserServiceImpl.class);

    private final DatabaseConnectionRepository databaseConnectionRepository;

    @Autowired
    DatabaseConnectionServiceImpl(DatabaseConnectionRepository databaseConnectionRepository) {
        this.databaseConnectionRepository = databaseConnectionRepository;
    }

    @Override
    public DatabaseConnection createDatabaseConnection(DatabaseConnection databaseConnection) {
        LOG.info("Creating a new database connection.");
        return databaseConnectionRepository.save(databaseConnection);
    }

    @Override
    public DatabaseConnection getDatabaseConnectionById(Long databaseConnectionId) {
        LOG.info("Retrieving the database connection with ID {}.", databaseConnectionId);
        return databaseConnectionRepository.findById(databaseConnectionId).orElseThrow(() -> new EntityNotFoundException("The database connection with ID " + databaseConnectionId + " not found"));
    }

    @Override
    public DatabaseConnection updateDatabaseConnectionById(Long databaseConnectionId, DatabaseConnection databaseConnection) {
        LOG.info("Updating the database connection with ID {}.", databaseConnectionId);
        DatabaseConnection updatedDatabaseConnection = databaseConnectionRepository.findById(databaseConnectionId).orElseThrow(() -> new EntityNotFoundException("Database connection not found"));
        updatedDatabaseConnection = databaseConnection;
        updatedDatabaseConnection.setId(databaseConnectionId);
        return databaseConnectionRepository.save(updatedDatabaseConnection);
    }

    @Override
    public List<DatabaseConnection> getAllDatabaseConnections() {
        LOG.info("Retrieving all database connections.");
        return databaseConnectionRepository.findAll();
    }

    @Override
    public void deleteDatabaseConnectionById(Long databaseConnectionId) {
        LOG.info("Deleting the database connection with ID {}.", databaseConnectionId);
        databaseConnectionRepository.findById(databaseConnectionId).orElseThrow(() -> new EntityNotFoundException("The database connection with ID " + databaseConnectionId + " not found"));
        databaseConnectionRepository.deleteById(databaseConnectionId);
    }
}
