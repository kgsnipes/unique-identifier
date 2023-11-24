package org.uid;

import org.uid.exception.GeneratorException;

public interface ResettableGenerator<U, T> extends Generator<T>{

    void reset();
    void reset(U value) throws GeneratorException;
}
