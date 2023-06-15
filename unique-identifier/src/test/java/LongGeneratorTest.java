import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uid.impl.LongGenerator;

import java.util.stream.IntStream;

public class LongGeneratorTest {

    LongGenerator longGenerator;


    @Test
    public void longGeneratorTest()
    {
        longGenerator=new LongGenerator();
        Assertions.assertEquals(1L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorCountTest()
    {
        longGenerator=new LongGenerator();
        IntStream.range(0,10).forEach(num->longGenerator.getNext());
        Assertions.assertEquals(11L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorHighCountTest()
    {
        longGenerator=new LongGenerator();
        IntStream.range(0,10000).forEach(num->longGenerator.getNext());
        Assertions.assertEquals(10001L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithStartValueTest()
    {
        longGenerator=new LongGenerator(100L);
        IntStream.range(0,1000).forEach(num->longGenerator.getNext());
        Assertions.assertEquals(1100L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithStepValueTest()
    {
        longGenerator=new LongGenerator(100L,2);
        IntStream.range(0,1000).forEach(num->longGenerator.getNext());
        Assertions.assertEquals(2100L,longGenerator.getNext());
    }

    @Test
    public void longGeneratorWithNegativeStepValueTest()
    {
        longGenerator=new LongGenerator(100L,-1);
        IntStream.range(0,10).forEach(num->longGenerator.getNext());
        Assertions.assertEquals(90L,longGenerator.getNext());
    }
}
