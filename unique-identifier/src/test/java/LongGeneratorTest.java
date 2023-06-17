import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.impl.LongGenerator;

import java.util.stream.IntStream;


/*
* -Dorg.slf4j.simpleLogger.defaultLogLevel=DEBUG
* */
public class LongGeneratorTest {

    LongGenerator longGenerator;


    @Test
    public void longGeneratorTest() throws Exception
    {
        longGenerator=new LongGenerator();
        Assertions.assertEquals(1L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorCountTest()throws Exception
    {
        longGenerator=new LongGenerator();
        IntStream.range(0,10).forEach(num-> {
            try {
                longGenerator.getNext();
            } catch (GeneratorLimitReachedException e) {
                throw new RuntimeException(e);
            }
        });
        Assertions.assertEquals(11L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorHighCountTest()throws Exception
    {
        longGenerator=new LongGenerator();
        IntStream.range(0,10000).forEach(num-> {
            try {
                longGenerator.getNext();
            } catch (GeneratorLimitReachedException e) {
                throw new RuntimeException(e);
            }
        });
        Assertions.assertEquals(10001L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithStartValueTest()throws Exception
    {
        longGenerator=new LongGenerator(100L);
        IntStream.range(0,1000).forEach(num-> {
            try {
                longGenerator.getNext();
            } catch (GeneratorLimitReachedException e) {
                throw new RuntimeException(e);
            }
        });
        Assertions.assertEquals(1100L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithStepValueTest()throws Exception
    {
        longGenerator=new LongGenerator(100L,2);
        IntStream.range(0,1000).forEach(num-> {
            try {
                longGenerator.getNext();
            } catch (GeneratorLimitReachedException e) {
                throw new RuntimeException(e);
            }
        });
        Assertions.assertEquals(2100L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithNegativeStepValueTest()throws Exception
    {
        longGenerator=new LongGenerator(100L,-1);
        IntStream.range(0,10).forEach(num-> {
            try {
                longGenerator.getNext();
            } catch (GeneratorLimitReachedException e) {
                throw new RuntimeException(e);
            }
        });
        Assertions.assertEquals(90L,longGenerator.getNext());
    }
}
