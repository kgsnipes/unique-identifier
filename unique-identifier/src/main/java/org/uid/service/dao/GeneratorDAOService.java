package org.uid.service.dao;

import org.uid.dto.GeneratorRecord;
import org.uid.dto.LongGeneratorData;
import org.uid.exception.GeneratorCreationException;
import org.uid.exception.GeneratorDeletionException;
import org.uid.exception.GeneratorDisabledExcepion;
import org.uid.exception.GeneratorRecordSavingException;
import org.uid.impl.LongGenerator;

import java.sql.Connection;
import java.sql.SQLException;

public interface GeneratorDAOService {

    void initDatabase(Connection connection)throws SQLException;
    LongGeneratorData createLongGenerator(LongGeneratorData data) throws GeneratorCreationException;
    LongGeneratorData updateLongGenerator(LongGeneratorData data) throws GeneratorCreationException;
    void deleteLongGenerator(LongGenerator data)throws GeneratorDeletionException;
    void disableLongGenerator(LongGenerator data)throws GeneratorDisabledExcepion;
    void postRecentGeneratedValue(GeneratorRecord record)throws GeneratorRecordSavingException;
}
