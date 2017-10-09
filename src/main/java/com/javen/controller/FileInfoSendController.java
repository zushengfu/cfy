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
import com.javen.model.FileInfo;
import com.javen.model.FileInfoSend;
import com.javen.model.User;
import com.javen.service.FileInfoSendService;
import com.javen.service.FileInfoService;
import com.sun.net.httpserver.HttpsConfigurator;

@Controller  
@RequestMapping("/fond")  
public class FileInfoSendController { 
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource  
		private FileInfoSendService fileInfoSendService;
		private AjaxJson aj=new AjaxJson();
		//移交  fileInfo。
		@RequestMapping(value="/adYjGd.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adYjGd(@RequestBody FileInfoSend fileInfoSend){
			log.info("进入移交归档文件的控制器");
			return fileInfoSendService.sendFileInfo(fileInfoSend);
		}
		//计算数据库新增数据条数
		@RequestMapping(value="/adYjGdNewCount.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adYjGdNewCount(){
			log.info("进入计算数据库新增数条数的控制器");
//			return fileInfoSendService.sendFileInfo(fileInfoSend);
			return null;
		}
}