package com.javen.service;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.User;
  
  
public interface IUserService {  
    public User getUserById(int userId);  
    public User checkRegist(String user_name,String card_number);
    public int insertUser(String userName,String userPassword,String cardType,String cardNumber,String telephone,String email,String remarks) throws Exception;
    public User login(String user_name,String user_password);
    public List<User> checkAllUser();
    public int deleteUser(String number);
    public User getUser(String car_number,String user_password);
    public AjaxJson setUserPower(User user);
    public int updateUser(User user);
    public int updateUserForSetForm(HttpServletRequest request);
}  