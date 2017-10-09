package com.javen.service;  

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.FondsInfo;
  
  
public interface FondsInfoService {  
    public AjaxJson createFondsInfo(FondsInfo fondsInfo);//创建fondsInfo
    public AjaxJson deleteFondsInfo(String fondsNum);//根据fondsNum删除全宗
    public List<String> queryFondsInfo();//无条件查询fondsInfo表中，所有fondsNum的集合。
    public List<FondsInfo> getFondsInfoForAll();//无条件查询fondsInfo表中所有fondsInfo的详细信息
    public AjaxJson updateFondsInfo(FondsInfo fondsInfo);
    public List<FondsInfo> queryFondsInfoByFondsNameAndFondsNum(FondsInfo fondsInfo);//根据fondsName和fondsNum模糊查询全宗
    public AjaxJson updateMoreFondsInfo(HttpServletRequest request);
}  