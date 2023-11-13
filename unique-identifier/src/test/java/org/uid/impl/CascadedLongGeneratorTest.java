package org.uid.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

import java.lang.reflect.Method;

public class CascadedLongGeneratorTest {

    private static final Logger log= LoggerFactory.getLogger(CascadedLongGeneratorTest.class);

    @BeforeAll
    public static void beforeAll()
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");
    }

    @Test
    public void testGenerator() throws GeneratorLimitReachedException, GeneratorException {
        CascadedLongGenerator generator=new CascadedLongGenerator("$1-$2-$3",new LongGenerator(),new LongGenerator(),new LongGenerator());

    }

    @Test
    public void testFormattingValues() throws NoSuchMethodException {
        Method method = CascadedLongGenerator.class.getDeclaredMethod("formatValues", String[].class);
        method.setAccessible(true);
    }

    @Test
    public void testFormattingValuesWithException() throws NoSuchMethodException {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,()->{
            CascadedLongGenerator generator=new CascadedLongGenerator("$1-$2-$3",new LongGenerator(),new LongGenerator());

        });
    }

    @Test
    public void testNextCascadingStepIncrement() throws GeneratorLimitReachedException, GeneratorException {
        CascadedLongGenerator generator=new CascadedLongGenerator("$1-$2-$3",new LongGenerator(Long.MAX_VALUE-1),new LongGenerator(),new LongGenerator());
        generator.getNext();
        generator.getNext();
        Assertions.assertEquals("9223372036854775806-000000000000000001-000000000000000000",generator.toString());
    }

    @Test
    public void testReachingLimits() throws GeneratorLimitReachedException, GeneratorException {

        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{
            CascadedLongGenerator generator=new CascadedLongGenerator("$1-$2-$3",new LongGenerator(Long.MAX_VALUE),new LongGenerator(Long.MAX_VALUE),new LongGenerator(Long.MAX_VALUE));
            generator.getNext();

        });
    }



}
