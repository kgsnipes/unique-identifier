package org.uid.impl;

import org.uid.ResettableGenerator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.util.GeneratorUtil;

public class SteppedGenerator implements ResettableGenerator<String,String> {

    private ResettableGenerator<Long,Long> stepGenerator=null;
    private ResettableGenerator<Long,Long> generator=null;

    private static final Character DEFAULT_STEP_SEPARATOR='-';

    private Character stepSeparator;

    public SteppedGenerator(Integer stepIncrements) {
        init(0l,0l,1,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Integer stepIncrements,Character stepSeparator) {
        init(0l,0l,stepIncrements,stepSeparator);
    }

    public SteppedGenerator(Long step,Integer stepIncrements) {
        init(step,0l,stepIncrements,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Long step,Integer stepIncrements,Character stepSeparator) {
        init(step,0l,stepIncrements,stepSeparator);
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements) {
        init(step,startValue,stepIncrements,DEFAULT_STEP_SEPARATOR);
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements,Character stepSeparator) {
        init(step,startValue,stepIncrements,stepSeparator);
    }

    private void init(Long step,Long startValue,Integer stepIncrements,Character stepSeparator)
    {
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
    }

    public ResettableGenerator getStepGenerator() {
        return stepGenerator;
    }

    public void setStepGenerator(ResettableGenerator stepGenerator) {
        this.stepGenerator = stepGenerator;
    }

    public ResettableGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(ResettableGenerator generator) {
        this.generator = generator;
    }

    public Character getStepSeparator() {
        return stepSeparator;
    }

    public void setStepSeparator(Character stepSeparator) {
        this.stepSeparator = stepSeparator;
    }
}
