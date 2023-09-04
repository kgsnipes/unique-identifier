package org.uid.impl;

import org.uid.Generator;
import org.uid.exception.GeneratorLimitReachedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
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
    public String getNext() throws GeneratorLimitReachedException {
       if(lock.tryLock())
       {
           try
           {
                long []arr=new long[generatorList.size()];
                IntStream.range(0,generatorList.size()).forEach(i->{
                    if(!generatorList.get(i).hasReachedLimit())
                    {

                    }else {
                        arr[i]=generatorList.get(i).getCurrentValue();
                    }
                });
           }
           finally {
               lock.unlock();
           }
       }
    }

    @Override
    public Boolean hasReachedLimit() {
        return null;
    }

    @Override
    public String getCurrentValue() {
        return null;
    }
}
