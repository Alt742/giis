package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class intBrands {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("update_time")
    public Long updateTime;
}
