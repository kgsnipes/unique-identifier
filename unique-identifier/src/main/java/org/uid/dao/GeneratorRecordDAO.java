package org.uid.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneratorRecordDAO {

    private Long id;
    private Long generatorId;
    private String hostId;
    private String generatorCode;
    private List<Long> lastGeneratedValue=new ArrayList<>();
    private Date createdOn;

    public Long getGeneratorId() {
        return generatorId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
