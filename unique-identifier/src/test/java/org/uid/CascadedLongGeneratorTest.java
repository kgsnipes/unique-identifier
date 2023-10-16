package org.uid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;
import org.uid.impl.CascadedLongGenerator;
import org.uid.impl.LongGenerator;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CascadedLongGeneratorTest {

    @Test
    public void testGenerator() throws GeneratorLimitReachedException, GeneratorException {
        CascadedLongGenerator generator=new CascadedLongGenerator("$1-$2-$3",new LongGenerator(),new LongGenerator(),new LongGenerator());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());

    }

    @Test
    public void testFormattingValues() throws NoSuchMethodException {
        Method method = CascadedLongGenerator.class.getDeclaredMethod("formatValues", String[].class);
        method.setAccessible(true);
    }


}
