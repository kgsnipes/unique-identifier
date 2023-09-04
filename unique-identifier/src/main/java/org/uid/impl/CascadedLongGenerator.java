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

    private Boolean stepCompletion[];
    private String format=null;

    private final Lock lock=new ReentrantLock();
    public CascadedLongGenerator(String format, Generator<Long> ...generators) {

        if(regexMatchCount(PLACEHOLDER_REGEX,format)==generators.length)
        {
            this.format=format;
            stepCompletion=new Boolean[generators.length];
            IntStream.range(0,generators.length).forEach(i->{
                stepCompletion[i]=false;
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
       if(lock.tryLock())
       {
           try
           {
                String []arr=new String[generatorList.size()];
                IntStream.range(0,generatorList.size()).forEach(i->{
                    try {
                        arr[i]=generatorList.get(i).getNext().toString();
                    } catch (GeneratorLimitReachedException | GeneratorException e) {
                        arr[i]=generatorList.get(i).getCurrentValue().toString();
                    }
                });
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
        return null;
    }

    private String formatValues(String vals[])
    {
        StringBuilder builder=new StringBuilder(this.format);
        Pattern pattern = Pattern.compile(PLACEHOLDER_REGEX);
        Matcher matcher = pattern.matcher(builder);
        int matches = 0;
        while (matcher.find()) {
            builder.replace(matcher.start(),matcher.end(),vals[matches]);
            matcher = pattern.matcher(builder);
            matches++;
        }
        return builder.toString();
    }
}
