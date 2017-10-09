package com.javen.dao;

import com.javen.model.SystemLog;
import com.javen.model.User;

public interface SystemLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    
    int record(SystemLog systemLog);
}