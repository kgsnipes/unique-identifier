package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.ResettableGenerator;
import org.uid.exception.GeneratorException;

public class ResettableLongGenerator extends LongGenerator implements ResettableGenerator<Long,Long> {

    private static final Logger log= LoggerFactory.getLogger(ResettableLongGenerator.class);
    protected Long startValue;

    public ResettableLongGenerator() throws GeneratorException {
        super();
        setStartValue(getValue().longValue());
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with default start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue) throws GeneratorException {
        super(startValue);
        setStartValue(startValue);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with a defined start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue,Integer stepValue) throws GeneratorException {
        super(startValue,stepValue);
        setStartValue(startValue);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with start value of {} and defined step value of {}",getValue().get(),getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue,Long upperLimit, Integer stepValue) throws GeneratorException {
        super(startValue,upperLimit,stepValue);
        setStartValue(startValue);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with start value of {} and defined step value of {}",getValue().get(),getStepValue());
        }
    }


    @Override
    public void reset() {

        super.getValue().set(getStartValue());
        limitReached=false;
        if(log.isDebugEnabled())
        {
            log.debug("Resetting to default start value");
        }
    }

    @Override
    public void reset(Long value)throws GeneratorException {
        if(value !=null)
        {
            setStartValue(value);
            super.getValue().set(getStartValue());
            limitReached=false;
        }
        else {
            throw new GeneratorException("Cannot reset with invalid value");
        }
        if(log.isDebugEnabled())
        {
            log.debug("Resetting to {}",getStartValue());
        }
    }

    protected Long getStartValue() {
        return startValue;
    }

    protected void setStartValue(Long startValue) {
        this.startValue = startValue;
    }
}
