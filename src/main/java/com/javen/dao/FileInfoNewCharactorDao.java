package com.javen.dao;

import com.javen.model.FileInfoNewCharactor;

public interface FileInfoNewCharactorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FileInfoNewCharactor record);

    int insertSelective(FileInfoNewCharactor record);

    FileInfoNewCharactor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileInfoNewCharactor record);

    int updateByPrimaryKey(FileInfoNewCharactor record);
    
    FileInfoNewCharactor getSingle(FileInfoNewCharactor record);
}