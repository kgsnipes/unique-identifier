package org.uid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.AbstractPersistableBatchLongGenerator;

import javax.sql.DataSource;

public class SQLPersistableBatchLongGenerator extends AbstractPersistableBatchLongGenerator {

    private static final Logger log= LoggerFactory.getLogger(SQLPersistableBatchLongGenerator.class);

    protected DataSource dataSource;

    public static final String GENERATOR_TABLE_NAME="long_generators";

    public SQLPersistableBatchLongGenerator(String name, Long batchSize, DataSource dataSource) {
        //check or create if generators table exist
        //check or create a generator entry and get the instance number
    }
}
