package de.gedoplan.webclients.service;

import de.gedoplan.webclients.model.Customer;
import de.gedoplan.webclients.model.User;
import de.gedoplan.webclients.model.dto.CustomerOrderInformation;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.repository.CustomerRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CustomerService {

    @Inject
    private CustomerRepository customerRepostory;

    private User user;

    public QueryResult<Customer> queryCustomer(QuerySettings settings) {
        return customerRepostory.queryCustomer(settings);
    }

    public Customer getCustomerById(String customerID) {
        return customerRepostory.getCustomerByID(customerID);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepostory.updateCustomer(customer);
    }

    public CustomerOrderInformation calculateCustomerDiscount(String customerId) {
        CustomerOrderInformation orderInformation = customerRepostory.getCustomerOrderInfomration(customerId);
        Double totalAmount = orderInformation.getTotalAmount();
        Double discount = 0.;

        if (totalAmount > 10000) {
            discount = 5.;
        } else if (totalAmount > 5000) {
            discount = 3.;
        } else if (totalAmount > 1000) {
            discount = 2.5;
        }

        orderInformation.setDiscount(discount);

        return orderInformation;
    }
}
