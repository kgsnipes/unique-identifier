import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uid.impl.LongGenerator;

public class LongGeneratorTest {

    LongGenerator longGenerator;


    @Test
    public void longGeneratorTest()
    {
        longGenerator=new LongGenerator();
        Assertions.assertEquals(1L,longGenerator.getNext());
    }
}
