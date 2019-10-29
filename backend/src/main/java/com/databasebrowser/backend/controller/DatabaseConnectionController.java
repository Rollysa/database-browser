package com.databasebrowser.backend.controller;

import com.databasebrowser.backend.model.DatabaseConnection;
import com.databasebrowser.backend.service.DatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/database-connection")
public class DatabaseConnectionController {

    private final DatabaseConnectionService databaseConnectionService;

    @Autowired
    DatabaseConnectionController(DatabaseConnectionService databaseConnectionService) {
        this.databaseConnectionService = databaseConnectionService;
    }

    @PostMapping()
    public ResponseEntity<?> addDatabaseConnection(@Valid @RequestBody DatabaseConnection databaseConnection) {
        DatabaseConnection addedDatabaseConnection = databaseConnectionService.createDatabaseConnection(databaseConnection);
        return new ResponseEntity<>(addedDatabaseConnection, HttpStatus.CREATED);
    }

    @GetMapping("/{databaseConnectionId}")
    public ResponseEntity<?> getDatabaseConnection(@PathVariable Long databaseConnectionId) {
        DatabaseConnection databaseConnection = databaseConnectionService.getDatabaseConnectionById(databaseConnectionId);
        return new ResponseEntity<>(databaseConnection, HttpStatus.OK);
    }

    @PutMapping("/{databaseConnectionId}")
    public ResponseEntity<?> updateDatabaseConnection(@PathVariable Long databaseConnectionId, @Valid @RequestBody DatabaseConnection databaseConnection) {
        DatabaseConnection updatedDatabaseConnection = databaseConnectionService.updateDatabaseConnectionById(databaseConnectionId, databaseConnection);
        return new ResponseEntity<>(updatedDatabaseConnection, HttpStatus.OK);
    }

    @GetMapping()
    public List<DatabaseConnection> getAll() {
        return databaseConnectionService.getAllDatabaseConnections();
    }

    @DeleteMapping("/{databaseConnectionId}")
    public ResponseEntity<?> deleteDatabaseConnection(@PathVariable Long databaseConnectionId) {
        databaseConnectionService.deleteDatabaseConnectionById(databaseConnectionId);
        return new ResponseEntity<>("The connection with ID '" + databaseConnectionId + "' has been deleted!", HttpStatus.OK);
    }
}
