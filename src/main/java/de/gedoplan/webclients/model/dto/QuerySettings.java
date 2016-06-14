package de.gedoplan.webclients.model.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuerySettings {

    private Integer start;

    private Integer max;

    private Map<String, String> sorting = new HashMap<>();

    private Map<String, Object> filter = new HashMap<>();

    public QuerySettings(Integer start, Integer max) {
        this.start = start;
        this.max = max;
    }

    public QuerySettings(Integer start, Integer max, Map<String, String> sorting) {
        this.start = start;
        this.max = max;
        this.sorting = sorting;
    }

    public QuerySettings(Integer start, Integer max, String[] sortAttributes, String[] sortDirections) {
        this.start = start;
        this.max = max;
        int sortAttributesCount = sortAttributes==null?0:sortAttributes.length;
        

        sorting = IntStream.range(0, sortAttributesCount).boxed().collect(Collectors.toMap(i -> sortAttributes[i], i -> sortDirections[i]));
    }

    public QuerySettings() {
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Map<String, String> getSorting() {
        return sorting;
    }

    public void setSorting(Map<String, String> sorting) {
        this.sorting = sorting;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

}
