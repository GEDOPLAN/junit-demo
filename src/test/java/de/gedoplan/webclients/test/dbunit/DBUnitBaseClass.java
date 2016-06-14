package de.gedoplan.webclients.test.dbunit;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;

import org.junit.Before;

/**
 *
 * @author Dominik Mathmann
 */
public class DBUnitBaseClass {

    @Before
    public void initData() throws Exception {
        getConnection().getConnection().createStatement().execute("SET foreign_key_checks = 0 ");
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
        getConnection().getConnection().createStatement().execute("SET foreign_key_checks = 1 ");
    }

    private IDataSet getDataSet() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dbunit_full.xml");
        IDataSet dataset = new FlatXmlDataSet(inputStream);
        return dataset;
    }

    private IDatabaseConnection getConnection() throws Exception {
        Class driverClass = Class.forName("org.h2.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:h2:~/test;AUTO_SERVER=TRUE", "sa", "sa");
        return new DatabaseConnection(jdbcConnection);
    }
}
