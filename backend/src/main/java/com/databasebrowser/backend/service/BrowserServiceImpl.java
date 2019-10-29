package com.databasebrowser.backend.service;

import com.databasebrowser.backend.configuration.HikariDataSourceConfig;
import com.databasebrowser.backend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrowserServiceImpl implements BrowserService {

    private static final Logger LOG = LoggerFactory.getLogger(BrowserServiceImpl.class);
    private final DatabaseConnectionService databaseConnectionService;
    private final HikariDataSourceConfig hikariDataSourceConfig;
    private Connection connection;

    @Autowired
    BrowserServiceImpl(DatabaseConnectionService databaseConnectionService, HikariDataSourceConfig hikariDataSourceConfig) {
        this.databaseConnectionService = databaseConnectionService;
        this.hikariDataSourceConfig = hikariDataSourceConfig;
    }

    @Override
    public List<String> getSchemasByDatabaseConnection(Long databaseConnectionId) {
        LOG.info("Retrieving all schemas using the database connection ID {}.", databaseConnectionId);
        List<String> schemas = new ArrayList<>();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSetOfSchemas = databaseMetaData.getSchemas();
            while (resultSetOfSchemas.next()) {
                String schemaName = resultSetOfSchemas.getString("TABLE_SCHEM");
                schemas.add(schemaName);
            }
            resultSetOfSchemas.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving schemas: " + ex.getMessage());
        }
        return schemas;
    }

    @Override
    public List<String> getTablesByDatabaseConnection(Long databaseConnectionId, String schemaName) {
        LOG.info("Retrieving all tables of the schema {} using the database connection ID {}.", schemaName, databaseConnectionId);
        List<String> tables = new ArrayList<>();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSetOfTables = databaseMetaData.getTables(null, schemaName, null, new String[]{"TABLE"});
            while (resultSetOfTables.next()) {
                String tableName = resultSetOfTables.getString("TABLE_NAME");
                tables.add(tableName);
            }
            resultSetOfTables.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving tables: " + ex.getMessage());
        }
        return tables;
    }

    @Override
    public List<String> getColumnsByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName) {
        LOG.info("Retrieving all columns of the table {} in the schema {} using the database connection ID {}.", schemaName, tableName, databaseConnectionId);
        List<String> columns = new ArrayList<>();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSetOfColumns = databaseMetaData.getColumns(null, schemaName, tableName, null);

            while (resultSetOfColumns.next()) {
                String columnName = resultSetOfColumns.getString("COLUMN_NAME");
                columns.add(columnName);
            }
            resultSetOfColumns.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving columns: " + ex.getMessage());
        }
        return columns;
    }

    @Override
    public Table getTablePreviewByDatabaseConnection(Long databaseConnectionId, String schemaName, String tableName) {
        LOG.info("Retrieving a data preview of the table {} in the schema {} using the database connection ID {}. ", schemaName, tableName,
                databaseConnectionId);
        List<Column> columns = new ArrayList<>();
        List<ForeignKey> foreignKeys = new ArrayList<>();
        Table table = new Table();
        try (Connection connection = getConnection(databaseConnectionId)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, schemaName, tableName, null);
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                String typeName = resultSet.getString("TYPE_NAME");
                int columnSize = resultSet.getInt("COLUMN_SIZE");
                String isNullable = resultSet.getString("IS_NULLABLE");
                String isAutoIncrement = resultSet.getString("IS_AUTOINCREMENT");
                columns.add(new Column(columnName, typeName, columnSize, isNullable, isAutoIncrement));
                table.setColumns(columns);
            }
            resultSet = databaseMetaData.getPrimaryKeys(null, schemaName, tableName);
            while (resultSet.next()) {
                String primaryKeyColumnName = resultSet.getString("COLUMN_NAME");
                String primaryKeyName = resultSet.getString("PK_NAME");
                table.setPrimaryKey(new PrimaryKey(primaryKeyColumnName, primaryKeyName));
            }
            resultSet = databaseMetaData.getImportedKeys(null, schemaName, tableName);
            while (resultSet.next()) {
                String foreignKeyName = resultSet.getString("FK_NAME");
                String primaryKeyTableName = resultSet.getString("PKTABLE_NAME");
                String primaryKeyColumnName = resultSet.getString("PKCOLUMN_NAME");
                String foreignKeyTableName = resultSet.getString("FKTABLE_NAME");
                String foreignKeyColumnName = resultSet.getString("FKCOLUMN_NAME");
                foreignKeys.add(new ForeignKey(foreignKeyName, primaryKeyTableName, primaryKeyColumnName, foreignKeyTableName, foreignKeyColumnName));
                table.setForeignKeys(foreignKeys);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new IllegalStateException("An error during retrieving data preview of the table: " + ex.getMessage());
        }
        return table;
    }

    private Connection getConnection(Long databaseConnectionId) throws SQLException {
        DatabaseConnection databaseConnection = databaseConnectionService.getDatabaseConnectionById(databaseConnectionId);
        return hikariDataSourceConfig.getConnection(databaseConnection);
    }
}
