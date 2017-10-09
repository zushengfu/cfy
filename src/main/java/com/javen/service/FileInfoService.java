package com.javen.service;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.function.FileInfoDefined;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoNewCharactor;
import com.javen.model.FileInfoVo;
import com.javen.model.FondsInfo;
  
  
public interface FileInfoService {  
    public AjaxJson createFileInfo(FileInfoVo fileInfoVo);
    public AjaxJson deleteFileInfoByFileInfo(FileInfo fileInfo);
    public AjaxJson updateFileInfo(FileInfo fileinfo);
    public List<String> queryFileInfo(FileInfo fileInfo);
    public AjaxJson deleteFileInfo(FileInfo fileInfo);
    public List<FileInfo> queryFileInfoByThreeCondition(FileInfo fileInfo);
    public AjaxJson hookCaseInfo(String fondsNum,String caseNum,String newCaseNum);
    public AjaxJson hookFondsInfo(String fondsNum,String newFondsNum);
    public List<FileInfoDefined> queryFileInfoNoCondition();
    public AjaxJson plXgGd(HttpServletRequest request);
    public int cxZs();
    
}  