package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class intDmdkTypesOfSize {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("dmdk_type")
    public String dmdkType;

    @JsonProperty("processing_type")
    public String processingType;
}
