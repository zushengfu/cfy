package com.javen.dao;

import java.util.List;

import com.javen.model.FileInfo;
import com.javen.model.FileRecords;

public interface FileRecordsDao {
	int deleteByPrimaryKey(FileRecords fileRecords);
	
    int insert(FileRecords record);

    int insertSelective(FileRecords record);

    FileRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileRecords record);

    int updateByPrimaryKey(FileRecords record);
    
    FileRecords checkFileRecordsExist(FileRecords fileRecords);
    
    int deleteByFileRecords(FileRecords fileRecords);
    
    List<String> queryFileRecords(FileRecords fileRecords);
    
    List<FileRecords> queryFileRecordsNoCondition();
    
    int updateFileRecords(FileRecords fileRecords);
    
    int hookCaseInfo(String fondsNum,String caseNum,String newCaseNum);
    
    int hookFondsInfo(String fondsNum,String newFondsNum);
    
    List<FileRecords> queryFileInfoByThreeCondition(FileRecords fileRecords);
   
    FileRecords getEntity(FileRecords fileRecords);
    
    int updateMoreFileRecords(String fondsNum,String caseNum,String catalogNum,String fileNum,String ziduan,String oldWord,String newWord);

    int cxZs();
}  				