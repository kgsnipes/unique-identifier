package org.uid.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.impl.BatchLongGenerator;

public class BatchLongGeneratorTest {

    @BeforeAll
    public static void beforeAll()
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");
    }


    @Test
    public void testBatchGenerator() throws GeneratorLimitReachedException, GeneratorException {
        BatchLongGenerator generator=new BatchLongGenerator(10000L);
        generator.getNext();
        generator.getNext();
        Assertions.assertEquals(2L,generator.getCurrentValue());

    }

    @Test
    public void testBatchGeneratorWithStartValue() throws GeneratorLimitReachedException, GeneratorException {
        BatchLongGenerator generator=new BatchLongGenerator(10000L,10L);
        generator.getNext();
        generator.getNext();
        Assertions.assertEquals(10002L,generator.getCurrentValue());

    }

    @Test
    public void testBatchGeneratorWithSmallBatchSize() throws GeneratorLimitReachedException, GeneratorException {
        BatchLongGenerator generator=new BatchLongGenerator(10000L,2L);
        generator.getNext();
        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{
            generator.getNext();
        });
    }

    @Test
    public void testBatchGeneratorWithStepValue() throws GeneratorLimitReachedException, GeneratorException {
        BatchLongGenerator generator=new BatchLongGenerator(10000L,2,2L);
        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{
            generator.getNext();
        });
    }

    @Test
    public void testBatchGeneratorWithNegativeStepValue() throws GeneratorLimitReachedException, GeneratorException {
        BatchLongGenerator generator=new BatchLongGenerator(10000L,-2,2L);
        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{
            generator.getNext();
        });
    }

}
