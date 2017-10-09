package com.javen.service;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.function.FileRecordsDefined;
import com.javen.model.FileInfo;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsVo;
  
  
public interface FileRecordsService {  
    public AjaxJson createFileRecords(FileRecordsVo fileRecordsVo);
    public AjaxJson deleteFileRecords(FileRecords fileRecords);
    public AjaxJson updateFileRecords(FileRecords fileRecords);
    public List<String> queryFileRecords(FileRecords fileRecords);
    public List<FileRecordsDefined> queryFileRecordsNoCondition();
    public AjaxJson hookCaseInfo(String fondsNum,String caseNum,String newCaseNum);
    public AjaxJson hookFondsInfo(String fondsNum,String newFondsNum);
    public List<FileRecords> queryFileRecordsByThreeCondition(FileRecords fileRecords);
    public AjaxJson plXgJn(HttpServletRequest request);
    public int cxZs();
}  