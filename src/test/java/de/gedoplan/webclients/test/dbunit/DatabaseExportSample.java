package de.gedoplan.webclients.test.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DatabaseExportSample {

    public static void main(String[] args) throws Exception {
        // database connection
        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        exportSchema(connection);
        exportAllTables(connection);
    }

    public static void exportSchema(IDatabaseConnection connection) throws Exception {
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("target/northwind.dtd"));
    }

    /**
     * export all tables from schema
     *
     * @param connection
     * @throws Exception
     */
    public static void exportAllTables(IDatabaseConnection connection) throws Exception {
        IDataSet fullDataSet = new FilteredDataSet(new DatabaseSequenceFilter(connection), connection.createDataSet());
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("target/dbunit_full.xml"));
    }

    /**
     * export only defined tables / query results
     *
     * @param connection
     * @throws Exception
     */
    public static void exportPartitialTables(IDatabaseConnection connection) throws Exception {
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
        partialDataSet.addTable("BAR");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("target/partial.xml"));
    }

    /**
     * dependent tables database export: export table X and all tables that have
     * a PK which is a FK on X, in the right order for insertion
     *
     * @param connection
     */
    public static void exportDependantTables(IDatabaseConnection connection) throws Exception {
        String[] depTableNames
                = TablesDependencyHelper.getAllDependentTables(connection, "X");
        IDataSet depDataset = connection.createDataSet(depTableNames);
        FlatXmlDataSet.write(depDataset, new FileOutputStream("target/dependents.xml"));

    }

}
