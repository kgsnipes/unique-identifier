package org.uid.dto;

import java.util.Date;
import java.util.List;

public class CascadedGeneratorData {

    private Long id;
    private String name;
    private List<LongGeneratorData> generatorList;
    private String format;
    private Date createdOn;
    private Date updatedOn;
    private Boolean disabled;
    private Boolean deleted;
    private Boolean isComplete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LongGeneratorData> getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(List<LongGeneratorData> generatorList) {
        this.generatorList = generatorList;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
