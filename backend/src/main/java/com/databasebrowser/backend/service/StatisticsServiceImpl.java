package com.databasebrowser.backend.service;

import com.databasebrowser.backend.configuration.HikariDataSourceConfig;
import com.databasebrowser.backend.model.ColumnStatistic;
import com.databasebrowser.backend.model.DatabaseConnection;
import com.databasebrowser.backend.model.TableStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOG = LoggerFactory.getLogger(BrowserServiceImpl.class);
    private final DatabaseConnectionService databaseConnectionService;
    private final HikariDataSourceConfig hikariDataSourceConfig;

    @Autowired
    StatisticsServiceImpl(DatabaseConnectionService databaseConnectionService, HikariDataSourceConfig hikariDataSourceConfig) {
        this.databaseConnectionService = databaseConnectionService;
        this.hikariDataSourceConfig = hikariDataSourceConfig;
    }

    @Override
    public List<ColumnStatistic> getColumnsStatisticsByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName) {
        LOG.info("Retrieving statistics of columns of the table {}.", tableName);
        ResultSet resultSetOfColumns;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ColumnStatistic> columnsStatistics = new ArrayList<>();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSetOfColumns = databaseMetaData.getColumns(null, schemaName, tableName, null);

            while (resultSetOfColumns.next()) {
                String columnName = resultSetOfColumns.getString("COLUMN_NAME");
                String typeName = resultSetOfColumns.getString("TYPE_NAME");
                if (!columnName.contains("id") && (typeName.contains("float") || typeName.contains("int"))) {
                    preparedStatement = connection.prepareStatement("SELECT MIN(" + columnName + ") FROM " + schemaName + "." + tableName);
                    double min = executeStatement(preparedStatement);

                    preparedStatement = connection.prepareStatement("SELECT MAX(" + columnName + ") FROM " + schemaName + "." + tableName);
                    double max = executeStatement(preparedStatement);

                    preparedStatement = connection.prepareStatement("SELECT AVG(" + columnName + ") FROM " + schemaName + "." + tableName);
                    double avg = executeStatement(preparedStatement);

                    preparedStatement = connection.prepareStatement("SELECT PERCENTILE_DISC(0.5) WITHIN GROUP (ORDER BY " + columnName + ") FROM " + schemaName + "." + tableName);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    double median = resultSet.getDouble(1);

                    columnsStatistics.add(new ColumnStatistic(columnName, min, max, avg, median));
                }
            }
            closeResource(preparedStatement, resultSet);
            resultSetOfColumns.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving column statistics: " + ex.getMessage());
        }

        return columnsStatistics;
    }

    @Override
    public List<TableStatistic> getTablesStatisticsByDatabaseConnection(Long databaseConnectionId, String schemaName) {
        LOG.info("Retrieving statistics of all tables in the schema {}.", schemaName);
        ResultSet resultSetOfTables = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TableStatistic> tablesStatistics = new ArrayList<>();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSetOfTables = databaseMetaData.getTables(null, schemaName, null, new String[]{"TABLE"});

            while (resultSetOfTables.next()) {
                String tableName = resultSetOfTables.getString("TABLE_NAME");

                preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + schemaName + "." + tableName);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int numberOfRecords = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("SELECT * FROM " + schemaName + "." + tableName);
                resultSet = preparedStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int numberOfAttributes = resultSetMetaData.getColumnCount();

                tablesStatistics.add(new TableStatistic(tableName, numberOfRecords, numberOfAttributes));
            }
            closeResource(preparedStatement, resultSet);
            resultSetOfTables.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving table statistics: " + ex.getMessage());
        }
        return tablesStatistics;
    }

    private Connection getConnection(Long databaseConnectionId) throws SQLException {
        DatabaseConnection databaseConnection = databaseConnectionService.getDatabaseConnectionById(databaseConnectionId);
        return hikariDataSourceConfig.getConnection(databaseConnection);
    }

    private double executeStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getDouble(1);
    }

    private void closeResource(PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}
