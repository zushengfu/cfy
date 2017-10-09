package com.javen.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.javen.model.FileRecordsPicture;
import com.javen.service.FileInfoPictureService;
import com.javen.service.FileRecordsPictureService;

public class PictureFileUpload {
	private AjaxJson aj=new AjaxJson();
	//归档文件上传
	public AjaxJson fileInfoPicturefileUpload(FileInfoPictureService fileInfoPictureService,HttpServletRequest request,CommonsMultipartFile files[]){
	List<String> list = new ArrayList<String>();  
	String fondsNum=request.getParameter("fondsNum");
	String fileYear=request.getParameter("fileYear");
	String caseNum=request.getParameter("caseNum");
	String safekeepingTerm=request.getParameter("safekeepingTerm");
	String fileNum=request.getParameter("fileNum");
	String orgType=request.getParameter("orgType");
    // 获得项目的路径  
//  ServletContext sc = request.getSession().getServletContext();  
    // 上传位置  
//  String path = sc.getRealPath("/filePicture") + "/"; // 设定文件保存的目录  
//    String path="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType+"/";
    String path=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType+"/";
    File f = new File(path); 
    if (!f.exists()) 
        f.mkdirs(); 
    for (int i = 0; i < files.length; i++) {
        // 获得文件名     全宗-年度-保管期限-机构类别-件号-顺序号
        String fileName =fondsNum+"-"+fileYear+"-"+safekeepingTerm+"-"+orgType+"-"+fileNum+"-"+"000"+(fileInfoPictureService.queryPicNameCount(fondsNum, fileYear, caseNum)+1)+files[i].getOriginalFilename().substring(files[i].getOriginalFilename().indexOf("."));
        File fileExist=new File(path+fileName);
        while(true){
    	if(fileExist.exists()){
    		int a=0;
    		a++;
    		fileName=fondsNum+"-"+fileYear+"-"+safekeepingTerm+"-"+orgType+"-"+fileNum+"-"+"000"+(fileInfoPictureService.queryPicNameCount(fondsNum, fileYear, caseNum)+1+a)+files[i].getOriginalFilename().substring(files[i].getOriginalFilename().indexOf("."));
    		}
    	else{
    		break;
    	}
        }
        String newFileName=fileName;
        if(!fileExist.exists()){
	        if (!files[i].isEmpty()) {  
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newFileName);  
	                InputStream in = files[i].getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
		        System.out.println("上传图片到:" + path + newFileName); 
//		        String picName=path+newFileName;    (此处是开发中测试)
		        String picName=newFileName;
		        fileInfoPictureService.addPicture(fondsNum,fileYear,caseNum,fileNum,picName);
		        list.add(path + newFileName);
	        }
        }
        else{
        	if (!files[i].isEmpty()) {  
        	String count="("+ i +")";
        	String[] fileCount=fileName.split("\\.");
        	newFileName=fileCount[0]+count+"."+fileCount[1];
        	try {  
                FileOutputStream fos = new FileOutputStream(path + newFileName);  
                InputStream in = files[i].getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
	        System.out.println("上传图片到:" + path + newFileName);  
//	        String picName=path+newFileName;
	        String picName=newFileName;
	        fileInfoPictureService.addPicture(fondsNum,fileYear,caseNum,fileNum,picName);
	        list.add(path + newFileName);
        	 }
        }
    }  
    aj.setMsg("图片已上传！");
    return aj;
	}
	
	
	//卷内文件上传
	public AjaxJson fileRecordsPicturefileUpload(FileRecordsPictureService fileRecordsPictureService,HttpServletRequest request,CommonsMultipartFile files[]){
		List<String> list = new ArrayList<String>();  
		String fondsNum=request.getParameter("fondsNum");
		String catalogNum=request.getParameter("catalogNum");
		String caseNum=request.getParameter("caseNum");
		String fileNum=request.getParameter("fileNum");
		String fileYear=request.getParameter("fileYear");
	    // 获得项目的路径  
//	    ServletContext sc = request.getSession().getServletContext();  
//		String path="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum+"/";
		String path=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum+"/";
		System.out.println(path);
		// 上传位置  
//	    String path = sc.getRealPath("/filePicture") + "/"; // 设定文件保存的目录  
	    File f = new File(path);  
	    if (!f.exists())  
	        f.mkdirs();  
	    for (int i = 0; i < files.length; i++) {  
	        // 全宗号-目录号-案卷号-文件号
	        String fileName =fondsNum+"-"+catalogNum+"-"+caseNum+"-"+fileNum+"-"+"000"+(fileRecordsPictureService.queryfileRecordsPictureCount(fondsNum, catalogNum, caseNum)+1)+ files[i].getOriginalFilename().substring(files[i].getOriginalFilename().indexOf("."));
	       System.out.println(fileRecordsPictureService.queryfileRecordsPictureCount(fondsNum, catalogNum, caseNum));
	        File fileExist=new File(path+fileName);
	    	String newFileName=fileName;
	        if(!fileExist.exists()){
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(path + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                while ((b = in.read()) != -1) {  
		                    fos.write(b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
			        System.out.println("上传图片到:" + path + newFileName); 
//			        String picName=path+newFileName;
			        String picName=newFileName;
			        fileRecordsPictureService.addPicture(fondsNum,catalogNum,caseNum,fileNum,picName);
			        list.add(path + newFileName);
		        }
	        }
	        else{
	        	if (!files[i].isEmpty()) {  
	        	String count="("+ i +")";
	        	String[] fileCount=fileName.split("\\.");
	        	newFileName=fileCount[0]+count+"."+fileCount[1];
	        	try {  
	                FileOutputStream fos = new FileOutputStream(path + newFileName);  
	                InputStream in = files[i].getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
		        System.out.println("上传图片到:" + path + newFileName);  
		        String picName=path+newFileName;
		        fileRecordsPictureService.addPicture(fondsNum,catalogNum,caseNum,fileNum,picName);
		        list.add(path + newFileName);
	        	 }
	        }
	    }  
	    aj.setMsg("图片已上传！");
	    return aj;
		}
	
	public static void isExist(String path) {
		  File file = new File(path);
		  //判断文件夹是否存在,如果不存在则创建文件夹
		  if (!file.exists()) {
		   file.mkdir();
		  }
		 }
}
