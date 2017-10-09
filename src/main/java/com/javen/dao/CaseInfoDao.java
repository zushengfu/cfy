package com.javen.dao;

import java.util.List;

import com.javen.model.CaseInfo;

public interface CaseInfoDao {
    int deleteByPrimaryKey(CaseInfo caseInfo);

    int insert(CaseInfo record);

    int insertSelective(CaseInfo record);

    CaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseInfo record);

    int updateByPrimaryKey(CaseInfo record);
    
    CaseInfo checkCaseExist(CaseInfo caseInfo);
    
    int deleteByCaseInfo(CaseInfo caseInfo);
    
    List<String> queryCaseInfo(CaseInfo caseInfo);
    
    List<String> queryCaseInfoCaseNum(CaseInfo caseInfo);
    
    List<String> queryCaseInfoGdCaseYear(CaseInfo caseInfo);
    
    List<String> queryCaseInfoGdCaseNum(CaseInfo caseInfo);
    
    List<CaseInfo> queryCaseInfoForAjSearch(CaseInfo caseInfo);
    
    List<CaseInfo> queryCaseInfoNoCondition();
    
    int updateByCaseInfo(CaseInfo caseInfo);
    
    int hookFondsInfo(String fondsNum,String newFondsNum);
    
    List<CaseInfo> queryCaseInfoWithCondition(CaseInfo caseInfo);
    
    int updateMoreCaseInfo(String fondsNum,String caseNum,String ziduan,String oldWord,String newWord);
}