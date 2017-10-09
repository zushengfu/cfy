package com.javen.dao;

import java.util.List;

import com.javen.model.SecurityRank;

public interface SecurityRankDao {
    int deleteByPrimarysKey(SecurityRank securityRank);

    int insert(SecurityRank record);

    int insertSelective(SecurityRank record);

    SecurityRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecurityRank record);

    int updateByPrimaryKey(SecurityRank record);
    
    List<SecurityRank> getSecurityRank();
    
    int restoreSecurityRank();
    
    SecurityRank check(SecurityRank securityRank);
}