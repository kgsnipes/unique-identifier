package org.uid.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneratorData {
    private String code;
    private String name;
    private List<GeneratorData> generatorList=new ArrayList<>();
    private Boolean isCascaded=false;
    private String format;
    private Date createdOn;
    private Date updatedOn;
    private Boolean disabled=false;
    private Boolean deleted=false;
    private Boolean isComplete=false;
    private String currentHostId;
    private Long upperLimit;
    private Long lowerLimit;
    private Integer acceptableLoss;
    private Boolean isBatched=false;
    private Boolean isResettable=false;


    public Integer getAcceptableLoss() {
        return acceptableLoss;
    }

    public void setAcceptableLoss(Integer acceptableLoss) {
        this.acceptableLoss = acceptableLoss;
    }

    public String getCurrentHostId() {
        return currentHostId;
    }

    public void setCurrentHostId(String currentHostId) {
        this.currentHostId = currentHostId;
    }

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
