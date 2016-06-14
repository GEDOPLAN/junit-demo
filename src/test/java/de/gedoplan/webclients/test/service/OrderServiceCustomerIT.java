package de.gedoplan.webclients.test.service;

import de.gedoplan.webclients.service.*;
import de.gedoplan.webclients.model.Order;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.test.TestBaseClass;
import de.gedoplan.webclients.testhelper.CustomerCaller;
import java.util.concurrent.Callable;
import javax.ejb.EJBAccessException;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class OrderServiceCustomerIT extends TestBaseClass {

    @Inject
    private OrderService orderService;

    @Inject
    private CustomerCaller customer;

    @Test
    public void queryOrders() throws Exception {
        QuerySettings customerQuery = new QuerySettings();
        customer.call(new Callable() {
            @Override
            public Object call() throws Exception {
                QueryResult<Order> orders = orderService.queryOrders(customerQuery);
                Assert.assertTrue(orders.getResult().size() == 2);
                return null;
            }
        });

    }

    @Test
    public void getOrder() throws Exception {
        customer.call(new Callable() {
            @Override
            public Object call() throws Exception {

                try {
                    orderService.getOrder(10250);
                    Assert.fail("Test should fail because of unallowed loading of order 10250 from customter");
                } catch (Exception e) {
                    //all fine
                }

                return null;
            }
        });
    }

}
