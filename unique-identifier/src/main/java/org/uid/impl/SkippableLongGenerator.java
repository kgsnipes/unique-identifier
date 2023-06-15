package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.SkippableGenerator;

public class SkippableLongGenerator extends ResettableLongGenerator implements SkippableGenerator<Long,Long> {

    private static final Logger log= LoggerFactory.getLogger(SkippableLongGenerator.class);

    protected Long skipValue;

    public SkippableLongGenerator(Long skipValue) {
        super();
        setSkipValue(skipValue);
    }

    public SkippableLongGenerator(Long startValue,Long skipValue) {
        super(startValue);
        setSkipValue(skipValue);
    }

    public SkippableLongGenerator(Long startValue,Integer stepValue,Long skipValue) {
        super(startValue,stepValue);
        setSkipValue(skipValue);
    }


    @Override
    public void skip() {
        super.reset(getSkipValue());
    }

    protected Long getSkipValue() {
        return skipValue;
    }

    protected void setSkipValue(Long skipValue) {
        this.skipValue = skipValue;
    }
}
