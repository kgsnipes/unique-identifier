package org.uid.util;

public class GeneratorUtil {

    public static String paddingValuesWithZeros(String val,Integer valueLength)
    {
        if(val.length()<valueLength)
        {
            String formattedString="%"+(valueLength-val.length())+"s";
            return String.format(formattedString, val).replace(' ', '0');
        }
        return val;
    }
}
