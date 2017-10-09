package com.javen.dao;

import java.util.List;

import com.javen.model.FileInfo;

public interface FileInfoDao {
    int deleteByPrimaryKey(FileInfo fileInfo);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
    
    FileInfo checkFileExist(FileInfo fileInfo);
    
    int deleteFileInfoByCatalogNumAndFileNum(String fileNums);
    
    int deleteByFileInfo(FileInfo fileInfo);
    
    List<String> queryFileInfo(FileInfo fileInfo);
    
    List<FileInfo> queryFileInfoByThreeCondition(FileInfo fileInfo);
    
    List<FileInfo> queryFileInfoNoCondition();
    
    int updateByFileInfo(FileInfo fileInfo);
    
    int hookCaseInfo(String fondsNum,String caseNum,String newCaseNum);
    
    int hookFondsInfo(String fondsNum,String newFondsNum);
    
    int updateMoreFileInfo(String fondsNum,String caseNum,String catalogNum,String fileNum,String ziduan,String oldWord,String newWord);

    int cxZs();
    
}