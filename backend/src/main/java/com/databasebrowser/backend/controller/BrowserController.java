package com.databasebrowser.backend.controller;

import com.databasebrowser.backend.model.Table;
import com.databasebrowser.backend.service.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/browser")
public class BrowserController {

    private final BrowserService browserService;

    @Autowired
    BrowserController(BrowserService browserService) {
        this.browserService = browserService;
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas")
    public ResponseEntity<?> getSchemas(@PathVariable Long databaseConnectionId) {
        List<String> schemas = browserService.getSchemasByDatabaseConnection(databaseConnectionId);
        return new ResponseEntity<>(schemas, HttpStatus.OK);
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas/{schemaName}/tables")
    public ResponseEntity<?> getTables(@PathVariable Long databaseConnectionId, @PathVariable String schemaName) {
        List<String> tables = browserService.getTablesByDatabaseConnection(databaseConnectionId, schemaName);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas/{schemaName}/tables/{tableName}/columns")
    public ResponseEntity<?> getColumns(@PathVariable Long databaseConnectionId, @PathVariable String schemaName, @PathVariable String tableName) {
        List<String> columns = browserService.getColumnsByDatabaseConnection(databaseConnectionId, schemaName, tableName);
        return new ResponseEntity<>(columns, HttpStatus.OK);
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas/{schemaName}/tables/{tableName}/preview")
    public ResponseEntity<?> getTablePreview(@PathVariable Long databaseConnectionId, @PathVariable String schemaName, @PathVariable String tableName) {
        Table tablePreview = browserService.getTablePreviewByDatabaseConnection(databaseConnectionId, schemaName, tableName);
        return new ResponseEntity<>(tablePreview, HttpStatus.OK);
    }
}
