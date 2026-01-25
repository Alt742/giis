package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntRequestStatus {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("request_type")
    public String requestType;

    @JsonProperty("args")
    public String args;

    @JsonProperty("status")
    public String status;

    @JsonProperty("message_id")
    public String messageId;

    @JsonProperty("action_id")
    public Long action_id;

    @JsonProperty("response")
    public String response;

    @JsonProperty("last_dmdk_check")
    public Long lastDmdkCheck;

    @JsonProperty("update_time")
    public Long updateTime;
}
