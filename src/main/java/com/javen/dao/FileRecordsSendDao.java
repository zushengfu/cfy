package com.javen.dao;

import java.util.List;

import com.javen.model.FileRecordsPicture;
import com.javen.model.FileRecordsSend;

public interface FileRecordsSendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FileRecordsSend record);

    int insertSelective(FileRecordsSend record);

    FileRecordsPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileRecordsSend record);

    int updateByPrimaryKey(FileRecordsSend record);
    
    List<FileRecordsSend> acceptFileREcordsSend(FileRecordsSend fileRecordsSend);
    
    List<FileRecordsSend> cxYjJn();
    
    int updateFileRecordsSend(FileRecordsSend fileRecordsSend);
    
    int cxYjJnNewCount();
}