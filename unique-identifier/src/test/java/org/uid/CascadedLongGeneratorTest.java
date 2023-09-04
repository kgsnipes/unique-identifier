package org.uid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CascadedLongGeneratorTest {

     int regexMatchCount(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    @Test
    public void testMatches()
    {
        Assertions.assertEquals(3,regexMatchCount("(\\$\\d)","$1-$$2"));
    }
}
