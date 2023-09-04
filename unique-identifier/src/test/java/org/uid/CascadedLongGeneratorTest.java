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

    public String formatValues(String format,String vals[])
    {
        StringBuilder builder=new StringBuilder(format);
        Pattern pattern = Pattern.compile("(\\$\\d)");
        Matcher matcher = pattern.matcher(builder);
        int matches = 0;
        while (matcher.find()) {
            builder.replace(matcher.start(),matcher.end(),vals[matches]);
            matcher = pattern.matcher(builder);
            matches++;
        }
        return builder.toString();
    }

    @Test
    public void testMatches()
    {
        String val=formatValues("$1##$2-$3",new String[]{"hello","world","me"});
        System.out.println(val);
        Assertions.assertEquals("hello##world-me",val);
    }
}
