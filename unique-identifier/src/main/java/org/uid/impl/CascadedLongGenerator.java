package org.uid.impl;

import org.uid.Generator;
import org.uid.exception.GeneratorLimitReachedException;

import java.util.ArrayList;
import java.util.List;

public class CascadedLongGenerator implements Generator<String> {

    private List<Generator> generatorList=new ArrayList<>();
    private String format=null;
    public CascadedLongGenerator(String format, Generator<? extends Number> ...generators) {


    }

    @Override
    public String getNext() throws GeneratorLimitReachedException {
        return null;
    }
}
