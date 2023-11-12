package org.uid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.uid.dao.GeneratorDAO;
import org.uid.dto.GeneratorData;

@Mapper
public interface GeneratorMapper {

    GeneratorMapper INSTANCE= Mappers.getMapper(GeneratorMapper.class);

    GeneratorData generatorDAOToDTO(GeneratorDAO dao);
    GeneratorDAO generatorDTOToDAO(GeneratorData dto);
}
