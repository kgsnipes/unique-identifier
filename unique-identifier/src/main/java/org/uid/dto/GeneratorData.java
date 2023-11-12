package org.uid.dto;

import java.util.Date;
import java.util.List;

public class GeneratorData {
    private String code;
    private String name;
    private List<GeneratorData> generatorList;
    private Boolean isCascaded;
    private String format;
    private Date createdOn;
    private Date updatedOn;
    private Boolean disabled;
    private Boolean deleted;
    private Boolean isComplete;

    private Long upperLimit;
    private Long lowerLimit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Long upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    private Boolean isBatched;

    private Boolean isResettable;

    public List<GeneratorData> getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(List<GeneratorData> generatorList) {
        this.generatorList = generatorList;
    }

    public Boolean getBatched() {
        return isBatched;
    }

    public void setBatched(Boolean batched) {
        isBatched = batched;
    }

    public Boolean getResettable() {
        return isResettable;
    }

    public void setResettable(Boolean resettable) {
        isResettable = resettable;
    }

    public Boolean getCascaded() {
        return isCascaded;
    }

    public void setCascaded(Boolean cascaded) {
        isCascaded = cascaded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
