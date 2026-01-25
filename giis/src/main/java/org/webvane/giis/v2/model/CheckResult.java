package org.webvane.giis.v2.model;

import java.util.Map;

public class CheckResult {
    public String status;
    public Map<String, Object> data;

    public CheckResult( String status, Map<String, Object> data ) {
        this.status = status;
        this.data = data;
    }

    public CheckResult(String status) {}

    public CheckResult() {}
}
