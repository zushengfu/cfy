package com.javen.service;  

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.CaseInfo;
import com.javen.model.FileInfo;
  
  
public interface CaseInfoService {  
    public AjaxJson createCaseInfo(CaseInfo caseInfo);
    public AjaxJson deleteCaseInfo(CaseInfo caseInfo);
    public AjaxJson updateCaseInfo(CaseInfo caseInfo);
    public List<String> queryCaseInfo(CaseInfo caseInfo);
    public List<String> queryCaseInfoCaseNum(CaseInfo caseInfo);
    public List<String> queryCaseInfoGdCaseYear(CaseInfo caseInfo);
    public List<String> queryCaseInfoGdCaseNum(CaseInfo caseInfo);
    public List<CaseInfo> queryCaseInfoWithCondition(CaseInfo caseInfo);
    public List<Map<String,String>> queryCaseInfoForAjSearch(CaseInfo caseInfo);
    public List<CaseInfo> queryCaseInfoNoCondition();
    public AjaxJson hookFondsInfo(String fondsNum,String newFondsNum);
    public AjaxJson plXgAj(HttpServletRequest request);
}  