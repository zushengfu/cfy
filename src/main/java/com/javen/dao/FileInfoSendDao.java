package com.javen.dao;

import java.util.List;

import com.javen.model.FileInfoPicture;
import com.javen.model.FileInfoSend;

public interface FileInfoSendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FileInfoSend fileInfoSend);

    int insertSelective(FileInfoSend record);

    FileInfoPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileInfoSend record);

    int updateByPrimaryKey(FileInfoSend record);
    
    List<FileInfoSend> acceptFileInfo(FileInfoSend fileInfoSend);
}