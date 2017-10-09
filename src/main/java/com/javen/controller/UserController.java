package com.javen.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javen.function.AjaxJson;
import com.javen.function.SecurityForDESForString;
import com.javen.model.User;
import com.javen.service.FileRecordsSendService;
import com.javen.service.IUserService;
import com.javen.service.SystemLogService;
  
  
@Controller  
@RequestMapping("/user")  
// /user/**
public class UserController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource  
		private IUserService userService;  
		@Resource
		private FileRecordsSendService fileRecordsSendService;
		@Resource
		private SystemLogService systemLogService;
		private  AjaxJson aj=new AjaxJson();
		private  User user1;
		private HttpSession session;
		private SecurityForDESForString des=new SecurityForDESForString();
		//用户登陆
		@RequestMapping(value="/login.do",method=RequestMethod.POST)
		public String login(HttpServletRequest request,ModelMap model,HttpServletResponse response) throws Exception{
			log.info("进入到用户登陆控制器");
			String userName=request.getParameter("userName");
			session=request.getSession();
			String userPassword=request.getParameter("userPassword");
			userPassword=des.encrypt(userPassword);
			user1=userService.login(userName,userPassword);
			if(null!=user1){
				if(user1.getUserType().equals("普通")){
					log.info("普通用户开始登陆"+user1.getUserName());
					user1.setUserPassword(des.encrypt(user1.getUserPassword()));
					session.setAttribute("user",user1);
					session.setAttribute("newCount", 0);
					systemLogService.record(user1);
					return "starter";
//					return "testWebsockect";
				}
				if(user1.getUserType().equals("管理员")){
					log.info("管理员用户开始登陆"+user1.getUserName());
					user1.setUserPassword(des.encrypt(user1.getUserPassword()));
					session.setAttribute("user",user1);
					session.setAttribute("newCount", 0);
					systemLogService.record(user1);
					return "admin";
				}
				else {
					log.info("高级管理员用户开始登陆"+user1.getUserName());
					user1.setUserPassword(des.encrypt(user1.getUserPassword()));
					session.setAttribute("user",user1);
					session.setAttribute("newCount", 0);
					systemLogService.record(user1);
					return "administrator";
					}
			}
			else{	
					log.info("用户名或密码错误");
					aj.setMsg("用户名或密码错误");
					model.addAttribute(aj);
					return "regist";
				}
		}
		//--------去session里面的user，以json格式的返回---------
		@RequestMapping(value="/getSessionUser.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson getSessionUser(HttpServletRequest request){
			 log.info("进入获取sesion  控制器");
			 User user=(User)request.getSession().getAttribute("user");
			 aj.setUser(user);
			 return aj;
		}
		//查询所有用户
		@RequestMapping(value="/getAllUserInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public List<User> checkAllUser(){
			log.info("进入获取所有用户控制器");
			return userService.checkAllUser();
		}
		
		
		 //用户注册
		 @RequestMapping(value="/regist.do",method=RequestMethod.POST)
		 public String  regist(User user,HttpServletRequest request,Model model) throws Exception{
			 log.info("进入注册用控制器");
			 user1=userService.checkRegist(user.getUserName(), user.getCardNumber());
			 if(null!=user1){
				 log.info("查询用户已存在");
				 System.out.println("对不起，用户已存在");
				 return "regist";
			 }
			 else
			 log.info("若用户不存在，创建用户");
			 userService.insertUser(user.getUserName(),user.getUserPassword(),user.getCardType(),user.getCardNumber(),user.getTelephone(),user.getEmail(),user.getRemarks());
			 aj.setSuccess(true);
			 return "login";
			 
		 	}
		 
		 	//更改用户权限
		 	@RequestMapping(value="/setUserPower.do",method=RequestMethod.POST)
		 	public @ResponseBody AjaxJson  updateUserPow(@RequestBody User user){
		 		log.info("进入到修改用户控制器");
				return userService.setUserPower(user);
		 	}
		 	
		 	//删除用户
		 	@RequestMapping(value="/deleteUser.do",method=RequestMethod.POST)
		 	public @ResponseBody AjaxJson  deleteUser(@RequestBody User user){
		 		 log.info("进入到删除用户控制器");
				 userService.deleteUser(user.getCardNumber());
				 aj.setMsg("shanchu成功");
				 return aj;
		 	}
		 	
		 	//修改用户密码
			@RequestMapping(value="/setUserPassWord.do",method=RequestMethod.POST)
			@ResponseBody
			public AjaxJson setUserPassWord(User user){
					log.info("进入到修改用户密码控制器");
					if(null!=userService.getUser(user.getCardNumber(),user.getUserPassword())){
						log.info("经查询，用户存在，准备修改用户资料");
						User user1=userService.getUser(user.getCardNumber(),user.getUserPassword());
						log.info("修改前，userPassword:"+user.getUserPassword());
						user1.setUserPassword(user.getNewPassword());
						log.info("修改后，userPassword："+user1.getUserPassword());
						userService.updateUser(user1);
						aj.setMsg("修改成功");
						return aj;
					}
					else{
						log.info("您要修改的用户证件或密码有误");
						aj.setMsg("用户证件号或密码有误");
						return aj;
					}		
			}
}  