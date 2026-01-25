package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 Действие по интеграции - отпарвка, получение ответа от внешней систем
 */
public class IntAction {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("system_id")
    public String systemId; // GIIS

    @JsonProperty("process_id")
    public Long processId;  //

    @JsonProperty("message_id")
    public String sysMessageId; // внутренний айди системы

    @JsonProperty("user_id")
    public Long userId;

    @JsonProperty("create_time")
    public Long createTime;

    @JsonProperty("update_time")
    public Long updateTime;

    @JsonProperty("type")
    public String type;

    @JsonProperty("status")
    public String status;   // NEW, SENT, SENT_RECEIVED, SEND_ERROR, RECEIVE_RECEIVED

    @JsonProperty("response")
    public String response;

    @JsonProperty("error")
    public String error;
}
