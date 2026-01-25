package org.webvane.giis.dmdk.request.dmdk;

import org.webvane.giis.dmdk.request.dmdk.elements.*;

public class EnvelopeFactory {
    private static EnvelopeFactory instance;
    private EnvelopeFactory(){}
    public static EnvelopeFactory getInstance(){
        if(instance == null){
            instance = new EnvelopeFactory();
        }
        return instance;
    }

    public Envelope createRequest(String requestType){
        switch (requestType) {
            case "SendGetContractorRequest":
                return new Envelope(new SendGetContractorRequest());
            case "SendGetBatchBriefRequest":
                return new Envelope(new SendGetBatchBriefRequest());
            case "SendGetBatchRequest":
                return new Envelope(new SendGetBatchRequest());
            case "CheckGetBatchBriefRequest":
                return new Envelope(new CheckGetBatchBrief());
            case "CheckGetContractorRequest":
                return new Envelope(new CheckGetContractorRequest());
            case "CheckGetBatchRequest":
                return new Envelope(new CheckGetBatchRequest());
            default:
                break;
        }
        return null;
    }
}
