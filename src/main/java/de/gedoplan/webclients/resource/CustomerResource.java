package de.gedoplan.webclients.resource;

import com.fasterxml.jackson.annotation.JsonView;
import de.gedoplan.webclients.model.Customer;
import de.gedoplan.webclients.model.dto.CustomerOrderInformation;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.resource.views.DetailView;
import de.gedoplan.webclients.resource.views.ListView;
import de.gedoplan.webclients.service.CustomerService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("customer")
@Consumes("application/json")
@Produces("application/json")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @JsonView(ListView.class)
    public QueryResult getCustomer(
            @QueryParam("max") Integer max,
            @QueryParam("start") Integer start,
            @QueryParam("sortAttributes") String sortAttributes[],
            @QueryParam("sortDirections") String sortDirections[]) {

        QuerySettings settings = new QuerySettings(start, max, sortAttributes, sortDirections);
        return customerService.queryCustomer(settings);
    }

    @GET
    @Path("detail/{id}")
    @JsonView(DetailView.class)
    public Customer getCustomerById(@PathParam("id") String customerID) {
        return customerService.getCustomerById(customerID);
    }

    @GET
    @Path("discount/{id}")
    public CustomerOrderInformation getCustomerOrderInformation(@PathParam("id") String customerId) {
        return customerService.calculateCustomerDiscount(customerId);
    }

    @PUT
    @JsonView(DetailView.class)
    public Customer updateCustomer(@JsonView(DetailView.class) Customer customer) {
        return customerService.updateCustomer(customer);
    }

}
