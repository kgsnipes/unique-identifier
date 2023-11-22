package org.uid.impl;

import org.uid.ResettableGenerator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

public class SteppedGenerator implements ResettableGenerator<String,String> {

    private ResettableGenerator stepGenerator=null;
    private ResettableGenerator generator=null;

    private static final Character DEFAULT_STEP_SEPARATOR='-';

    private Character stepSeparator;

    public SteppedGenerator(Integer stepIncrements) {
        setStepSeparator(DEFAULT_STEP_SEPARATOR);
        setStepGenerator(new ResettableLongGenerator(0l));
        setGenerator(new ResettableLongGenerator(0L,stepIncrements));
    }

    public SteppedGenerator(Integer stepIncrements,Character stepSeparator) {
        setStepGenerator(new ResettableLongGenerator(0l));
        setStepSeparator(stepSeparator!=null?stepSeparator:DEFAULT_STEP_SEPARATOR);
        setGenerator(new ResettableLongGenerator(0L,stepIncrements));
    }

    public SteppedGenerator(Long step,Integer stepIncrements) {
        setStepGenerator(new ResettableLongGenerator(step));
        setGenerator(new ResettableLongGenerator(0L,stepIncrements));
    }

    public SteppedGenerator(Long step,Integer stepIncrements,Character stepSeparator) {
        setStepGenerator(new ResettableLongGenerator(step));
        setStepSeparator(stepSeparator!=null?stepSeparator:DEFAULT_STEP_SEPARATOR);
        setGenerator(new ResettableLongGenerator(0L,stepIncrements));
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements) {
        setStepGenerator(new ResettableLongGenerator(step));
        setGenerator(new ResettableLongGenerator(startValue,stepIncrements));
    }

    public SteppedGenerator(Long step,Long startValue,Integer stepIncrements,Character stepSeparator) {
        setStepGenerator(new ResettableLongGenerator(step));
        setStepSeparator(stepSeparator!=null?stepSeparator:DEFAULT_STEP_SEPARATOR);
        setGenerator(new ResettableLongGenerator(startValue,stepIncrements));
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
        }
        if(incrementStep)
        {
            getStepGenerator().getNext();
        }
        return String.format("%d%c%d",getStepGenerator().getCurrentValue(),getStepSeparator(),getGenerator().getCurrentValue());
    }

    @Override
    public boolean hasReachedLimit() {
        return getStepGenerator().hasReachedLimit();
    }

    @Override
    public String getCurrentValue() {
        return String.format("%d%c%d",getStepGenerator().getCurrentValue(),getStepSeparator(),getGenerator().getCurrentValue());
    }

    @Override
    public void reset() {
        getStepGenerator().reset();
        getGenerator().reset();
    }

    @Override
    public void reset(String value) {
        if(value!=null && value.indexOf(getStepSeparator())!=-1 && Long.valueOf())
        {

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
