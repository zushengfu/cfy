package com.javen.controller;

import java.io.IOException;
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
import com.javen.model.CaseInfo;
import com.javen.service.CaseInfoService;

import net.sf.json.JSONArray;

@Controller  
@RequestMapping("/fond")  
public class CaseInfoController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		@Resource  
		private CaseInfoService caseInfoService;
		private AjaxJson aj=new AjaxJson();
		//创建案卷
		@RequestMapping(value="/adXzAj.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xzAj(@RequestBody CaseInfo caseInfo){
			log.info("进入创建案卷控制器");
			return caseInfoService.createCaseInfo(caseInfo);
			}
		//删除案卷
		@RequestMapping(value="/adScAj.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson scAj(@RequestBody CaseInfo caseInfo){
			log.info("进入删除案卷控制器");
			return caseInfoService.deleteCaseInfo(caseInfo);
		}
		
		//批量删除案卷
		@RequestMapping(value="/adPlScAj.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson plScAj(HttpServletRequest request){
			log.info("进入案卷批量删除案卷控制器");
			String mybooksid;
			try {
				ServletInputStream inputStream = request.getInputStream();
				mybooksid = IOUtils.toString(inputStream);
				JSONArray json=JSONArray.fromObject(mybooksid);
				for(int i=0;i<json.size();i++){
					log.info("反序列化转化成json后");
					caseInfoService.deleteCaseInfo((CaseInfo)json.get(i));
				}
			} catch (IOException e) {
				log.info("进入：批量删除，反序列化异常");
			} 
			aj.setMsg("批量删除已完成");
			return aj;
		}
				
		//修改案卷下的catalogNum
		@RequestMapping(value="/adXgAj.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson xgAj(@RequestBody CaseInfo caseInfo){
			log.info("进入修改案卷下的catalogNum控制器");
			return caseInfoService.updateCaseInfo(caseInfo);
		}
		//查询案卷下的catalogNum
		@RequestMapping(value="/adCxAj.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxAj(@RequestBody CaseInfo caseInfo){
			log.info("进入查询案卷下的catalogNum控制器");
			return caseInfoService.queryCaseInfo(caseInfo);
		}
		//根据fondsNum查询案卷下的catalogNum和caseYear
		@RequestMapping(value="/adCxAjForAjSearch.do",method=RequestMethod.POST)
		@ResponseBody
		public List<Map<String,String>> cxAjForSearch(@RequestBody CaseInfo caseInfo){
			log.info("进入根据fondsNum："+caseInfo.getFondsNum()+"查询案卷下的catalogNum和caseYear控制器");
			return caseInfoService.queryCaseInfoForAjSearch(caseInfo);
		}
		//根据参数fondsNum和catalogNum查询案下caseNum
		@RequestMapping(value="/adCxAjCaseNum.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxAjCaseNum(@RequestBody CaseInfo caseInfo){
			log.info("进入根据fonsNum和catalogNum");
			return caseInfoService.queryCaseInfoCaseNum(caseInfo);
		}
		//查村案卷下的CaseYear
		@RequestMapping(value="/adCxAjGdCaseYear.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxAjGdCaseYear(@RequestBody CaseInfo caseInfo){
			log.info("查询案卷下的CaseYear控制器");
			return caseInfoService.queryCaseInfoGdCaseYear(caseInfo);
		}
		//根据参数fondsNum和caseYear查询案卷下的caseNum
		@RequestMapping(value="/adCxAjGdCaseNum.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxAjCaseNumGdCaseNum(@RequestBody CaseInfo caseInfo){
			log.info("进入根据参数fonsNum："+caseInfo.getFondsNum()+" caseYear："
			+caseInfo.getCaseYear()+"控制器");
			return caseInfoService.queryCaseInfoGdCaseNum(caseInfo);
		}
		//无条件查询所有案卷信息
		@RequestMapping(value="/adCxAjAllInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public List<CaseInfo> cxAjAllInfo(){
			log.info("进入无条件查询所有案卷信息控制器");
			return caseInfoService.queryCaseInfoNoCondition();
		}
		//搜索业务（根据搜索条件查询符合业务）
		@RequestMapping(value="/adMhCxAj.do",method=RequestMethod.POST)
		@ResponseBody
		public List<CaseInfo> cxAjWithCondition(@RequestBody CaseInfo caseInfo){
			log.info("进入根据搜索条件查询所有caseInfo信息控制器");
			return caseInfoService.queryCaseInfoWithCondition(caseInfo);
		}
		//批量修改案卷信息
		@RequestMapping(value="/adPlXgAj.do",method=RequestMethod.POST)
		@ResponseBody
		public List<CaseInfo> plXgAj(HttpServletRequest request){
			log.info("进入批量修改案卷信息控制器");
//			caseInfoService.plXgAj(request);
			return null;
		}
}