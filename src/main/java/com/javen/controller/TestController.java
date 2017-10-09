package com.javen.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javen.function.AjaxJson;
import com.javen.service.FileInfoService;
import com.javen.service.FileRecordsService;
import com.javen.service.IUserService;
  
  
@Controller  
@RequestMapping("/spring-websocket-uptest")  
// /user/**
public class TestController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource  
		private IUserService userService;
		@Resource
		private FileInfoService fileInfoService;
		@Resource
		private FileRecordsService fileRecordsService;
		private  AjaxJson aj=new AjaxJson();
		
		//新增file_records表字段
		@RequestMapping(value="/addCharact.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson addCharact(HttpServletRequest request){
			log.info("进入新增字段控制器");
			userService.updateUserForSetForm(request);
			aj.setMsg("归档新增字段成功...");
			return aj;
		}
		//新增file_records字段值
		@RequestMapping(value="/addNewValue.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson addNewValue(HttpServletRequest request){
			log.info("进入新增某字段的值");
			userService.updateUserForSetForm(request);
			aj.setMsg("成功进入新增某字段值控制器");
			return null;
		}
		
		//新增file_records字段值
		@RequestMapping(value="/websocket.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson websocket(HttpServletRequest request){
			log.info("进入新增某字段的值");
			userService.updateUserForSetForm(request);
			aj.setMsg("成功进入新增某字段值控制器");
			return null;
		}
		
}  