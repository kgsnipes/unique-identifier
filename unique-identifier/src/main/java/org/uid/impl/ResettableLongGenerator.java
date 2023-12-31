package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.ResettableGenerator;

public class ResettableLongGenerator extends LongGenerator implements ResettableGenerator<Long,Long> {

    private static final Logger log= LoggerFactory.getLogger(ResettableLongGenerator.class);
    protected Long startValue;

    public ResettableLongGenerator() {
        super();
        setStartValue(getValue().longValue());
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with default start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue) {
        super(startValue);
        setStartValue(startValue);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with a defined start value of {} and step value of {}",getValue().get(),getStepValue());
        }
    }

    public ResettableLongGenerator(Long startValue,Integer stepValue) {
        super(startValue,stepValue);
        setStartValue(startValue);
        if(log.isDebugEnabled())
        {
            log.debug("Creating a Resettable long Generator with start value of {} and defined step value of {}",getValue().get(),getStepValue());
        }
    }


    @Override
    public void reset() {

        super.getValue().set(getStartValue());
        if(log.isDebugEnabled())
        {
            log.debug("Resetting to default start value");
        }
    }

    @Override
    public void reset(Long value) {
        if(value !=null)
        {
            setStartValue(value);
            super.getValue().set(getStartValue());
        }
        else {
            super.getValue().set(getStartValue());
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
