package de.gedoplan.webclients.repository;

import de.gedoplan.webclients.model.Customer;
import de.gedoplan.webclients.model.Order;
import de.gedoplan.webclients.model.Order_;
import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequestScoped
public class OrderRepository extends BasicRepository<Order, Integer> {

    public OrderRepository() {
        super(Order.class);
    }

    public QueryResult queryOrders(QuerySettings settings) {
        return search(settings);
    }

    public QueryResult queryCustomerOrders(String customerID, QuerySettings settings) {
        settings.getFilter().put("customer", getEntityManager().getReference(Customer.class, customerID));
        return search(settings);
    }

    public Order findOrder(Integer id) {
        return super.findById(id);
    }

    public Order findCustomerOrder(String customerId, Integer orderId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root);
        Predicate orderIdPredicate = cb.equal(root.get(Order_.orderID), orderId);
        Predicate customerPredicate = cb.equal(root.get(Order_.customer), getEntityManager().getReference(Customer.class, customerId));

        query.where(orderIdPredicate, customerPredicate);

        return getEntityManager().createQuery(query).getSingleResult();
    }

}
