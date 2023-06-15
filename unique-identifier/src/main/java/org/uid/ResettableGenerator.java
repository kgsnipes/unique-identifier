package org.uid;

public interface ResettableGenerator<U, T> extends Generator<T>{

    void reset();
    void reset(U value);
}
