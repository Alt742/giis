package org.webvane.giis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class intProduct {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("material")
    public String material;

    @JsonProperty("description")
    public String description;

    @JsonProperty("hallmark")
    public Long hallmark;

    @JsonProperty("weight")
    public Float weight;

    @JsonProperty("weight_Inserts")
    public Float weightInserts;

    @JsonProperty("price")
    public Float price;

    @JsonProperty("size")
    public Float size;

    @JsonProperty("brand_id")
    public Long brandId;

    @JsonProperty("article")
    public String article;

    @JsonProperty("additionally")
    public String additionally;

    @JsonProperty("producer")
    public String producer;

    @JsonProperty("uin_inp")
    public String uin_inp;

    @JsonProperty("okpd2")
    public String okpd2;

    @JsonProperty("tnved")
    public String tnved;

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("update_time")
    public Long updateTime;
}
