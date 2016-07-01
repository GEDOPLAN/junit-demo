package de.gedoplan.webclients.test.service;

import de.gedoplan.webclients.model.Customer;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.service.CustomerService;

import de.gedoplan.webclients.test.TestBaseClass;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testfälle für CustomerService.
 *
 * @author Dominik Mathmann
 */


@RunWith(Arquillian.class)
public class CustomerServiceIT extends TestBaseClass {

    @Inject
    CustomerService service;

    @Test
    public void queryCustomer() {
        QuerySettings settings = new QuerySettings(0, 10);
        final QueryResult<Customer> result = service.queryCustomer(settings);

        Assert.assertTrue(result.getResult().size() == 10);
    }

    @Test
    public void getCustomerById() {
        Assert.assertNotNull(service.getCustomerById("ALFAA"));
    }

    @Test
    public void updateCustomer() {
        Customer customer = service.getCustomerById("ALFAA");
        customer.setContactName("JUNIT");
        service.updateCustomer(customer);

        Assert.assertEquals(service.getCustomerById("ALFAA").getContactName(), "JUNIT");
    }

    @Test
    public void calculateCustomerDiscount() {
        Assert.assertTrue(service.calculateCustomerDiscount("ALFAA").getDiscount() == 2.5);
    }
}
