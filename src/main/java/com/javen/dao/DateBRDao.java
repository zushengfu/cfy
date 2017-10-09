package com.javen.dao;

import java.util.List;

import com.javen.model.DateBR;

public interface DateBRDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DateBR record);

    int insertSelective(DateBR record);

    DateBR selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DateBR record);

    int updateByPrimaryKey(DateBR record);
    
    List<DateBR> showRestoreDB();
}