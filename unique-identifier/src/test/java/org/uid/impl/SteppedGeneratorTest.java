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

    @Test
    public void testStepGeneratorForResetWithValue() throws GeneratorLimitReachedException, GeneratorException {

        ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE-2,1,'=');
            String v1=generator.getNext();
            generator.getNext();
            generator.reset(v1);
            Assertions.assertEquals(Long.MAX_VALUE+"="+(Long.MAX_VALUE-1),generator.getCurrentValue());


    }

    @Test
    public void testStepGeneratorForResetWithNullValue() throws GeneratorLimitReachedException, GeneratorException {

        Assertions.assertThrowsExactly(GeneratorException.class,()->{
        ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE-2,1,'=');
        generator.reset(null);
        });
    }

    @Test
    public void testStepGeneratorForResetWithInvalidValue() throws GeneratorLimitReachedException, GeneratorException {

        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE-2,1,'=');
            generator.reset("34987298437324!2794873234");
        });

    }

    @Test
    public void testStepForLimits() throws GeneratorLimitReachedException, GeneratorException {

        Assertions.assertThrowsExactly(GeneratorLimitReachedException.class,()->{
            ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE,1,'=');
            generator.getNext();
            generator.getNext();
        });
    }
    @Test
    public void testStepGeneratorForReset() throws GeneratorLimitReachedException, GeneratorException {

        ResettableGenerator<String,String> generator=new SteppedGenerator(Long.MAX_VALUE,Long.MAX_VALUE-2,1,'=');
        String v1=generator.getNext();
        generator.getNext();
        generator.reset();
        Assertions.assertEquals(Long.MAX_VALUE+"="+(Long.MAX_VALUE-2),generator.getCurrentValue());


    }

    @Test
    public void testConstructors() throws GeneratorException {
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(null);
        });
        new SteppedGenerator(1);
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1,null);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(null,'-');
        });
        new SteppedGenerator(1,'-');
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1L,-1);
        });
        new SteppedGenerator(1L,1);
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(-1L,1);
        });

        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1l,1,null);
        });
        new SteppedGenerator(1l,1,'-');
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1l,null,'-');
        });

        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(-1l,1,'-');
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1L,1l,null,'-');
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(-1L,1l,null,'-');
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1L,1l,1,null);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1L,-1l,1,'-');
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(-1L,1l,1,'-');
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(-1L,-1l,-1);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1L,-1l,-1);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(null,1l,-1);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1l,1l,-1);
        });
        Assertions.assertThrowsExactly(GeneratorException.class,()->{
            new SteppedGenerator(1l,null,-1);
        });

        new SteppedGenerator(1L,1l,1,'-');

    }
}
