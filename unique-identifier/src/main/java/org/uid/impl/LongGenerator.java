package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.Generator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

import java.util.concurrent.atomic.AtomicLong;

public class LongGenerator implements Generator<Long> {

   private static final Logger log= LoggerFactory.getLogger(LongGenerator.class);
   protected AtomicLong value;
   protected Integer stepValue;
   protected Long upperLimitValue;

   private static final String REACHED_UPPER_LIMIT_MESSAGE="Already reached the upper limit of Long";

   protected Boolean limitReached=false;
    public LongGenerator() throws GeneratorException {
        init(0L,null,1);
    }

    public LongGenerator(Long startValue) throws GeneratorException {
        init(startValue,Long.MAX_VALUE,1);
    }

    public LongGenerator(Long startValue,Integer stepValue) throws GeneratorException {
        init(startValue,null,stepValue);
    }

    public LongGenerator(Long startValue,Long upperLimit, Integer stepValue) throws GeneratorException {
        init(startValue,upperLimit,stepValue);
    }

    private void init(Long startValue,Long upperLimit,Integer stepValue) throws GeneratorException {
        if(stepValue==0)
        {
            throw new GeneratorException("Step value cannot be zero");
        }
        setValue(new AtomicLong(startValue));
        setStepValue(stepValue);

        if(upperLimit!=null)
        {
            setUpperLimitValue(upperLimit);
            if(isValueAlreadyReachedLimit())
            {
                limitReached=true;
            }
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

        if(getUpperLimitValue()!=null)
        {
            return incrementBasedOnLimits(getUpperLimitValue());
        }
        else
        {
            if(getStepValue()>0) {
                return incrementBasedOnLimits(Long.MAX_VALUE);
            }
            else {
                return incrementBasedOnLimits(Long.MIN_VALUE);
            }

        }


    }

    private Long incrementBasedOnLimits(Long upperLimit) throws GeneratorLimitReachedException {
        if(upperLimit>0)
        {
            if((getValue().get()+getStepValue())<upperLimit)
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
            if((getValue().get()+getStepValue())>upperLimit)
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
