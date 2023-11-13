package org.uid.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.impl.ResettableLongGenerator;

public class ResettableLongGeneratorTest {

    @BeforeAll
    public static void beforeAll()
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");
    }

    @Test
    public void testResettingValue() throws GeneratorLimitReachedException {
        ResettableLongGenerator generator=new ResettableLongGenerator();
        generator.getNext();
        generator.reset();
        Assertions.assertEquals(0L,generator.getCurrentValue());
    }

    @Test
    public void testResettingValueWithStartValue() throws GeneratorLimitReachedException {
        ResettableLongGenerator generator=new ResettableLongGenerator(10L);
        generator.getNext();
        generator.reset();
        Assertions.assertEquals(10L,generator.getCurrentValue());
    }

    @Test
    public void testResettingValueWithStartValueAndStepValue() throws GeneratorLimitReachedException {
        ResettableLongGenerator generator=new ResettableLongGenerator(10L,2);
        generator.getNext();
        Assertions.assertEquals(12L,generator.getCurrentValue());
        generator.reset();
        Assertions.assertEquals(10L,generator.getCurrentValue());
    }

    @Test
    public void testResettingSpecificValue() throws GeneratorLimitReachedException {
        ResettableLongGenerator generator=new ResettableLongGenerator();
        generator.getNext();
        generator.getNext();
        Assertions.assertEquals(2L,generator.getCurrentValue());
        generator.getNext();
        generator.reset(2L);
        Assertions.assertEquals(2L,generator.getCurrentValue());
        generator.getNext();
        Assertions.assertEquals(3L,generator.getCurrentValue());
    }

    @Test
    public void testResettingUpperLimit() throws GeneratorLimitReachedException {
        ResettableLongGenerator generator=new ResettableLongGenerator();
        generator.getNext();
        generator.getNext();
        Assertions.assertEquals(2L,generator.getCurrentValue());
        generator.getNext();
        generator.reset(null);
        Assertions.assertEquals(0L,generator.getCurrentValue());
        generator.getNext();
        Assertions.assertEquals(1L,generator.getCurrentValue());
    }
}
