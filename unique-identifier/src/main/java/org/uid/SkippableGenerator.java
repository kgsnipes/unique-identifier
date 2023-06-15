package org.uid;

public interface SkippableGenerator<U, T> extends  ResettableGenerator<U,T>{
    void skip();
}
