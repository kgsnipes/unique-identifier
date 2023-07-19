package org.uid.services;

import org.uid.services.dto.Generator;
import org.uid.services.dto.GeneratorEntry;
import org.uid.services.exception.CannotPersistGenerator;
import org.uid.services.exception.CannotPersistGeneratorEntry;
import org.uid.services.exception.GeneratorAlreadyExists;
import org.uid.services.exception.GeneratorEntryAlreadyExists;

public interface GeneratorService {

    void saveGenerator(Generator generator,Boolean isUpdate)throws GeneratorAlreadyExists, CannotPersistGenerator;

    void deleteGenerator(Generator generator)throws GeneratorAlreadyExists, CannotPersistGenerator;

    void addEntry(GeneratorEntry entry)throws GeneratorEntryAlreadyExists, CannotPersistGeneratorEntry;

}
