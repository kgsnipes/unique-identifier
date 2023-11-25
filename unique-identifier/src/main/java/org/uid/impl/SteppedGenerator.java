package org.uid.impl;

import org.uid.ResettableGenerator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.util.GeneratorUtil;

import java.util.Objects;

public class SteppedGenerator implements ResettableGenerator<String,String> {

    private ResettableGenerator<Long,Long> stepGenerator=null;
    private ResettableGenerator<Long,Long> generator=null;

    private static final Character DEFAULT_STEP_SEPARATOR='-';

    private static final String STEP_INCR_ERROR_MESSAGE="Step Increments cannot be null or zero or negative value";
    private static final String STEP_ERROR_MESSAGE="Step cannot be null or zero or negative value";
    private static final String STEP_SEPARATOR_ERROR_MESSAGE="Step separator cannot be null";

    private Character stepSeparator;

    public SteppedGenerator(Integer stepIncrements) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        init(0l,0l,stepIncrements,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Integer stepIncrements,Character stepSeparator) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        if(Objects.isNull(stepSeparator))
        {
            throw new GeneratorException(STEP_SEPARATOR_ERROR_MESSAGE);
        }
        init(0l,0l,stepIncrements,stepSeparator);
    }

    public SteppedGenerator(Long step,Integer stepIncrements) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        if(Objects.isNull(step) || step<0)
        {
            throw new GeneratorException(STEP_ERROR_MESSAGE);
        }
        init(step,0l,stepIncrements,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Long step,Integer stepIncrements,Character stepSeparator) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        if(Objects.isNull(step) || step<0)
        {
            throw new GeneratorException(STEP_ERROR_MESSAGE);
        }
        if(Objects.isNull(stepSeparator))
        {
            throw new GeneratorException(STEP_SEPARATOR_ERROR_MESSAGE);
        }
        init(step,0l,stepIncrements,stepSeparator);
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        if(Objects.isNull(step) || step<0)
        {
            throw new GeneratorException(STEP_ERROR_MESSAGE);
        }
        if(Objects.isNull(startValue) || startValue<=0)
        {
            throw new GeneratorException("Start value cannot be null or negative value");
        }
        init(step,startValue,stepIncrements,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements,Character stepSeparator) throws GeneratorException {
        if(Objects.isNull(stepIncrements) || stepIncrements<=0)
        {
            throw new GeneratorException(STEP_INCR_ERROR_MESSAGE);
        }
        if(Objects.isNull(step) || step<0)
        {
            throw new GeneratorException(STEP_ERROR_MESSAGE);
        }
        if(Objects.isNull(stepSeparator))
        {
            throw new GeneratorException(STEP_SEPARATOR_ERROR_MESSAGE);
        }
        if(Objects.isNull(startValue) || startValue<=0)
        {
            throw new GeneratorException("Start value cannot be null or negative value");
        }
        init(step,startValue,stepIncrements,stepSeparator);
    }

    private void init(Long step,Long startValue,Integer stepIncrements,Character stepSeparator) throws GeneratorException {
        setStepGenerator(new ResettableLongGenerator(step!=null?step:0l));
        setStepSeparator(stepSeparator!=null?stepSeparator:DEFAULT_STEP_SEPARATOR);
        setGenerator(new ResettableLongGenerator(startValue!=null?startValue:0L,stepIncrements!=null?stepIncrements:1));
    }

    @Override
    public String getNext() throws GeneratorLimitReachedException, GeneratorException {

        boolean incrementStep=false;
        try{
            getGenerator().getNext();
        }
        catch (GeneratorLimitReachedException ex)
        {
            incrementStep=true;
            getGenerator().reset(0L);
        }
        if(incrementStep)
        {
            getStepGenerator().getNext();
        }
        return getCurrentValue();
    }

    @Override
    public boolean hasReachedLimit() {
        return getStepGenerator().hasReachedLimit();
    }

    @Override
    public String getCurrentValue() {
        return String.format("%s%c%s",GeneratorUtil.paddingValuesWithZeros(getStepGenerator().getCurrentValue().toString(),LONG_LENGTH),getStepSeparator(), GeneratorUtil.paddingValuesWithZeros(getGenerator().getCurrentValue().toString(),LONG_LENGTH));
    }

    @Override
    public void reset() {
        getStepGenerator().reset();
        getGenerator().reset();
    }

    @Override
    public void reset(String value) throws GeneratorException {
        if(value!=null && value.indexOf(getStepSeparator())!=-1)
        {
            String[] parts=value.split(Character.toString(getStepSeparator()));
            if(parts.length==2)
            {
                try
                {
                    getStepGenerator().reset(Long.parseLong(parts[0]));
                    getGenerator().reset(Long.parseLong(parts[1]));
                }
                catch (Exception ex)
                {
                    throw new GeneratorException("Cannot reset with invalid value");
                }
            }
            else {
                throw new GeneratorException("Invalid value to reset "+value);
            }

        }
        else {
            throw new GeneratorException("Invalid value for reset.");
        }
    }

    public ResettableGenerator<Long, Long> getStepGenerator() {
        return stepGenerator;
    }

    public void setStepGenerator(ResettableGenerator<Long, Long> stepGenerator) {
        this.stepGenerator = stepGenerator;
    }

    public ResettableGenerator<Long, Long> getGenerator() {
        return generator;
    }

    public void setGenerator(ResettableGenerator<Long, Long> generator) {
        this.generator = generator;
    }

    public Character getStepSeparator() {
        return stepSeparator;
    }

    public void setStepSeparator(Character stepSeparator) {
        this.stepSeparator = stepSeparator;
    }
}
