package de.gedoplan.webclients.test.service;

import de.gedoplan.webclients.service.*;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.test.TestBaseClass;
import de.gedoplan.webclients.testhelper.AdminCaller;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testfälle für OrderSerice.
 *
 * Aufrufe erfolgen als "admin".
 *
 * @author Dominik Mathmann
 */
@RunWith(Arquillian.class)
public class OrderServiceAdminTest extends TestBaseClass {

    @Inject
    private OrderService orderService;

    @Inject
    private AdminCaller admin;

    @Test
    public void queryOrders() throws Exception {
        QuerySettings adminQuery = new QuerySettings();
        admin.call(new Callable() {
            @Override
            public Object call() throws Exception {
                Assert.assertTrue(orderService.queryOrders(adminQuery).getResult().size() == 830);
                adminQuery.setMax(10);
                Assert.assertTrue(orderService.queryOrders(adminQuery).getResult().size() == 10);
                return null;
            }
        });

    }

    @Test
    public void getOrder() throws Exception {
        admin.call(new Callable() {
            @Override
            public Object call() throws Exception {
                Assert.assertNotNull(orderService.getOrder(10250));
                return null;
            }
        });
    }

}
