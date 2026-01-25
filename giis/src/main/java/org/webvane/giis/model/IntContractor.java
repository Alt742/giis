package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntContractor {
    @JsonProperty("id")
    public Long id;
    @JsonProperty("id_int_action")
    public Long idIntAction;
    @JsonProperty("data")
    public String data;
    @JsonProperty("inn")
    public String inn;
    @JsonProperty("update_time")
    public Long updateTime;
    @JsonProperty("user_id")
    public Long userId;
}