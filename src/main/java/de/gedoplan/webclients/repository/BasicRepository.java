package de.gedoplan.webclients.repository;

import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QuerySettings;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

abstract class BasicRepository<ENTITY, ID> {

    @PersistenceContext(name = "webclients")
    EntityManager entityManager;

    private final Class<ENTITY> entityClass;

    private BasicRepository() {
        this.entityClass = null;
    }

    public BasicRepository(Class<ENTITY> entityKlasse) {
        this.entityClass = entityKlasse;
    }

    protected ENTITY findById(ID id) {
        return this.entityManager.find(entityClass, id);
    }

    protected <Y> List<ENTITY> findByAttribute(SingularAttribute<ENTITY, Y> attributename, Y attributevalue) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = cb.createQuery(entityClass);
        Root<ENTITY> root = query.from(entityClass);
        query.where(cb.equal(root.get(attributename), attributevalue));

        return this.getEntityManager().createQuery(query).getResultList();
    }

    protected <Y> Long countByAttribute(SingularAttribute<ENTITY, Y> attributename, Y attributevalue) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ENTITY> root = query.from(entityClass);
        query.select(cb.count(root));
        query.where(cb.equal(root.get(attributename), attributevalue));

        return this.getEntityManager().createQuery(query).getSingleResult();
    }

    protected Long count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ENTITY> root = query.from(entityClass);
        query.select(cb.count(root));

        return this.getEntityManager().createQuery(query).getSingleResult();
    }

    protected ENTITY merge(ENTITY entity) {
        return this.entityManager.merge(entity);
    }

    protected void delete(Integer id) {
        this.entityManager.remove(this.entityManager.getReference(entityClass, id));
    }

    protected List<ENTITY> getAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = cb.createQuery(entityClass);
        Root<ENTITY> root = query.from(entityClass);

        return this.getEntityManager().createQuery(query).getResultList();
    }

    protected List<ENTITY> getAll(SingularAttribute<ENTITY, ?> sortAttribut, boolean descending) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = cb.createQuery(entityClass);
        Root<ENTITY> root = query.from(entityClass);

        if (descending) {
            query.orderBy(cb.desc(root.get(sortAttribut)));
        } else {
            query.orderBy(cb.asc(root.get(sortAttribut)));
        }
        return this.getEntityManager().createQuery(query).getResultList();
    }

    protected List<ENTITY> getAll(SingularAttribute<ENTITY, ?> sortAttribut, boolean descending, int max, int start) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = cb.createQuery(entityClass);
        Root<ENTITY> root = query.from(entityClass);

        if (descending) {
            query.orderBy(cb.desc(root.get(sortAttribut)));
        } else {
            query.orderBy(cb.asc(root.get(sortAttribut)));
        }
        TypedQuery<ENTITY> typedQuery = this.getEntityManager().createQuery(query);
        typedQuery.setMaxResults(max);
        typedQuery.setFirstResult(start);

        return typedQuery.getResultList();
    }

    protected QueryResult<ENTITY> search(QuerySettings settings) {
        List<ENTITY> result = searchEntities(settings);
        Long count = searchCount(settings);
        return new QueryResult<>(result, count);
    }

    protected List<ENTITY> searchEntities(QuerySettings settings) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> query = cb.createQuery(entityClass);
        Root<ENTITY> root = query.from(entityClass);

        Order[] sortings = retrieveSorting(settings, cb, root);
        Predicate[] filters = retrieveFilter(settings, cb, root);

        query.where(filters);
        query.orderBy(sortings);
        query.select(root);

        TypedQuery<ENTITY> typedQuery = this.getEntityManager().createQuery(query);
        typedQuery.setMaxResults(settings.getMax() == null ? Integer.MAX_VALUE : settings.getMax());
        typedQuery.setFirstResult(settings.getStart() == null ? 0 : settings.getStart());

        return typedQuery.getResultList();
    }

    protected Long searchCount(QuerySettings settings) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ENTITY> root = query.from(entityClass);

        Predicate[] filters = retrieveFilter(settings, cb, root);

        query.where(filters);
        query.select(cb.count(root));

        TypedQuery<Long> typedQuery = this.getEntityManager().createQuery(query);
        return typedQuery.getSingleResult();
    }

    private Order[] retrieveSorting(QuerySettings settings, CriteriaBuilder cb, Root<ENTITY> root) {
        return settings.getSorting().keySet().stream().map((sortKey) -> {
            if (settings.getSorting().get(sortKey).equals("desc")) {
                return cb.desc(root.get(sortKey));
            } else {
                return cb.asc(root.get(sortKey));
            }
        }).toArray(Order[]::new);
    }

    private Predicate[] retrieveFilter(QuerySettings settings, CriteriaBuilder cb, Root<ENTITY> root) {
        return settings.getFilter().keySet().stream().map((filterKey) -> {
            return cb.equal(root.get(filterKey), settings.getFilter().get(filterKey));
        }).toArray(Predicate[]::new);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
