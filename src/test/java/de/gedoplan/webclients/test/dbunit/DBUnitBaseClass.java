package de.gedoplan.webclients.test.dbunit;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import org.junit.Before;

/**
 * Basisklasse um Testdaten zu laden.
 *
 * In diesem Beispiel laden wir immer dasselbe Set von Daten (Full) was bei
 * großen Datenbanken natürlich die Tests enorm verlangsamt. Alterantiv könnten
 * mit DBUnit auch nur einzelne Tabellen exportiert je nach Test importiert
 * werden oder wir wrapppen unsere Tests in einer Suit die dafür sorgt das
 * unsere Testdaten nur einmal geladen werden ( was die Unabhänigkeit der Tests
 * allerdings einschränkt)
 *
 * @author Dominik Mathmann
 */
public class DBUnitBaseClass {

    /**
     * Wir nutzen eine H2 Datenbank die wir bei der Befüllung anweisen die
     * Constraints zu deaktieren.
     *
     * @throws Exception
     */
    @Before
    public void initData() throws Exception {
        getConnection().getConnection().createStatement().execute("SET foreign_key_checks = 0 ");
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
        getConnection().getConnection().createStatement().execute("SET foreign_key_checks = 1 ");
    }

    /**
     * Daten aus XML laden
     *
     * @return
     * @throws Exception
     */
    private IDataSet getDataSet() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dbunit_full.xml");
        IDataSet dataset = new FlatXmlDataSet(inputStream);
        return dataset;
    }

    /**
     * Aufbau einer eigenen Datenbank Verbindung.
     *
     * @return
     * @throws Exception
     */
    private IDatabaseConnection getConnection() throws Exception {
        Class driverClass = Class.forName("org.h2.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:h2:~/junit;AUTO_SERVER=TRUE", "sa", "sa");
        return new DatabaseConnection(jdbcConnection);
    }

    @Resource(name = "java:jboss/datasources/junit")
    DataSource connection;

    /**
     * In dieser Methode lassen wir uns die Datasource aus dem ApplicationServer
     * geben. Ein wenig Vorsicht ist hier geboten: oben löschen wir alle Daten
     * dieser Datenbank, also muss sichergestellt werden das hier wirklich eine
     * separate DB konfiguriet ist
     *
     * @return
     * @throws Exception
     */
    private IDatabaseConnection getConnectionInject() throws Exception {
        final Connection conn = connection.getConnection();
        System.out.println("Connection: " + conn.getCatalog() + ": " + conn);
        return new DatabaseConnection(conn);
    }
}
