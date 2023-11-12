package org.uid.service;

import org.uid.dto.GeneratorData;
import org.uid.exception.*;

import java.util.List;

public interface GeneratorService {
    void createGenerator(GeneratorData data) throws GeneratorCreationException;
    GeneratorData updateGenerator(GeneratorData data)throws GeneratorUpdateException;
    void deleteGenerator(String generatorCode) throws GeneratorDeletionException;
    void disableGenerator(GeneratorData data) throws GeneratorDisabledExcepion;
    List<String> getUniqueIdentifiers(String generatorCode,int size)throws GeneratorNotFoundException, GeneratorLimitReachedException,GeneratorException;
}
