package com.javen.dao;

import java.util.List;

import com.javen.model.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(String userName,String userPassword,String cardType,String cardNumber,String telephone,String email,String reamarks);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User login(String userName,String userPassword);
    
    User checkUser(String user_name,String card_number);
    
    List<User> checkAllUser();
    
    User getVerifyUserInfo();
    
    int deleteUser(String number);
    
    User getUser(String cardNumber,String userPassword);
    
    User getUserByOne(String cardNumber);
    
    int updateTelephone(String telephone);
    
    int updateUserPassword(String userPassword,String cardNumber);
    
    User getUserByNameAndPassword(String userName,String userPassword);
    
    int updateUserForSetForm(User user);
}