package de.gedoplan.webclients.resource;

import com.fasterxml.jackson.annotation.JsonView;
import de.gedoplan.webclients.model.Order;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.resource.views.DetailView;
import de.gedoplan.webclients.resource.views.ListView;
import de.gedoplan.webclients.service.OrderService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("order")
@Consumes("application/json")
@Produces("application/json")
public class OrderResource {

    @Inject
    private OrderService orderService;

    @GET
    @JsonView(ListView.class)
    public QueryResult getOrders(
            @QueryParam("max") Integer max,
            @QueryParam("start") Integer start,
            @QueryParam("sortAttributes") String sortAttributes[],
            @QueryParam("sortDirections") String sortDirections[]) {

        QuerySettings settings = new QuerySettings(start, max, sortAttributes, sortDirections);
        return orderService.queryOrders(settings);
    }

    @GET
    @Path("details/{id}")
    @JsonView(DetailView.class)
    public Order getOrder(@PathParam("id") Integer orderId) {
        return orderService.getOrder(orderId);
    }

}
