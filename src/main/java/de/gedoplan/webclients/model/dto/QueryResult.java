
package de.gedoplan.webclients.model.dto;

import java.util.List;

public class QueryResult<ENTITY> {

    private List<ENTITY> result;
    
    private Long resultCount;

    public QueryResult(List<ENTITY> result, Long resultCount) {
        this.result = result;
        this.resultCount = resultCount;
    }
    
    public List<ENTITY> getResult() {
        return result;
    }

    public void setResult(List<ENTITY> result) {
        this.result = result;
    }

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }
    
    
}
