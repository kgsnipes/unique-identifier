package org.uid.service.dao;

import org.uid.dto.GeneratorData;
import org.uid.dto.GeneratorRecordData;
import org.uid.dto.HostData;
import org.uid.exception.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface GeneratorDAOService {

    void initDatabase()throws SQLException;
    void setDataSource(DataSource dataSource)throws SQLException;
    GeneratorData createGenerator(GeneratorData data) throws GeneratorCreationException;
    GeneratorData updateLongGenerator(GeneratorData data) throws GeneratorCreationException;
    void deleteGenerator(GeneratorData data)throws GeneratorDeletionException;
    void disableGenerator(GeneratorData data)throws GeneratorDisabledExcepion;
    void postRecentGeneratedValue(GeneratorRecordData record)throws GeneratorRecordSavingException;

    void registerHost(HostData data)throws HostRegistrationException;
    void registerHearBeat(HostData data)throws HostHeartBeatRegistrationException;
}
