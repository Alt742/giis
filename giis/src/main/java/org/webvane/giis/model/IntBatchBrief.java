package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntBatchBrief {
    @JsonProperty("id")
    public Long id;
    @JsonProperty("id_int_action")
    public Long idIntAction;
    @JsonProperty("data")
    public String data;
    @JsonProperty("uin")
    public String uin;
    @JsonProperty("update_time")
    public Long updateTime;
    @JsonProperty("user_id")
    public Long userId;
}