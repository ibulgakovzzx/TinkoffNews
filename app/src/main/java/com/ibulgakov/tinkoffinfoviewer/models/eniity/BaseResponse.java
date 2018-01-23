package com.ibulgakov.tinkoffinfoviewer.models.eniity;

/**
 * Created by ibulgakov on 23.01.18.
 */

public class BaseResponse<T> {
    private String resultCode;

    private T payload;

    private String trackingId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
}
