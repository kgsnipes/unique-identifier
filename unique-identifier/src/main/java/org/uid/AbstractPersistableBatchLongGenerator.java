package org.uid;

public abstract class AbstractPersistableBatchLongGenerator {

    protected String Name;
    protected Generator generator;
    protected Integer instance;
    protected Integer batchSize;

    protected String getName() {
        return Name;
    }

    protected void setName(String name) {
        Name = name;
    }

    protected Generator getGenerator() {
        return generator;
    }

    protected void setGenerator(Generator generator) {
        this.generator = generator;
    }

    protected Integer getInstance() {
        return instance;
    }

    protected void setInstance(Integer instance) {
        this.instance = instance;
    }

    protected Integer getBatchSize() {
        return batchSize;
    }

    protected void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
}
