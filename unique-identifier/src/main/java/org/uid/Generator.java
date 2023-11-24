package org.uid;

import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

public interface Generator<T> {

    int LONG_LENGTH=Long.toString(Long.MAX_VALUE).length();

    T getNext() throws GeneratorLimitReachedException, GeneratorException;

    boolean hasReachedLimit();

    T getCurrentValue();
}
