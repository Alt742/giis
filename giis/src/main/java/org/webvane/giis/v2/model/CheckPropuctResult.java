package org.webvane.giis.v2.model;

import org.webvane.common.Pair;
import org.webvane.giis.dmdk.request.dmdk.elements.response.batch.ResultBatch;

import java.util.List;
import java.util.Map;

public class CheckPropuctResult {
    public String status;
    public List<ShortResultBatch> uins;
    public ResultBatch data;

    public List<Pair<List<ShortResultBatch>, Map<String, String>>> variants;
}
