package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntProductProces {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("action_id")
    public Long action_id;

    @JsonProperty("status")
    public String status;

    @JsonProperty("response")
    public String response;

    @JsonProperty("update_time")
    public Long updateTime;
}
