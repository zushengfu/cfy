package com.javen.dao;

import java.util.List;

import com.javen.model.FileDescribeNum;

public interface FileDescribeNumDao {
    int deleteByPrimaryfKey(FileDescribeNum fileDescribeNum);

    int insert(FileDescribeNum record);

    int insertSelective(FileDescribeNum record);

    FileDescribeNum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileDescribeNum record);

    int updateByPrimaryKey(FileDescribeNum record);
    
    List<FileDescribeNum> getFileDescribeNum();
    
    int restoreFileDescribeNum();
}