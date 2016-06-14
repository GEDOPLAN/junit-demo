package de.gedoplan.webclients.jsf.controller;

import de.gedoplan.webclients.model.dto.QueryResult;
import de.gedoplan.webclients.model.dto.QueryResult;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class BaseTableController<ENTITY> extends LazyDataModel<ENTITY> {

    public abstract QueryResult<ENTITY> loadData(Integer pageSize, Integer pageStart, String[] sortAttributes, String[] sortDirections);

    @Override
    public abstract Object getRowKey(ENTITY object);


    @Override
    public List<ENTITY> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        String[] sortOrders = null;
        String[] sortAttributes = null;
        if (sortField != null && !sortField.isEmpty()) {
            sortOrders = new String[]{sortOrder == SortOrder.ASCENDING ? "asc" : "desc"};
            sortAttributes = new String[]{sortField};
        }

        QueryResult<ENTITY> data = loadData(pageSize, first, sortAttributes, sortOrders);

        this.setRowCount(data.getResultCount().intValue());
        return data.getResult();
    }
}
