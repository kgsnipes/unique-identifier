package org.uid;

import org.uid.exception.GeneratorLimitReachedException;

public interface Generator<T> {

    T getNext() throws GeneratorLimitReachedException;

    Boolean hasReachedLimit();

    T getCurrentValue();
}
