package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.Generator;


import java.util.concurrent.atomic.AtomicLong;

public class LongGenerator implements Generator<Long> {

   private static final Logger log= LoggerFactory.getLogger(LongGenerator.class);

   protected AtomicLong value;
   protected Integer stepValue;

    public LongGenerator() {
        this.value=new AtomicLong(1L);
        this.stepValue=1;
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with start value of "+ getValue().get() +" and step value of "+getStepValue());
        }
    }

    public LongGenerator(Long startValue) {
        this.value=new AtomicLong(startValue);
        this.stepValue=1;
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with start value of "+ getValue().get() +" and step value of "+getStepValue());
        }
    }

    public LongGenerator(Long startValue,Integer stepValue) {
        this.value=new AtomicLong(startValue);
        this.stepValue=stepValue;
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with start value of "+ getValue().get() +" and step value of "+getStepValue());
        }
    }

    @Override
    public Long getNext() {
        return getNextValue();
    }

    private Long getNextValue()
    {
        if((getValue().get()+getStepValue())<Long.MAX_VALUE)
        {
            return getValue().getAndAdd(getStepValue());
        }
        else {
            if(log.isDebugEnabled())
            {
                log.debug("Already reached the upper limit of Long");
            }
            return getValue().get();
        }
    }

    protected AtomicLong getValue() {
        return value;
    }

    protected void setValue(AtomicLong value) {
        this.value = value;
    }

    protected Integer getStepValue() {
        return stepValue;
    }

    protected void setStepValue(Integer stepValue) {
        this.stepValue = stepValue;
    }
}
