package com.javen.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javen.function.AjaxJson;
import com.javen.function.FileInfoDefined;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoVo;
import com.javen.model.FileRecordsSend;
import com.javen.service.FileInfoService;
import com.sun.net.httpserver.HttpsConfigurator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller  
@RequestMapping("/fond")  
public class FileInfoController { 
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource  
		private FileInfoService fileInfoService;
		private AjaxJson aj=new AjaxJson();
		//创建归档
		@RequestMapping(value="/adXzGd.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xzJn(@RequestBody FileInfoVo fileInfoVo){
			log.info("进入创建归档控制器");
			return fileInfoService.createFileInfo(fileInfoVo);
		}
		//删除归档
		@RequestMapping(value="/adScGd.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson scJn(@RequestBody FileInfo fileInfo){
			log.info("进入单个删除归档控制器");
			return fileInfoService.deleteFileInfo(fileInfo);
		}
		//批量删除归档
		@RequestMapping(value="/adPlScGd.do",method=RequestMethod.POST)
		 @ResponseBody
		 public AjaxJson updateFondsInfo(HttpServletRequest request) throws IOException
		 {
			log.info("进入批量删除归档控制器");
			ServletInputStream inputStream = request.getInputStream();  
            String mybooksid = IOUtils.toString(inputStream);  
            JSONArray json=JSONArray.fromObject(mybooksid);
//          JSONObject jsonOne;
            for(int i=0;i<json.size();i++){
//                jsonOne = json.getJSONObject(i); 
                fileInfoService.deleteFileInfoByFileInfo((FileInfo)json.get(0));
            }
			aj.setMsg("删除已完成");
			return aj;
		 }
		//修改归档
		@RequestMapping(value="/adXgGd.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xgGd(@RequestBody FileInfo fileInfo){
			log.info("进入修改归档控制器");
			return fileInfoService.updateFileInfo(fileInfo);
		}
		//查询归档 返回归档文件号
		@RequestMapping(value="/adCxGd.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cgGd(@RequestBody FileInfo fileInfo){
			log.info("进入查询归档，返回归档文件号控制器！");
			return fileInfoService.queryFileInfo(fileInfo);
		}
		//模糊搜索归档（FileInfo）返回归档详细信息
		@RequestMapping(value="/adMhCxGd.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileInfo> mhCxGd(@RequestBody FileInfo fileInfo){
			log.info("进入模糊搜索归档控制器");
			return fileInfoService.queryFileInfoByThreeCondition(fileInfo);
		}
		//无条件查询归档，返回归档详细信息
		@RequestMapping(value="/adCxGdAllInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileInfoDefined> cxGdNoCondition(HttpServletRequest request){
			log.info("进入无条件查询归档，返回归档详细信息控制器！");
			return fileInfoService.queryFileInfoNoCondition();
		}
		
		//批量修改归档
		@RequestMapping(value="/plXgGd.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson plXgGd(HttpServletRequest request){
			log.info("进入批量修改归档控制器");
			return fileInfoService.plXgGd(request);
		}
		
}