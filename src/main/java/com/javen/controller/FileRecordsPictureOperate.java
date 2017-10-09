 package com.javen.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.javen.function.AjaxJson;
import com.javen.function.PictureFileUpload;
import com.javen.model.FileInfo;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;
import com.javen.service.FileRecordsPictureService;
import com.javen.service.FileRecordsService;

@Controller  
@RequestMapping("/file")  
public class FileRecordsPictureOperate {
	 private Logger log=LoggerFactory.getLogger(this.getClass());
	 @Resource  
	 private FileRecordsPictureService fileRecordsPictureService;
	 private FileRecordsService fileRecordsService;
	 private PictureFileUpload fileUpload=new PictureFileUpload();
	 private AjaxJson aj=new AjaxJson();
	@RequestMapping(value="/adXzJnTp.do",method=RequestMethod.POST)  
	@ResponseBody
	//卷内文件上传
	public AjaxJson threeFileUpload(@RequestParam("file")CommonsMultipartFile files[]
			,HttpServletRequest request, ModelMap model,FileRecordsPicture fileRecordsPicture) {  
		log.info("进入卷内文件上传控制器  有用参数："+fileRecordsPicture.getCatalogNum());
		return fileUpload.fileRecordsPicturefileUpload(fileRecordsPictureService,request, files);
	}
	//删除卷内图片
	public AjaxJson deJnPicture(HttpServletRequest request) {  
		log.info("进入删除卷内图片控制器");
		return fileRecordsPictureService.dePicture(request,request.getParameter("picName"));
	}
	//全文索引
	@RequestMapping(value="/adSyQw.do",method=RequestMethod.GET)
	@ResponseBody
	public List<FileRecords> syQw(HttpServletRequest request)throws IOException, ParseException{
		log.info("进入归档全文索引控制器    有用参数condition："+request.getParameter("condition"));
		return fileRecordsPictureService.syQw(request,request.getParameter("condition"));
	}
	//查询卷内图片操作，将图片名字以及图片地址以List<Map<String,String>>的形式发给前端
	@RequestMapping(value="/adCxJnTpName.do",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> adCxJnTpName(@RequestBody FileRecordsPicture fileRecordsPicture){
			log.info("进入查询查询卷内图片控制器    有用参数fondsNum："+fileRecordsPicture.getFondsNum()
					+"catalogNum："+fileRecordsPicture.getCatalogNum()+"fileNum："
					+fileRecordsPicture.getFileNum());
			return fileRecordsPictureService.getJnTpName(fileRecordsPicture.getFondsNum(),
					fileRecordsPicture.getCatalogNum(),fileRecordsPicture.getFileNum());
	}
	//模糊搜索卷内（FileRecords）返回归档详细信息
	@RequestMapping(value="/adMhCxJn.do",method=RequestMethod.POST)
	@ResponseBody
	public List<FileRecords> mhCxGd(@RequestBody FileRecords fileRecords){
		log.info("进入模糊搜索卷内，返回归档详细信息控制器 ");
		return fileRecordsService.queryFileRecordsByThreeCondition(fileRecords);
	}
	 //根据前端发挥的picName返回前端图片
	 @RequestMapping(value="/adCxJnTpAdd.do",method=RequestMethod.GET)
	 public void viewAndPicture(HttpServletRequest request,HttpServletResponse response, @RequestParam("picName")String picn)throws Exception{
		   	 log.info("进入返回图片控制器   有用参数picName："+picn);
		 	 FileInputStream hFile = new FileInputStream(request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\" + picn.substring(0,picn.indexOf("-"))
		 	 						+"\\"+picn.substring(0,picn.indexOf("-",picn.indexOf("-")+1))+"\\"+picn.substring(0,picn.indexOf("-",picn.indexOf("-",picn.indexOf("-")+1)+1))+"/"+picn); // 以byte流的方式打开文件  
//		     FileInputStream hFile=new FileInputStream(request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+request.getParameter("fondsNum")+"\\"+request.getParameter("fondsNum")
//		     						+"-"+request.getParameter("fileYear")+"\\"+request.getParameter("fondsNum")+"-"+request.getParameter("fileYear")+"-"+request.getParameter("orgType")+"/"+picn);
		 	 int i = hFile.available(); // 得到文件大小  
		     byte data[] = new byte[i];  
		     hFile.read(data); // 读数据  
		     hFile.close();  
		     response.setContentType("image/*"); // 设置返回的文件类型  
		     OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
		     toClient.write(data); // 输出数据  
		     toClient.close();  
	 }
	 
}


