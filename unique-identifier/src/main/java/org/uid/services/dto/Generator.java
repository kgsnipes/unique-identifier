package org.uid.services.dto;

import java.util.Date;

public class Generator {

    private Long id;
    private String uniqueIdentifier;
    private String generatorName;
    private String principalCreated;
    private Long startValue;
    private Date createdDate;
    private Date updatedDate;

    public Generator(String uniqueIdentifier, String generatorName, String principalCreated, Long startValue, Date createdDate, Date updatedDate) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.generatorName = generatorName;
        this.principalCreated = principalCreated;
        this.startValue = startValue;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getPrincipalCreated() {
        return principalCreated;
    }

    public void setPrincipalCreated(String principalCreated) {
        this.principalCreated = principalCreated;
    }

    public Long getStartValue() {
        return startValue;
    }

    public void setStartValue(Long startValue) {
        this.startValue = startValue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
