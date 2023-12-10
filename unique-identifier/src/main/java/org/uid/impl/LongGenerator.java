package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.Generator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.exception.GeneratorLockException;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LongGenerator implements Generator<Long> {

   private static final Logger log= LoggerFactory.getLogger(LongGenerator.class);
   protected AtomicLong value;
   protected Integer stepValue;
   protected Long upperLimitValue;

   private Lock lock=new ReentrantLock(true);

   private static final String REACHED_UPPER_LIMIT_MESSAGE="Already reached the upper limit of Long";

   private static final String START_VALUE_ERROR_MESSAGE="Start value cannot be null or less than zero";

   private static final String STEP_VALUE_ERROR_MESSAGE="step value cannot be negative";

   protected Boolean limitReached=false;
    public LongGenerator() throws GeneratorException {
        init(0L,Long.MAX_VALUE,1);
    }

    public LongGenerator(Long startValue) throws GeneratorException {
        if(Objects.isNull(startValue) ||startValue<0)
        {
            throw new GeneratorException(START_VALUE_ERROR_MESSAGE);
        }
        init(startValue,Long.MAX_VALUE,1);
    }

    public LongGenerator(Long startValue,Integer stepValue) throws GeneratorException {
        if(Objects.isNull(startValue) || startValue<0)
        {
            throw new GeneratorException(START_VALUE_ERROR_MESSAGE);
        }
        if(Objects.isNull(stepValue)|| stepValue<0)
        {
            throw new GeneratorException(STEP_VALUE_ERROR_MESSAGE);
        }
        init(startValue,Long.MAX_VALUE,stepValue);
    }

    public LongGenerator(Long startValue,Long upperLimit, Integer stepValue) throws GeneratorException {

        if(Objects.isNull(startValue) || startValue<0)
        {
            throw new GeneratorException(START_VALUE_ERROR_MESSAGE);
        }
        if(Objects.isNull(stepValue)|| stepValue<0)
        {
            throw new GeneratorException(STEP_VALUE_ERROR_MESSAGE);
        }

        if(Objects.isNull(upperLimit)|| upperLimit<0)
        {
            throw new GeneratorException("upper limit value cannot be negative");
        }

        init(startValue,upperLimit,stepValue);
    }

    private void init(Long startValue,Long upperLimit,Integer stepValue) {
        setValue(new AtomicLong(startValue));
        setStepValue(stepValue);
        setUpperLimitValue(upperLimit);
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
        try {
            if(lock.tryLock()) {
                if(hasReachedLimit())
                {
                    throw new GeneratorLimitReachedException();
                }
                if((getValue().get()+getStepValue())<=getUpperLimitValue() && (getValue().get()+getStepValue())>=0)
                {
                    return getValue().getAndAdd(getStepValue());
                }
                else {
                    setLimitReachedAndLogIt();
                }
            }
            else {
                throw new GeneratorLockException("Could not get a lock on the generator");
            }
        }
        finally
        {
            lock.unlock();
        }
        return getCurrentValue();
    }

    private void setLimitReachedAndLogIt() throws GeneratorLimitReachedException {
        if(log.isDebugEnabled())
        {
            log.debug(REACHED_UPPER_LIMIT_MESSAGE);
        }
        limitReached=true;
        throw new GeneratorLimitReachedException();
    }

    private boolean isValueAlreadyReachedLimit()
    {
        return getUpperLimitValue().longValue()==getValue().get() || getValue().get()==Long.MAX_VALUE;
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
