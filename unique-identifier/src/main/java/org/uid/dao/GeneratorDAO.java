package org.uid.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@DatabaseTable(tableName = "generator")
public class GeneratorDAO {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField(canBeNull = false,unique = true)
    private String code;
    @DatabaseField(canBeNull = false)
    private String name;
    @ForeignCollectionField(eager = true,columnName = "generatorList",orderColumnName = "generatorOrder",orderAscending = true)
    private List<GeneratorDAO> generatorList=new ArrayList<>();
    @DatabaseField(canBeNull = false)
    private Boolean isCascaded;
    @DatabaseField(canBeNull = false)
    private String format;
    @DatabaseField(canBeNull = false)
    private Date createdOn;
    @DatabaseField(canBeNull = false)
    private Date updatedOn;
    private Boolean disabled;
    private Boolean deleted;
    private Boolean isComplete;
    private String currentHostId;
    private Long upperLimit;
    private Long lowerLimit;

    private Boolean isBatched;

    private Boolean isResettable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Integer acceptableLoss;

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



    public List<GeneratorDAO> getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(List<GeneratorDAO> generatorList) {
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
