package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.ResettableGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class ResettableLongGenerator extends LongGenerator implements ResettableGenerator<Long,Long> {

    private static final Logger log= LoggerFactory.getLogger(ResettableLongGenerator.class);

    protected Long startValue;


    public ResettableLongGenerator() {
        super();
        setStartValue(getValue().longValue());
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with start value of "+ getValue().get() +" and step value of "+getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue) {
        super(startValue);
        setStartValue(startValue);
    }

    public ResettableLongGenerator(Long startValue,Integer stepValue) {
        super(startValue,stepValue);
        setStartValue(startValue);
    }

    @Override
    public Long getNext() {
        return super.getNext();
    }

    @Override
    public void reset() {
        super.setValue(new AtomicLong(getStartValue()));
    }

    @Override
    public void reset(Long value) {
        if(value !=null && value<=Long.MAX_VALUE)
        {
            setStartValue(value);
            super.setValue(new AtomicLong(getStartValue()));
        }
        else {
            super.setValue(new AtomicLong(getStartValue()));
        }
    }

    protected Long getStartValue() {
        return startValue;
    }

    protected void setStartValue(Long startValue) {
        this.startValue = startValue;
    }
}
