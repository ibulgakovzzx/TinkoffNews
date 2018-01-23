package com.ibulgakov.tinkoffinfoviewer.models.eniity;

import io.realm.RealmObject;


public class BaseDate extends RealmObject {

    private Long milliseconds;

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }
}
