package com.javen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.javen.model.FileRecordsSend;
import com.javen.service.FileRecordsSendService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller  
@RequestMapping("/fond")  
public class FileRecordsSendController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		
		@Resource  
		private FileRecordsSendService fileRecordsSendService;
		private AjaxJson aj=new AjaxJson();
		
		//修改移交卷内表。
		@RequestMapping(value="/adYjXgJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adYjXgJn(@RequestBody FileRecordsSend fileRecordsSend){
			log.info("进入修改移交卷内控制器");
			return fileRecordsSendService.updateFileRecordsSend(fileRecordsSend);
		}
		//移交  fileRecords。
		@RequestMapping(value="/adYjJn.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adYjJn(HttpServletRequest request) throws IOException{
			log.info("进入移交卷内控制器");
			ServletInputStream inputStream = request.getInputStream();  
            String mybooksid = IOUtils.toString(inputStream);  
            JSONArray json=JSONArray.fromObject(mybooksid);
            JSONObject jsonOne;
            for(int i=0;i<json.size();i++){
            	FileRecordsSend fileRecordsSend=new FileRecordsSend();
                jsonOne = json.getJSONObject(i); 
                fileRecordsSend.setFondsNum((String)jsonOne.get("fondsNum"));
                fileRecordsSend.setCatalogNum((String)jsonOne.get("catalogNum"));
                fileRecordsSend.setCaseNum((String)jsonOne.get("caseNum"));
                fileRecordsSend.setFileNum((String)jsonOne.get("fileNum"));
                fileRecordsSend.setSubjectName((String)jsonOne.get("subjectName"));
                fileRecordsSend.setSecurityRank((String)jsonOne.get("securityRank"));
                fileRecordsSend.setFileDescribeNum((String)jsonOne.get("fileDescribeNum"));
                fileRecordsSend.setOrgType((String)jsonOne.get("orgType"));
                fileRecordsSend.setResponsibler((String)jsonOne.get("responsibler"));
                fileRecordsSend.setTime((String)jsonOne.get("time"));
                fileRecordsSend.setBeiyong((String)jsonOne.get("beiyong"));
                if(null==jsonOne.get("filePath")){
                	fileRecordsSend.setFilePath(null);
                }
                fileRecordsSend.setRemarks((String)jsonOne.get("remarks"));
                fileRecordsSend.setSafekeepingTerm((String)jsonOne.get("safekeepinTerm"));
                if(null==jsonOne.get("pageNum")){
                	fileRecordsSend.setPageNum(null);
                }
                fileRecordsSend.setFileYear((String)jsonOne.get("fileYear"));
                fileRecordsSend.setSender((String)jsonOne.get("sender"));
                fileRecordsSend.setAccepter((String)jsonOne.get("accepter"));
                fileRecordsSend.setDataState(1);
                fileRecordsSend.setOprateState(1);
                System.out.println(jsonOne.get("fondsNum"));
                fileRecordsSendService.sendFileRecords(fileRecordsSend);
             }
            aj.setMsg("操作成功");
            return aj;
		}
		//无条件查询移交卷内表
		@RequestMapping(value="/adCxYjJn.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileRecordsSend> adCxYjJn() {
			log.info("进入无条件查询移交卷内控制器");
			return fileRecordsSendService.cxYjJn();
		}
		//查询新增信息条数(未查看信息条数)
		@RequestMapping(value="/adCxYjJnNewCount.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adCxYjJnNewCount(HttpServletRequest request) {
			 log.info("进入查询卷内新增信息条数控制器");
			 int oldCount=(int)request.getSession().getAttribute("newCount");
			 int newCount=fileRecordsSendService.cxYjJnNewCount();
			 int newMsg=newCount-oldCount;
			 request.getSession().setAttribute("newCount", newCount);
			 System.out.println(newMsg);
			 System.out.println(request.getSession().getAttribute("newCount"));
			 aj.setMsg("最新值放在session");
			 aj.setNewCount(newMsg);
			 return aj;
		}
		//无条件查询移交卷内表,如果beiyong=已通过|beiyong=不合格，不显示
		@RequestMapping(value="/adCxNoOperateYjJn.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileRecordsSend> adCxNoOperateYjJn() {
			log.info("进入无条件查询已移交卷内数据未被查看控制器");
			return fileRecordsSendService.cxNoOperateYjJn();
		}
		//无条件查询自己移交卷内表数据
		@RequestMapping(value="/adCxSelfYjJn.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FileRecordsSend> adCxSelfYjJn(HttpServletRequest request) {
			log.info("进入查询用户本人已移交卷内数据控制器");
			return fileRecordsSendService.cxSelfYjJn(request);
		}
}