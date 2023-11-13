package org.uid.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "generator_record")
public class GeneratorRecordDAO {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField(canBeNull = false)
    private Long generatorId;
    @DatabaseField(canBeNull = false)
    private String hostId;
    @DatabaseField(canBeNull = false)
    private String generatorCode;
    @ForeignCollectionField(eager = true,columnName = "lastGeneratedValue",orderColumnName = "valueOrder",orderAscending = true)
    private List<Long> lastGeneratedValue=new ArrayList<>();
    @DatabaseField(canBeNull = false)
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
