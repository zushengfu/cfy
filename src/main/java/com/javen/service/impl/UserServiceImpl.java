package com.javen.service.impl;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.javen.dao.IUserDao;
import com.javen.function.AjaxJson;
import com.javen.function.SecurityForDESForString;
import com.javen.model.User;
import com.javen.service.IUserService;
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired  
    private IUserDao userDao;  
	private AjaxJson aj;
	private SecurityForDESForString ss=new SecurityForDESForString();
    
    public User getUserById(int userId) {   
        return userDao.selectByPrimaryKey(userId);  
    }
    //检查注册用户是否存在
	@Override
	public User checkRegist(String user_name, String card_number) {
		log.info("进入检查用户信息是否存在ServiceImp层");
		return userDao.checkUser(user_name, card_number);
	}
	//用户注册
	@Override
	public int insertUser(String userName,String userPassword,String cardType,String cardNumber,String telephone,String email,String remarks) throws Exception  {
		log.info("进入用户注册ServiceImp层");
		String up;
		up=ss.encrypt(userPassword);//用户密码加密
		log.info("加密后的用户密码userPassword："+up);
		return userDao.insert(userName,up, cardType, cardNumber, telephone, email,remarks);
		
	}
	//用户登陆
	@Override
	public User login(String userName, String userPassword) {
		log.info("进入用户登陆ServiceImp层");
		return userDao.login(userName, userPassword);
	}
	
	//无条件查询所有用户
	@Override
	public List<User> checkAllUser() {
		log.info("进入到无条件查询所有用户信息ServiceImp层");
		return userDao.checkAllUser();
	}
	//根据身份证号删除某一指定用户
	@Override
	public int deleteUser(String number) {
		log.info("进入到删除某一指定用户ServiceImp层");
		return userDao.deleteUser(number);
	}
	//通过用户名和密码验证用户是否存在
	@Override
	public User getUser(String car_number, String user_password) {
		log.info("进入通过用户名和密码校验ServiceImp层");
		return userDao.getUser(car_number,user_password);
	}
	//用过发过来的用户信息，对用户的权限进行修改
	@Override
	public AjaxJson setUserPower(User user) {
		log.info("进入到修改用户权限ServiceImp层");
		User user1=new User();
		log.info("通过发过来的用户信息中的CarNumber："+user.getCardNumber()+"取到此用户");
		user1=userDao.getUserByOne(user.getCardNumber());
		if(null!=user1){
		log.info("若用户不为空，执行修改用户，即修改权限操作");
		user1.setUserType(user.getUserType());
		userDao.updateByPrimaryKey(user1);
		aj.setSuccess(true);
		return aj;
		}
		else
			log.info("若发过来的CarNumber："+user.getCardNumber()+"无法取到此用户，执行此操作");
			aj.setMsg("对不起，您输入的用户不存在");
			return aj;
		
	}
	//修改用户资料
	@Override
	public int updateUser(User user) {
		log.info("进入到修改用户ServiceImp层，CardNumber："+user.getCardNumber()+" 表示唯一"+" userPassword："+user.getUserPassword()+"为修改后的密码");
		return userDao.updateUserPassword(user.getUserPassword(),user.getCardNumber());
	}
	//根据用户设置表格，修改用户信息
	@Override
	public int updateUserForSetForm(HttpServletRequest request) {
		User user=new User();
		user=(User)request.getSession().getAttribute("user");
		if(null!=request.getParameter("address")){
			user.setaddress(request.getParameter("address"));
		}
		if(null!=request.getParameter("state")){
			user.setstate(request.getParameter("state"));
		}
		if(null!=request.getParameter("beiyong1")){
			user.setbeiyong1(request.getParameter("beiyong1"));
		}
		if(null!=request.getParameter("beiyong2")){
			user.setBeiyong2(request.getParameter("beiyong2"));
		}
		if(null!=request.getParameter("addressValue")){
			user.setAddressValue(request.getParameter("addressValue"));
		}
		if(null!=request.getParameter("stateValue")){
			user.setStateValue(request.getParameter("stateValue"));
		}
		return userDao.updateUserForSetForm(user);
	}
	
	

	
	
	


    
}  
