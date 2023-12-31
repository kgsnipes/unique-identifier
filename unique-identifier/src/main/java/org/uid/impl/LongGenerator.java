package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.Generator;
import org.uid.exception.GeneratorLimitReachedException;

import java.util.concurrent.atomic.AtomicLong;

public class LongGenerator implements Generator<Long> {

   private static final Logger log= LoggerFactory.getLogger(LongGenerator.class);
   protected AtomicLong value;
   protected Integer stepValue;
   protected Long upperLimitValue;

   private static final String REACHED_UPPER_LIMIT_MESSAGE="Already reached the upper limit of Long";

   protected Boolean limitReached=false;
    public LongGenerator() {
        setValue(new AtomicLong(0L));
        setStepValue(1);
        setUpperLimitValue(Long.MAX_VALUE);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with default start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public LongGenerator(Long startValue) {
        setValue(new AtomicLong(startValue));
        setStepValue(1);
        if(getStepValue()>0)
            setUpperLimitValue(Long.MAX_VALUE);

        if(isValueAlreadyReachedLimit())
        {
            limitReached=true;
        }

        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with defined start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public LongGenerator(Long startValue,Integer stepValue) {
        setValue(new AtomicLong(startValue));
        setStepValue(stepValue);
        if(getStepValue()>0)
            setUpperLimitValue(Long.MAX_VALUE);
        else
            setUpperLimitValue(Long.MIN_VALUE);

        if(isValueAlreadyReachedLimit())
        {
            limitReached=true;
        }

        if(log.isDebugEnabled())
        {
            log.debug("Creating a Long Generator with start value of {} and defined step value of {}",getValue().get(),getStepValue());
        }
    }

    @Override
    public Long getNext()throws GeneratorLimitReachedException {
        return getNextValue();
    }

    @Override
    public boolean hasReachedLimit() {
        return limitReached;
    }

    @Override
    public Long getCurrentValue() {
        return getValue().get();
    }

    private Long getNextValue()throws GeneratorLimitReachedException
    {
        if(hasReachedLimit())
        {
            throw new GeneratorLimitReachedException();
        }

        if(getUpperLimitValue()>0)
        {
            if((getValue().get()+getStepValue())<getUpperLimitValue())
            {
                return getValue().getAndAdd(getStepValue());
            }
            else {
                if(log.isDebugEnabled())
                {
                    log.debug(REACHED_UPPER_LIMIT_MESSAGE);
                }
                limitReached=true;
                throw new GeneratorLimitReachedException();
            }
        }
        else {
            if((getValue().get()+getStepValue())>getUpperLimitValue())
            {
                return getValue().getAndAdd(getStepValue());
            }
            else {
                if(log.isDebugEnabled())
                {
                    log.debug(REACHED_UPPER_LIMIT_MESSAGE);
                }
                limitReached=true;
                throw new GeneratorLimitReachedException();
            }
        }

    }

    private boolean isValueAlreadyReachedLimit()
    {
        return getUpperLimitValue()==getValue().get();
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

    protected Long getUpperLimitValue() {
        return upperLimitValue;
    }

    protected void setUpperLimitValue(Long upperLimitValue) {
        this.upperLimitValue = upperLimitValue;
    }

    @Override
    public String toString() {
        return this.getCurrentValue().toString();
    }
}
