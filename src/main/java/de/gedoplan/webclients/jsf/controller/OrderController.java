package de.gedoplan.webclients.jsf.controller;

import de.gedoplan.webclients.model.Order;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import de.gedoplan.webclients.service.OrderService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class OrderController extends BaseTableController<Order> implements BaseController, Serializable {

    @Inject
    private OrderService customerService;

    @Override
    public QueryResult<Order> loadData(Integer pageSize, Integer pageStart, String[] sortAttributes, String[] sortDirections) {
        QuerySettings querySettings = new QuerySettings(pageStart, pageSize, sortAttributes, sortDirections);
        QueryResult<Order> queryResult = customerService.queryOrders(querySettings);

        return queryResult;
    }

    @Override
    public Object getRowKey(Order customer) {
        return customer.getOrderID();
    }
}
