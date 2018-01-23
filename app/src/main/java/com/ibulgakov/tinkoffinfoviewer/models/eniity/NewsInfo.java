package com.ibulgakov.tinkoffinfoviewer.models.eniity;

import io.realm.RealmObject;


public class NewsInfo extends RealmObject {

    private News title;

    private BaseDate creationDate;

    private BaseDate lastModificationDate;

    private String content;

    private Integer bankInfoTypeId;

    private String typeId;

    public News getTitle() {
        return title;
    }

    public void setTitle(News title) {
        this.title = title;
    }

    public BaseDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(BaseDate creationDate) {
        this.creationDate = creationDate;
    }

    public BaseDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(BaseDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
