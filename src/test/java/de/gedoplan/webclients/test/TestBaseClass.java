package de.gedoplan.webclients.test;

import de.gedoplan.webclients.test.dbunit.DBUnitBaseClass;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Basis-Klass für alle unsere Tests die das Deployment bereit stellt. In diesem
 * Projekt verwenden wir das komplette war-File. Alterantiv könnte hier mit der
 * ShrinkWrap-Api auch ein individuelles Deployment zusammen gestellt werden
 *
 * @author Dominik Mathmann
 */
public class TestBaseClass extends DBUnitBaseClass {

    /**
     * Erzeugt Deyployment aus packetiertem WAR.
     *
     * @return WebArchiv-Deployment
     */
    @Deployment()
    public static WebArchive createDeployment() {
        WebArchive deployment = ShrinkWrap
                .create(WebArchive.class, "junit-demo-test.war")
                .as(ZipImporter.class)
                .importFrom(new File("target/junit-demo.war"))
                .as(WebArchive.class)
                .addPackage("de.gedoplan.webclients.test")
                .addPackage("de.gedoplan.webclients.testhelper")
                .addPackage("de.gedoplan.webclients.test.dbunit")
                .addAsResource(new File("src/test/resources/dbunit_full.xml"))
                .addAsWebInfResource(new File("src/test/resources/beans.xml"));

        deployment.delete("META-INF/persistence.xml");
        deployment.addAsResource("test-persistence.xml", "META-INF/persistence.xml");

        return deployment;
    }

}
