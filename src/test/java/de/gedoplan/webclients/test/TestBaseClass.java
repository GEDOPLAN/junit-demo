package de.gedoplan.webclients.test;

import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 *
 * @author dmathmann
 */
public class TestBaseClass {

    @Deployment()
    public static WebArchive createDeployment() {
        WebArchive deployment = ShrinkWrap
                .create(WebArchive.class, "junit-demo-test.war")
                .as(ZipImporter.class)
                .importFrom(new File("target/junit-demo.war"))
                .as(WebArchive.class)
                .addPackage("de.gedoplan.webclients.test")
                .addPackage("de.gedoplan.webclients.testhelper")
                .addAsWebInfResource(new File("src/test/resources/beans.xml"));

        return deployment;
    }

}