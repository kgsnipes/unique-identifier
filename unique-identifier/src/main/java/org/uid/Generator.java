package org.uid;

import org.uid.exception.GeneratorException;
import org.uid.exception.GeneratorLimitReachedException;

public interface Generator<T> {

    T getNext() throws GeneratorLimitReachedException, GeneratorException;

    Boolean hasReachedLimit();

    T getCurrentValue();
}
