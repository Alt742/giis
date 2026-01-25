package org.webvane.giis.v2.model;

public class ShortResultBatch {
    public String valid;
    public String name;
    public String uin;

    public ShortResultBatch( String name, String uin, String valid){
        this.valid = valid;
        this.name = name;
        this.uin = uin;
    }

    public ShortResultBatch(){
    }
}
