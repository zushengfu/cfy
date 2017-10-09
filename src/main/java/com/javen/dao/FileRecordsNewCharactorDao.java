package com.javen.dao;

import com.javen.model.FileRecordsNewCharactor;

public interface FileRecordsNewCharactorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FileRecordsNewCharactor record);

    int insertSelective(FileRecordsNewCharactor record);

    FileRecordsNewCharactor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileRecordsNewCharactor record);

    int updateByPrimaryKey(FileRecordsNewCharactor record);
    
    FileRecordsNewCharactor getSingle(FileRecordsNewCharactor fileRecordsNewCharactor);
}