package com.javen.dao;

import java.util.List;

import com.javen.model.FondsInfo;
import com.javen.model.FondsInfoVo;

public interface FondsInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FondsInfo record);

    int insertSelective(FondsInfo record);

    FondsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FondsInfo record);

    int updateByPrimaryKey(FondsInfo record);
    
    FondsInfo checkFondExist(FondsInfo fondsInfo);//检查是否存在fondsInfo信息
    
    int deleteByFondsNum(String fondsNum);//通过fondsNum删除fondsInfo
    
    List<String> queryFondsInfo();//无条件查询fondsInfo表中的 fondsNum集
    
    List<FondsInfo> getFondsInfoForAll();//无条件查询fondsInfo表中的所有fondsInfo的详细信息
    
    List<FondsInfo> queryFondsInfoByFondsNameAndFondsNum(FondsInfo fondsInfo);//根据fondsName和fondsNum模糊查询全宗

    int updateMoreFondsInfo(String ziduan,String oldWord,String newWord,String fondsNum);
}