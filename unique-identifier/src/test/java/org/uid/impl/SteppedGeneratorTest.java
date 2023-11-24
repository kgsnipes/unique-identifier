package org.uid.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uid.ResettableGenerator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

public class SteppedGeneratorTest {

    @BeforeAll
    public static void beforeAll()
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","debug");
    }

    @Test
    public void testStepGeneratorValue() throws GeneratorLimitReachedException, GeneratorException {
        ResettableGenerator<String,String> generator=new SteppedGenerator(2L,2L,2,'=');
        String value1= generator.getNext();
        Assertions.assertEquals("000000000000000002=000000000000000004",value1);
    }

    @Test
    public void testStepGeneratorValueForLimitExceededOnGenerator() throws GeneratorLimitReachedException, GeneratorException {
        ResettableGenerator<String,String> generator=new SteppedGenerator(2L,Long.MAX_VALUE-2,2,'=');
        generator.getNext();
        String value1= generator.getNext();
        Assertions.assertEquals("000000000000000003=000000000000000002",value1);
    }

    @Test
    public void testStepGeneratorValueForLimitExceededOnGeneratorAndStepGenerator(){

        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{

            ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE-1,2,'=');
            generator.getNext();
            generator.getNext();
            System.out.println(generator.getCurrentValue());
        });

    }
}
