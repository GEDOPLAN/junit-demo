package de.gedoplan.webclients.jsf.controller;

import de.gedoplan.webclients.model.Customer;
import de.gedoplan.webclients.model.dto.CustomerOrderInformation;
import de.gedoplan.webclients.service.CustomerService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CustomerDetailController implements BaseController, Serializable {

    private String customerID;

    private Customer customer;

    @Inject
    private CustomerService customerService;

    public String saveCustomer() {
        customer = customerService.updateCustomer(customer);
        addInfoMessage("save_success");
        return null;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public CustomerOrderInformation getCustomerDiscount() {
        return customerService.calculateCustomerDiscount(customer.getCustomerID());
    }

    public Customer getCustomer() {
        if (customer == null || !customer.getCustomerID().equals(customerID)) {
            customer = customerService.getCustomerById(customerID);
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
