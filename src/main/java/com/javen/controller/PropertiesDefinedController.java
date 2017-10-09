package com.javen.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javen.function.AjaxJson;
import com.javen.model.CaseInfo;
import com.javen.model.FileDescribeNum;
import com.javen.model.SecurityRank;
import com.javen.service.FileInfoService;
import com.javen.service.FileRecordsService;
import com.javen.service.IUserService;
import com.javen.service.PropertiesService;
  
  
@Controller  
@RequestMapping("/properties")  
// /user/**
public class PropertiesDefinedController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource
		private PropertiesService propertiesService;
		private AjaxJson aj=new AjaxJson();
		
		//新增SEcurityRank
		@RequestMapping(value="/setSecurityRank.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson setSecurityRank(HttpServletRequest request){
				return propertiesService.insertSecurityRank(request);
		}
		
		//新增SEcurityRank
		@RequestMapping(value="/setFileDescribeNum.do",method=RequestMethod.POST)
		@ResponseBody
		public int setFileDescribeNum(@RequestBody FileDescribeNum fileDescribeNum){
				return propertiesService.insertFileDescribeNum(fileDescribeNum);
		}
		//无条件查询文件级别属性值
		@RequestMapping(value="/getSecurityRank.do",method=RequestMethod.POST)
		@ResponseBody
		public List<SecurityRank> getSecurityRank(){
				return propertiesService.getSecurityRank();
		}
		//无条件查询秘密级别属性值
		@RequestMapping(value="/getFileDescribeNum.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileDescribeNum> getFileDescribeNum(){
				return propertiesService.getFileDescribeNum();
		}
		//恢复SecurityRank默认设置
		@RequestMapping(value="/restoreSecurityRank.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson restoreSecurityRank(){
			System.out.println("fdsfsadfsdfasdfsadfas");
				propertiesService.restoreSecurityRank();
				aj.setMsg("成功恢复");
				return aj;
		}
		
		//恢复SecurityRank默认设置
		@RequestMapping(value="/restoreFileDescribeNum.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson restoreFileDescribeNum(){
				System.out.println("fdsfsadfsdfasdfsadfas");
				propertiesService.restoreFileDescribeNum();
				aj.setMsg("成功恢复");
				return aj;
		}
		
		//deleteFileDescribeNum
		@RequestMapping(value="/deleteFileDescribeNum.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson deleteFileDescribeNum(@RequestBody FileDescribeNum fileDescribeNum){
				propertiesService.deleteFileDescribeNum(fileDescribeNum);
				aj.setMsg("成功恢复");
				return aj;
		}
		//deleteSecurityRank
		@RequestMapping(value="/deleteSecurityRank.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson deleteSecurityRank(@RequestBody SecurityRank securityRank){
				propertiesService.deleteSecurityRank(securityRank);
				aj.setMsg("成功恢复");
				return aj;
		}
}  