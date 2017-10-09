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
import com.javen.function.FileRecordsDefined;
import com.javen.model.FileInfo;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsVo;
import com.javen.service.FileRecordsService;

import net.sf.json.JSONArray;

@Controller  
@RequestMapping("/fond")  
public class FileRecordsController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		
		@Resource  
		private FileRecordsService fileRecordsService;
		private AjaxJson aj=new AjaxJson();
		 
		//创建卷内
		@RequestMapping(value="/adXzJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xzGd(@RequestBody FileRecordsVo fileRecordsVo){
			log.info("进入创建卷内控制器");
			return fileRecordsService.createFileRecords(fileRecordsVo);
		}
		
		//修改卷内
		@RequestMapping(value="/adXgJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xgGd(@RequestBody FileRecords fileRecords){
			log.info("进入修改卷内控制器");
			return fileRecordsService.updateFileRecords(fileRecords);
		}
		
		//查询卷内，返回catalogNum
		@RequestMapping(value="/adCxJn.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxGd(@RequestBody FileRecords fileRecords){
			log.info("进入查询卷内，返回catalogNum控制器");
			return fileRecordsService.queryFileRecords(fileRecords);
		}
		
		//无条件查询卷内，返回卷内详细信息
		@RequestMapping(value="/adCxJnAllInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileRecordsDefined> cxGdNoCondition(){
			log.info("进入无条件查询卷内，返回卷内详细信息控制器");
			return fileRecordsService.queryFileRecordsNoCondition();
		}
		
		//挂接全宗业务的操作，（参数：fondsNum，newFondsNum）
		@RequestMapping(value="/fileRecordsHookFondsNum.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson hookFondsNum(HttpServletRequest request){
			log.info("进入挂接全宗业务修改后的控制器   参数fondsNum:"+request.getParameter("fondsNum")
					+"newFonedsNum:"+request.getParameter("newFondsNum"));
			String fondsNum=request.getParameter("fondsNum");
			String newFondsNum=request.getParameter("newFondsNum");
			return fileRecordsService.hookFondsInfo(fondsNum,newFondsNum);
		}
		
		//挂接案卷业务的操作，（参数：fondsNum，原caseNum，newCaseNum）
		@RequestMapping(value="/fileRecordsHookCaseInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson hookCaseInfo(HttpServletRequest request){
			log.info("进入挂接案卷修改业务的ServiceImp层 有用参数fodnsNum："+request.getParameter("fondsNum")
					+"caseNum:"+request.getParameter("caseNum")+"newCaseNum:"
					+request.getParameter("newCaseNum"));
			String fondsNum=request.getParameter("fondsNum");
			String caseNum=request.getParameter("caseNum");
			String newCaseNum=request.getParameter("newCaseNum");
			return fileRecordsService.hookCaseInfo(fondsNum,caseNum,newCaseNum);
		}
		//模糊搜索归档（FileRecords）返回归档详细信息
		@RequestMapping(value="/adMhCxJn.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileRecords> mhCxJn(@RequestBody FileRecords fileRecords){
			log.info("进入模糊搜索归档，返回归档详细信息list集的控制器");
			return fileRecordsService.queryFileRecordsByThreeCondition(fileRecords);
		}
		//单个删除卷内
		@RequestMapping(value="/adScJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson scGd(@RequestBody FileRecords fileRecords){
			log.info("进入单个删除卷内控制器 有用参数fileNum："+fileRecords.getFileNum());
			return fileRecordsService.deleteFileRecords(fileRecords);
		}
		//批量删除卷内
		@RequestMapping(value="/adPlScJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson plScGd(HttpServletRequest request) throws IOException{
			log.info("进入批量删除卷内控制器    备注：后台反序列化解析，循环删除!");
			ServletInputStream inputStream = request.getInputStream();  
			String mybooksid = IOUtils.toString(inputStream);  
			JSONArray json=JSONArray.fromObject(mybooksid);
//		    JSONObject jsonOne;
			for(int i=0;i<json.size();i++){
//		   		 jsonOne = json.getJSONObject(i); 
			FileRecords fileRecords=new FileRecords();
			fileRecords.setFileNum((String)json.get(0));
		            fileRecordsService.deleteFileRecords(fileRecords);
			}
			aj.setMsg("删除完成");
			return aj;
		}
		
		//批量修改卷内
		@RequestMapping(value="/plXgJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson plXgJn(HttpServletRequest request){
			log.info("进入批量修改卷内控制器");
			return fileRecordsService.plXgJn(request);
		}
		
}