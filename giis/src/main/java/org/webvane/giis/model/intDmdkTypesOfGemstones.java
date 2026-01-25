package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class intDmdkTypesOfGemstones {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("dmdk_type")
    public String dmdkType;

    @JsonProperty("ump_type")
    public String umpType;
}
