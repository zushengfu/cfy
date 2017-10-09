package com.javen.dao;

import com.javen.model.FileInfoPicture;

public interface FileInfoPictureDao {
    int deleteByPrimaryKey(FileInfoPicture fileInfoPicture);

    int insert(FileInfoPicture record);

    int insertSelective(FileInfoPicture record);

    FileInfoPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileInfoPicture record);

    int updateByPrimaryKey(FileInfoPicture record);
    
    int addPicture(String fondsNum,String fileNum,String filePath);
    
    int addPicture(String fondsNum,String fileYear,String caseNum,String fileNum,String picName);

    int queryPicNameCount(String fondsNum,String fileYear,String caseNum);//查询条件下的picName的数量
    
    FileInfoPicture getAllFileInfoPictureByFilePath(String filePath);//查询picName下的所有信息
    
    int deleteByFileInfoPicture(FileInfoPicture fileInfoPicture);
    
    int dePicture(String address);
}