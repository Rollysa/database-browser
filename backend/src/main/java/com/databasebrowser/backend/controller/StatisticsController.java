package com.databasebrowser.backend.controller;

import com.databasebrowser.backend.model.ColumnStatistic;
import com.databasebrowser.backend.model.TableStatistic;
import com.databasebrowser.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas/{schemaName}/tables/{tableName}/columns")
    public ResponseEntity<?> getColumnsStatistics(@PathVariable Long databaseConnectionId, @PathVariable String schemaName,
                                                  @PathVariable String tableName) {
        List<ColumnStatistic> columnsStatistics = statisticsService.getColumnsStatisticsByDatabaseConnection(databaseConnectionId, schemaName, tableName);
        return new ResponseEntity<>(columnsStatistics, HttpStatus.OK);
    }

    @GetMapping("/database-connection/{databaseConnectionId}/schemas/{schemaName}/tables")
    public ResponseEntity<?> getTablesStatistics(@PathVariable Long databaseConnectionId, @PathVariable String schemaName) {
        List<TableStatistic> tablesStatistics = statisticsService.getTablesStatisticsByDatabaseConnection(databaseConnectionId, schemaName);
        return new ResponseEntity<>(tablesStatistics, HttpStatus.OK);
    }
}
