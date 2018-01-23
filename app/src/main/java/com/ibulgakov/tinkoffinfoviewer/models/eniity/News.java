package com.ibulgakov.tinkoffinfoviewer.models.eniity;


import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class News extends RealmObject {

    @PrimaryKey
    private String id;

    private String name;

    private String text;

    private BaseDate publicationDate;

    private Integer bankInfoTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BaseDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(BaseDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof News) || obj.getClass() != getClass())
            return false;
        News other = (News) obj;

        return Objects.equals(id, other.getId()) &&
               Objects.equals(name, other.getName()) &&
               Objects.equals(text, other.getText()) &&
               Objects.equals(publicationDate, other.getPublicationDate()) &&
               Objects.equals(bankInfoTypeId, other.getBankInfoTypeId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}