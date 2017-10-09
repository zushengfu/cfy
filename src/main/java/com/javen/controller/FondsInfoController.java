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
import com.javen.model.FileRecords;
import com.javen.model.FondsInfo;
import com.javen.service.CaseInfoService;
import com.javen.service.FileInfoService;
import com.javen.service.FileRecordsService;
import com.javen.service.FondsInfoService;

import net.sf.json.JSONArray;

@Controller  
@RequestMapping("/fond")  
public class FondsInfoController {  
		private Logger log=LoggerFactory.getLogger(this.getClass());
		//批量修改：UPDATE 数据表名 SET 字段名 = replace(字段名， '要替换的字符串', '替换为') WHERE 设定条件;
		@Resource  
		private FondsInfoService fondsInfoService;
		private AjaxJson aj=new AjaxJson();
		
		//创建全宗
		@RequestMapping(value="/adXzQz.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson sc(@RequestBody FondsInfo fondsInfo){
			log.info("进入全宗创建控制器");
			return fondsInfoService.createFondsInfo(fondsInfo);
		}
		
		//删除全宗
		@RequestMapping(value="/adScQz.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson plScQz(@RequestBody FondsInfo fondsInfo){
			log.info("进入单个删除全宗控制器");
			return fondsInfoService.deleteFondsInfo(fondsInfo.getFondsNum());
		}
		
		//批量删除全宗
		@RequestMapping(value="/adPlScQz.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson scQz(HttpServletRequest request){
			log.info("进入批量删除全宗控制器");
			String mybooksid;
			try {
				ServletInputStream inputStream = request.getInputStream();
				mybooksid = IOUtils.toString(inputStream);
				JSONArray json=JSONArray.fromObject(mybooksid);
				for(int i=0;i<json.size();i++){
					log.info("反序列化转化成json后，fondsNum的值为："+(String)json.get(i));
					fondsInfoService.deleteFondsInfo((String)json.get(i));
				}
			} catch (IOException e) {
				log.info("进入：批量删除，反序列化异常");
			}  
//			fondsNum=request.getParameter("fondsNum");
//			log.info(fondsNum);
//			fondsNum=fondsNum.replace("[", "");
//			fondsNum=fondsNum.replaceAll("]", "");
//			String[] arrFiledata=fondsNum.split(",");
//			for(int i = 0;i<arrFiledata.length;i++){
//				fondsInfoService.deleteFondsInfo(arrFiledata[i]);
//			}
			aj.setMsg("全宗已删除");	
			return aj;
		}
		
		//模糊查询全宗
		@RequestMapping(value="/adMhCxQz.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FondsInfo> mhCxQz(HttpServletRequest request,@RequestBody FondsInfo fondsInfo){
			log.info("根据fondName:"+fondsInfo.getFondsName()+" fondsNum"+fondsInfo.getFondsNum()
			+"进入模糊查询全宗控制器");
			return fondsInfoService.queryFondsInfoByFondsNameAndFondsNum(fondsInfo);
		}
		
		//无条件查询全宗，返回所有全宗号
		@RequestMapping(value="/adCxQz.do",method=RequestMethod.POST)
		@ResponseBody
		public List<String> cxQz(){
			log.info("进入无条件查询全宗信息，返回所有全宗号的list集的控制器");
//			log.info(request.getSession().getServletContext().getRealPath("upload"));
			return fondsInfoService.queryFondsInfo();
		}
		
		//无条件查询全宗，返回全宗详细信息
		@RequestMapping(value="/adCxQzAllInfo.do",method=RequestMethod.POST)
		@ResponseBody
		public List<FondsInfo> cxQzAllInfo(HttpServletRequest request){
			log.info("进入无条件查询，返回所有全宗详细信息的list集的控制器");
			System.out.println(request.getSession().getServletContext().getRealPath("upload")+"     "+"nihaooaoosadfisdiufsdoaf");
			return fondsInfoService.getFondsInfoForAll();
		}
		
		//根据前台传过来的已经修改后的FondsInfo修改全宗
		@RequestMapping(value="/adXgQz.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson XgQz(@RequestBody FondsInfo fondsInfo){
			log.info("进入修改全宗控制器");
			
			return fondsInfoService.updateFondsInfo(fondsInfo);
		}
		//批量修改全宗内容
		@RequestMapping(value="/adPlXgQz.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson adPlXgQz(HttpServletRequest request){
			log.info("进入批量修改控制层");
			return  fondsInfoService.updateMoreFondsInfo(request);
		}
}