package org.uid.dto;

import java.util.Date;
import java.util.List;

public class GeneratorRecordData {

    private String id;
    private Long generatorId;
    private String generatorCode;
    private List<Long> lastGeneratedValue;
    private Date createdOn;

    public void setGeneratorId(Long generatorId) {
        this.generatorId = generatorId;
    }

    public String getGeneratorCode() {
        return generatorCode;
    }

    public void setGeneratorCode(String generatorCode) {
        this.generatorCode = generatorCode;
    }

    public List<Long> getLastGeneratedValue() {
        return lastGeneratedValue;
    }

    public void setLastGeneratedValue(List<Long> lastGeneratedValue) {
        this.lastGeneratedValue = lastGeneratedValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
