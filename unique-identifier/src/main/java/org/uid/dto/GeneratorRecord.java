package org.uid.dto;

import java.util.Date;

public class GeneratorRecord {

    private String id;
    private String generatorId;
    private Long lastGeneratedValue;
    private Date createdOn;
    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLastGeneratedValue() {
        return lastGeneratedValue;
    }

    public void setLastGeneratedValue(Long lastGeneratedValue) {
        this.lastGeneratedValue = lastGeneratedValue;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(String generatorId) {
        this.generatorId = generatorId;
    }
}
