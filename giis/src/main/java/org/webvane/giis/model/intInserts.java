package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class intInserts {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("id_goods")
    public Long goodsId;

    @JsonProperty("inserts_type")
    public String insertsType;

    @JsonProperty("update_time")
    public Long updateTime;
}
