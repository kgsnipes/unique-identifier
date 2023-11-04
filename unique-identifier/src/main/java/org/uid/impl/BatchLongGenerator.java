package org.uid.impl;

public class BatchLongGenerator extends ResettableLongGenerator{

    protected Long batchSize;

    public BatchLongGenerator(Long batchSize) {
        super(0L);
        setBatchSize(batchSize);
        setUpperLimitValue(getStartValue()+getBatchSize());
    }

    public BatchLongGenerator(Long startValue,Long batchSize) {
        super(startValue);
        setBatchSize(batchSize);
        setUpperLimitValue(getStartValue()+getBatchSize());
    }

    public BatchLongGenerator(Long startValue,Integer stepValue,Long batchSize) {
        super(startValue,stepValue);
        setBatchSize(batchSize);
        if(getStepValue()>0)
            setUpperLimitValue(getStartValue()+getBatchSize());
        else
            setUpperLimitValue(getStartValue()-getBatchSize());
    }

    protected Long getBatchSize() {
        return batchSize;
    }

    protected void setBatchSize(Long batchSize) {
        this.batchSize = batchSize;
    }

}
