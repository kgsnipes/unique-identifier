package org.uid.impl;

import org.uid.Generator;
import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CascadedLongGenerator implements Generator<String> {

    private final String PLACEHOLDER_REGEX="(\\$\\d)";
    private List<Generator> generatorList=new ArrayList<>();

    private static final int LONG_LENGTH=Long.toString(Long.MAX_VALUE).length();

    private int currentStep=0;
    private String format=null;
    private final Lock lock=new ReentrantLock();
    public CascadedLongGenerator(String format, Generator<Long> ...generators) {

        if(regexMatchCount(PLACEHOLDER_REGEX,format)==generators.length)
        {
            this.format=format;
            IntStream.range(0,generators.length).forEach(i->{
                generatorList.add(generators[i]);
            });
        }
        else {
            throw new IllegalArgumentException("The placeholders do no match with number of generators provided");
        }

    }

    private int regexMatchCount(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
    @Override
    public String getNext() throws GeneratorLimitReachedException, GeneratorException {
        String nextValue="";

        if(hasReachedLimit())
        {
            throw new GeneratorLimitReachedException();
        }

       if(lock.tryLock())
       {
           try
           {
                int LIST_SIZE=generatorList.size();
                String []arr=new String[LIST_SIZE];

                for(int i=0;i<LIST_SIZE;i++)
                {
                    try {
                        if (i == currentStep && generatorList.get(i).hasReachedLimit()) {
                            currentStep++;
                        }

                        if (i == currentStep) {
                            arr[i] = paddingValuesWithZeros(generatorList.get(i).getNext().toString());
                        } else {
                            arr[i] = paddingValuesWithZeros(generatorList.get(i).getCurrentValue().toString());
                        }
                    }
                    catch (GeneratorLimitReachedException | GeneratorException e)
                    {
                        arr[i]=paddingValuesWithZeros(generatorList.get(i).getCurrentValue().toString());
                    }

                }
                nextValue=formatValues(arr);
           }
           finally {
               lock.unlock();
           }
       }
       else {
           throw new GeneratorException("Could not acquire a lock on the Generator");
       }

       return nextValue;
    }


    @Override
    public Boolean hasReachedLimit() {
        return generatorList.size()==generatorList.stream().filter(Generator::hasReachedLimit).count();
    }

    @Override
    public String getCurrentValue() {

        int LIST_SIZE=generatorList.size();
        String []arr=new String[LIST_SIZE];

        for(int i=0;i<LIST_SIZE;i++)
        {
            arr[i] = paddingValuesWithZeros(generatorList.get(i).getCurrentValue().toString());
        }
        return formatValues(arr);
    }

    private String formatValues(String vals[])
    {
        StringBuilder builder=new StringBuilder(this.format);
        IntStream.range(0,vals.length).forEach(i->{
            int position=i+1;
            String token="$"+position;
            int startOffset=builder.indexOf(token);
            int endOffset=startOffset+token.length();
            builder.replace(startOffset,endOffset,vals[i]);
        });
        return builder.toString();
    }

    private String paddingValuesWithZeros(String val)
    {
        if(val.length()<LONG_LENGTH)
        {
            return String.format("%"+(LONG_LENGTH-val.length())+"s", val).replace(' ', '0');
        }
        return val;
    }

    @Override
    public String toString() {
        return getCurrentValue();
    }
}
