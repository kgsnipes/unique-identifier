package org.uid.exception;

public class GeneratorException extends Exception{

    public GeneratorException() {
        //generating default constructor
        super();
    }

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
