package com.javen.dao;

import java.util.List;

import com.javen.model.FileRecordsPicture;

public interface FileRecordsPictureDao {
    int deleteByPrimaryKey(FileRecordsPicture fileRecordsPicture);

    int insert(FileRecordsPicture record);

    int insertSelective(FileRecordsPicture record);

    FileRecordsPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileRecordsPicture record);

    int updateByPrimaryKey(FileRecordsPicture record);
    
    int addPicture(String fondsNum,String catalogNum,String caseNum,String fileNum,String picName);

    int queryPicNmaeCount(String fondsNum,String catalogNum,String caseNum);
    
    FileRecordsPicture getAllFileRecordsPictureByFilePath(String filePath);
    
    List<String> getJnTpAdd(String fondsNum,String catalogNum,String fileNum);
    
    int deleteByFileRecordsPicture(FileRecordsPicture fileRecordsPicture);
    
    int dePicture(String picName);
    
    int updateByPicName(FileRecordsPicture fileRecordsPicture);
}