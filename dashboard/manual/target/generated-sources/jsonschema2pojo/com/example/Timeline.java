
package com.example;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "_cacheHit"
})
public class Timeline {

    @JsonProperty("data")
    private List<Datum> data = new ArrayList<Datum>();
    @JsonProperty("_cacheHit")
    private Boolean cacheHit;

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    @JsonProperty("_cacheHit")
    public Boolean getCacheHit() {
        return cacheHit;
    }

    @JsonProperty("_cacheHit")
    public void setCacheHit(Boolean cacheHit) {
        this.cacheHit = cacheHit;
    }

}
