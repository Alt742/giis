package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntUinStatus {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("action_id")
    public Long action_id;

    @JsonProperty("valid")
    public String valid;

    @JsonProperty("name")
    public String name;

    @JsonProperty("uin")
    public String uin;

    @JsonProperty("message_id")
    public String messageId;

    @JsonProperty("variant")
    public Long variant;

    @JsonProperty("difference")
    public String difference;

    public IntUinStatus(Long action_id, String uin, String name, String valid, String messageId ){
        this.action_id = action_id;
        this.uin = uin;
        this.name = name;
        this.valid = valid;
        this.messageId = messageId;
    }
    public IntUinStatus(){}
}
