package org.uid.service.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.jdbc.db.SqliteDatabaseType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uid.dao.GeneratorDAO;
import org.uid.dao.GeneratorRecordDAO;
import org.uid.dao.HostDAO;
import org.uid.dto.GeneratorData;
import org.uid.exception.*;
import org.uid.impl.LongGenerator;
import org.uid.service.GeneratorService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGeneratorService implements GeneratorService {
    
    private DataSource dataSource;
    private Map<String,Class> daoClassMap=new HashMap<>();
    private Map<String, Dao> daoMap=new HashMap<>();
    private static Boolean INIT_COMPLETE=false;

    private static final Logger log= LoggerFactory.getLogger(DefaultGeneratorService.class);


    public DefaultGeneratorService(DataSource dataSource) throws GeneratorException {
        try {
            loadDaoClassMap();
            if(null!=dataSource && null!=dataSource.getConnection()) {
                setDataSource(dataSource);
                initDatabase();
            }
            else {
                throw new GeneratorException("Datasource or connection is null.");
            }
        } catch (SQLException e) {
            throw new GeneratorException("Datasource is null or connection to the database is failing");
        }
    }

    public Map<String, Class> getDaoClassMap() {
        return daoClassMap;
    }

    public void setDaoClassMap(Map<String, Class> daoClassMap) {
        this.daoClassMap = daoClassMap;
    }

    public Map<String, Dao> getDaoMap() {
        return daoMap;
    }

    public void setDaoMap(Map<String, Dao> daoMap) {
        this.daoMap = daoMap;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void initDatabase() throws GeneratorException {
        if(!INIT_COMPLETE) {
            createDAOs();
        }
        if(!INIT_COMPLETE)
        {
            throw new GeneratorException("Generator Service is not initiated..");
        }
    }

    private void createDAOs() throws GeneratorException {
        ConnectionSource connectionSource = getConnectionSource();
        initDAOs(connectionSource);
        createTables(connectionSource);
    }

    private void initDAOs(ConnectionSource connectionSource)
    {
        getDaoClassMap().forEach((k, v)->{
            try {
                getDaoMap().put(k, DaoManager.createDao(connectionSource,v));
            } catch (SQLException e) {
                log.error("Exception occurred ",e);
            }
        });
    }

    private ConnectionSource getConnectionSource() throws GeneratorException {
        try {
            return new DataSourceConnectionSource(getDataSource(), new SqliteDatabaseType());
        } catch (SQLException e) {
            throw new GeneratorException("could not establish connection source");
        }

    }

    private void createTables(ConnectionSource connectionSource)
    {
        getDaoMap().forEach((k,v)->{
            if(!isTableAvailable(v.getTableName()))
            {
                try {
                    TableUtils.createTable(getDaoMap().get(k));
                } catch (SQLException e) {
                    log.error("not able to create table",e);
                }
            }
        });
    }

    private Boolean isTableAvailable(String tableName)
    {
       boolean tableAvailable=false;
        try {
            DatabaseMetaData meta = getDataSource().getConnection().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            tableAvailable= resultSet.next();
        } catch (SQLException e) {
            tableAvailable=false;
        }
        return tableAvailable;
    }

    private void loadDaoClassMap(){
        getDaoClassMap().put("Generator", GeneratorDAO.class);
        getDaoClassMap().put("GeneratorRecord", GeneratorRecordDAO.class);
        getDaoClassMap().put("Host", HostDAO.class);
    }

    @Override
    public void createGenerator(GeneratorData data) throws GeneratorCreationException {
        
    }

    @Override
    public GeneratorData updateGenerator(GeneratorData data) throws GeneratorUpdateException {
        return null;
    }

    @Override
    public void deleteGenerator(String generatorCode) throws GeneratorDeletionException {

    }

    @Override
    public void resetGenerator(String generatorCode, Long... resetPoints) throws GeneratorDeletionException {

    }

    @Override
    public void disableGenerator(String generatorCode) throws GeneratorDisabledExcepion {

    }

    @Override
    public List<String> getUniqueIdentifiers(String generatorCode, int size) throws GeneratorNotFoundException, GeneratorLimitReachedException, GeneratorException {
        return null;
    }
}
