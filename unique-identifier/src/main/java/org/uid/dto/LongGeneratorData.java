package org.uid.dto;

import java.util.Date;

public class LongGeneratorData {

    private Long id;
    private String name;
    private String startValue;
    private String stepValue;
    private Boolean isResettable;
    private Boolean isBatched;
    private String upperLimit;

    private Date createdOn;
    private Date updatedOn;
    private Boolean disabled;
    private Boolean deleted;

    private Boolean isComplete;

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

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

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getStepValue() {
        return stepValue;
    }

    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    public Boolean getResettable() {
        return isResettable;
    }

    public void setResettable(Boolean resettable) {
        isResettable = resettable;
    }

    public Boolean getBatched() {
        return isBatched;
    }

    public void setBatched(Boolean batched) {
        isBatched = batched;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
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
}
