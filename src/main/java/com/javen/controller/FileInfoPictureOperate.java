 package com.javen.controller;


import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.javen.function.AjaxJson;
import com.javen.function.PictureFileUpload;
import com.javen.model.FileInfoPicture;
import com.javen.model.FileRecordsPicture;
import com.javen.service.FileInfoPictureService;

//归档文件上传
@Controller  
@RequestMapping("/gdfile")  
public class FileInfoPictureOperate {
	 private Logger log=LoggerFactory.getLogger(this.getClass());
	 @Resource  
	 private FileInfoPictureService fileInfoPictureService;
	 private PictureFileUpload fileUpload=new PictureFileUpload();
	 private AjaxJson aj=new AjaxJson();
	 private FileInfoPicture fileInfoPicture;
	
	//归档文件上传
	@RequestMapping(value="/adXzGdTp.do",method=RequestMethod.POST)  
	@ResponseBody
	public AjaxJson gdThreeFileUpload(@RequestParam("file")CommonsMultipartFile files[]
			,HttpServletRequest request, ModelMap model,FileInfoPicture fileInfoPicture) { 
		log.info("进入归档文件上传控制器");
		return fileUpload.fileInfoPicturefileUpload(fileInfoPictureService,request, files);
	}
	
	//归档图片删除
	@RequestMapping(value="/dePicture.do",method=RequestMethod.POST)  
	@ResponseBody
	public AjaxJson gdThreeFileUpload(HttpServletRequest request) { 
		log.info("进入归档文件上传控制器");
		return fileInfoPictureService.dePicture(request,request.getParameter("picName"));
	}
	
		//全文索引
		@RequestMapping(value="/adSyQw.do",method=RequestMethod.POST)
		@ResponseBody
		public AjaxJson syQw(HttpServletRequest request){
			log.info("进入归档全文索引控制器");
			List<Map<String,String>> list0=new ArrayList<Map<String,String>>();
			List<Map<String,FileInfoPicture>> list3=new ArrayList<Map<String,FileInfoPicture>>();
			list0=fileInfoPictureService.syQw(request.getParameter("condition"));//拿到存了路径，文件名，内容的map
			for(Map<String,String> map:list0){
				Map<String,FileInfoPicture> map1=new HashMap<>();
				String filePath=map.get("filePath");//拿到文件的存储路径
				fileInfoPicture=fileInfoPictureService.getAllFileInfoPictureByFilePath(map.get("filePath"));//拿到文件存储的业务
				if(null!=fileInfoPicture){
					map.put("filePath", filePath);
					map.put("fondsNum",fileInfoPicture.getFondsNum());
					map.put("fileYear", fileInfoPicture.getFileYear());
					map.put("caseNum",fileInfoPicture.getSafekeepingTerm());
					map.put("fileNum", fileInfoPicture.getFileNum());
					map1.put("fileInfoPicture",fileInfoPicture);
					list3.add(map1);
				}
				else
					aj.setMsg("没有符合条件");
			}
			aj.setListFileInfo(list3);
			return aj;
		}
		//根据前端发挥的picName返回前端图片
		 @RequestMapping(value="/adCxGdTpAdd.do",method=RequestMethod.GET)
		 public void viewAndPicture(HttpServletRequest request,HttpServletResponse response, @RequestParam("picName")String picn)throws Exception{
			   	 log.info("进入返回图片控制器   有用参数picName："+picn);
			 	 FileInputStream hFile = new FileInputStream(request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\" + picn.substring(0,picn.indexOf("-"))+"\\"+picn.substring(0,picn.indexOf("-",picn.indexOf("-")+1))+"\\"+picn.substring(0,picn.indexOf("-",picn.indexOf("-",picn.indexOf("-")+1)+1))+"/"+picn); // 以byte流的方式打开文件  
			     int i = hFile.available(); // 得到文件大小  
			     byte data[] = new byte[i];  
			     hFile.read(data); // 读数据  
			     hFile.close();  
			     response.setContentType("image/*"); // 设置返回的文件类型  
			     OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
			     toClient.write(data); // 输出数据  
			     toClient.close();  
		 }
		
	//单个文件下载
	 @RequestMapping(value="/download.do",method=RequestMethod.GET)  
	    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	        
	    }   
	 	 
	 
}


