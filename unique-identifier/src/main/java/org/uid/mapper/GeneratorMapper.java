package org.uid.mapper;

import org.uid.dao.GeneratorDAO;
import org.uid.dto.GeneratorData;

public class GeneratorMapper {

    public static GeneratorDAO generatorDataToGeneratorDAO(GeneratorData data)
    {
        GeneratorDAO dao=new GeneratorDAO();
        dao.setBatched(data.getBatched());
        dao.setCascaded(data.getCascaded());
        dao.setCode(data.getCode());
        dao.setComplete(data.getComplete());
        dao.setCreatedOn(data.getCreatedOn());
        data.getGeneratorList().forEach(d->dao.getGeneratorList().add(generatorDataToGeneratorDAO(d)));
        dao.setDeleted(data.getDeleted());
        dao.setDisabled(data.getDisabled());
        dao.se
        return null;
    }

    public static GeneratorData generatorDAOToGeneratorData(GeneratorDAO data)
    {
        return null;
    }
}
