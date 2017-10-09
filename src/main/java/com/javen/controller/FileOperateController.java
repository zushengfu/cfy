package com.javen.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.javen.function.AjaxJson;
import com.javen.function.DatabaseBackUp;
import com.javen.function.DatabaseRestore;
import com.javen.function.DateChange;
import com.javen.function.MakeDIR;
import com.javen.model.CaseInfo;
import com.javen.model.DateBR;
import com.javen.model.FileInfo;
import com.javen.model.FileRecords;
import com.javen.service.CaseInfoService;
import com.javen.service.DateBRService;

@Controller  
@RequestMapping("/file")  
public class FileOperateController {  
			private Logger log=LoggerFactory.getLogger(this.getClass());
			@Resource
			private DateBRService dateBRService;
			private DatabaseBackUp backUp =new DatabaseBackUp();
			private DatabaseRestore restore=new DatabaseRestore();
			private DateChange dc=new DateChange();
			private DateBR dbr=new DateBR();
			private AjaxJson aj=new  AjaxJson();
			
			
			//数据全部备份操作
			@RequestMapping(value="/dataBackUp.do",method=RequestMethod.POST)
			@ResponseBody
			public AjaxJson dataBackUp(){
				log.info("进入数据备份操作控制器");
				//拿到操作时间
				Date date=new Date();
				//根据时间对文件存储位置，存储名，进行文件存储
				backUp.backup(date);
				String name=dc.DateToStr(date)+".sql";
				String address="d:\\backupDatabase\\"+name;
				dbr.setFileName(name);
				dbr.setFileAddress(address);
				dateBRService.dateBackUp(dbr);
				aj.setMsg("全部数据备份成功！！！");
				return aj;
			}
			/**
			 * @param request
			 * @return
			 */
			//数据时间条件备份操作
			@RequestMapping(value="/dataBackUpCondition.do",method=RequestMethod.POST)
			@ResponseBody
			public AjaxJson dataBackUpCondition(HttpServletRequest request){
				log.info("进入数据时间条件备份操作控制器");
				String condition=request.getParameter("biaoming");
				String timeStart=request.getParameter("timeStart");
				String timeOver=request.getParameter("timeOver");
				//拿到操作时间
				Date date=new Date();
				//根据时间对文件存储位置，存储名，进行文件存储
				backUp.conditionBackup(date,condition,timeStart,timeOver);
				String name=timeStart+"_"+timeOver+".sql";
				String address="d:\\backupDatabase\\"+name;
				dbr.setFileName(name);
				dbr.setFileAddress(address);
				dateBRService.dateBackUp(dbr);
				aj.setMsg("数据条件备份成功！！！");
				return aj;
			}
			//还原数据库
			@RequestMapping(value="/restoreDB.do",method=RequestMethod.POST)
			@ResponseBody
			public AjaxJson RestoreDB(HttpServletRequest request){
				log.info("进入还原数据库控制器");
				String databaseName=request.getParameter("databaseName");
				System.out.println(databaseName);
				restore.restore(databaseName);
				aj.setMsg("还原成功");
				return aj;
			}
			//作为还原展示时，查询的所有已备份资料
			@RequestMapping(value="/showRestoreDB.do",method=RequestMethod.POST)
			@ResponseBody
			public List<DateBR> showRestoreDB(){
				log.info("进入查询所有备份信息控制器");
				return dateBRService.showRestoreDB();
			}
			/**
			 * @param fondsNum   全宗号（可多文件下载全宗下的所有文件）
			 * @param fileYear   归档的年份
			 * @param orgType    归档的类型
			 * @param picName    要下载的图片名字
			 * 
			 * @param request
			 * @param response
			 * @throws Exception
			 */
			//单个归档文件下载
		 	@RequestMapping(value="/fileInfoDownload.do",method=RequestMethod.GET)  
		    public void fileInfoDown(HttpServletRequest request,HttpServletResponse response) throws Exception{  
		        log.info("进入归档单个文件下载控制器");
		 		//模拟文件，myfile.txt为需要下载的文件  
		 		//String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
		        String fondsNum=request.getParameter("fondsNum");
		        String fileYear=request.getParameter("fileYear");
		        String orgType=request.getParameter("orgType");
		        String picName=request.getParameter("picName");
		        log.info("fondsNum="+fondsNum+",fileYear="+fileYear+",orgType="+orgType+",picName="+picName);
//		        String path="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType+"/"+picName;
		        String path=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType+"/"+picName;
		        //获取输入流  
		        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));  
		        //假如以中文名下载的话  
		        String filename = "下载文件.png";  
		        //转码，免得文件名中文乱码  
		        filename = URLEncoder.encode(filename,"UTF-8");  
		        //设置文件下载头  
		        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
		        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
		        response.setContentType("multipart/form-data");   
		        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
		        int len = 0;  
		        while((len = bis.read()) != -1){  
		            out.write(len);  
		            out.flush(); 
		        }  
		        out.close();  
		    }   
		 	/**
		 	 * @param fondsNum  全宗（多文件下载，单文件下载时做文件名）
		 	 * @param  catalogNum  目录号（多文件下载，单文件下载是做文件名）
		 	 * @param  caseNum    案卷号（多文件下载，单文件下载时做文件名）
		 	 * @param  picName   文件名（单文件下载的文件名）
		 	 * 
		 	 * @param request
		 	 * @param response
		 	 * @throws Exception
		 	 */
		 	//单个卷内文件下载
		 	@RequestMapping(value="/fileRecordsDownload.do",method=RequestMethod.GET)  
		    public void fileRecordsDown(HttpServletRequest request,HttpServletResponse response) throws Exception{  
		        log.info("进入卷内单个文件下载控制器");
		 		//模拟文件，myfile.txt为需要下载的文件  
		 		//String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
		        String fondsNum=request.getParameter("fondsNum");
		        String catalogNum=request.getParameter("catalogNum");
		        String caseNum=request.getParameter("caseNum");
		        String picName=request.getParameter("picName");
		        log.info("fondsNum="+fondsNum+",catalogNum="+catalogNum+",caseNum="+caseNum+",picName="+picName);
//		        String path="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum+"/"+picName;
		        String path=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum+"/"+picName;
//		        if(fondsNum !=null && catalogNum==null && caseNum==null && picName==null)
		        //获取输入流  
		        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));  
		        //假如以中文名下载的话  
		        String filename = "下载文件.png";  
		        //转码，免得文件名中文乱码  
		        filename = URLEncoder.encode(filename,"UTF-8");  
		        //设置文件下载头  
		        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
		        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
		        response.setContentType("multipart/form-data");   
		        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
		        int len = 0;  
		        while((len = bis.read()) != -1){  
		            out.write(len);  
		            out.flush(); 
		        }  
		        out.close();  
		    }   
		 
		 	//卷内多文件打包zip下载
		 	@RequestMapping(value = "/fileRecordsDownloadZip.do",method=RequestMethod.GET)
		    public String fileRecordsDownloadFiles(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		 		log.info("进入卷内多文件打包zip下载控制器");
		        List<File> files = new ArrayList<File>();
		        //文件下载的地址
		        String fondsNum=request.getParameter("fondsNum");
		        String catalogNum=request.getParameter("catalogNum");
		        String caseNum=request.getParameter("caseNum");
		        String fileNum=request.getParameter("fileNum");
//		        String fileAll="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum;
		        String fileAll=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+catalogNum+"\\"+fondsNum+"-"+catalogNum+"-"+caseNum;
		        File Allfile = new File(fileAll);
		        if (Allfile.exists()) {
		            File[] fileArr = Allfile.listFiles();
		            for (File file2 : fileArr) {
		                files.add(file2);
		            }
		        }
		        String fileName = UUID.randomUUID().toString() + ".zip";
		        // 在服务器端创建打包下载的临时文件
		        String outFilePath = request.getSession().getServletContext().getRealPath("/") + "zip/";

		        File fileZip = new File(outFilePath + fileName);
		        // 文件输出流
		        FileOutputStream outStream = new FileOutputStream(fileZip);
		        // 压缩流
		        ZipOutputStream toClient = new ZipOutputStream(outStream);
//		          toClient.setEncoding("gbk");
		        zipFile(files, toClient);
		        toClient.close();
		        outStream.close();
		        this.downloadFile(fileZip, response, true);
		        return null;
		 		}
		 	
		 	//归档多文件打包zip下载
		 	@RequestMapping(value = "/fileInfoDownloadZip.do",method=RequestMethod.POST)
		    public String fileInfoDownloadFiles(@RequestBody FileInfo fileInfo,HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		 		log.info("进入归档多文件打包zip下载控制器");
		        List<File> files = new ArrayList<File>();
		        //文件下载的地址
		        String fondsNum=request.getParameter("fondsNum");
		        String fileYear=request.getParameter("fileYear");
		        String orgType=request.getParameter("orgType");
		        String fileNum=request.getParameter("fileNum");
//		        String fileAll="F:\\AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType;
		        String fileAll=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+fondsNum+"\\"+fondsNum+"-"+fileYear+"\\"+fondsNum+"-"+fileYear+"-"+orgType;
		        File Allfile = new File(fileAll);
		        if (Allfile.exists()) {
		            File[] fileArr = Allfile.listFiles();
		            for (File file2 : fileArr) {
		                files.add(file2);
		            }
		        }
		        String fileName = UUID.randomUUID().toString() + ".zip";
		        // 在服务器端创建打包下载的临时文件
		        String outFilePath = request.getSession().getServletContext().getRealPath("/") + "zip/";
		        File fileZip = new File(outFilePath + fileName);
		        // 文件输出流
		        FileOutputStream outStream = new FileOutputStream(fileZip);
		        // 压缩流
		        ZipOutputStream toClient = new ZipOutputStream(outStream);
		        //  toClient.setEncoding("gbk");
		        zipFile(files, toClient);
		        toClient.close();
		        outStream.close();
		        this.downloadFile(fileZip, response, true);
		        return null;
		 		}
		 	
		 		public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
			        try {
			            int size = files.size();
			            // 压缩列表中的文件
			            for (int i = 0; i < size; i++) {
			                File file = (File) files.get(i);
			                zipFile1(file, outputStream);
			            }
			        } catch (IOException e) {
			            throw e;
			        }
		 		}
				public static void zipFile1(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
					try {
			            if (inputFile.exists()) {
			                if (inputFile.isFile()) {
			                    FileInputStream inStream = new FileInputStream(inputFile);
			                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
			                    ZipEntry entry = new ZipEntry(inputFile.getName());
			                    outputstream.putNextEntry(entry);
	
			                    final int MAX_BYTE = 10 * 1024 * 1024 ; // 最大的流为10M
			                    long streamTotal = 0; // 接受流的容量
			                    int streamNum = 0; // 流需要分开的数量
			                    int leaveByte = 0; // 文件剩下的字符数
			                    byte[] inOutbyte; // byte数组接受文件的数据
	
			                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
			                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
			                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
	
			                    if (streamNum > 0) {
			                        for (int j = 0; j < streamNum; ++j) {
			                            inOutbyte = new byte[MAX_BYTE];
			                            // 读入流,保存在byte数组
			                            bInStream.read(inOutbyte, 0, MAX_BYTE);
			                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
			                        }
			                    }
			                    // 写出剩下的流数据
			                    inOutbyte = new byte[leaveByte];
			                    bInStream.read(inOutbyte, 0, leaveByte);
			                    outputstream.write(inOutbyte);
			                    outputstream.closeEntry(); // Closes the current ZIP entry
			                    // and positions the stream for
			                    // writing the next entry
			                    bInStream.close(); // 关闭
			                    inStream.close();
			                }
			            } else {
			                throw new ServletException("文件不存在！");
			            }
			        } catch (IOException e) {
			            throw e;
			        }
			        
			    }
			 public void downloadFile(File file,HttpServletResponse response,boolean isDelete) {
					try {
			        // 以流的形式下载文件。
			        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			        byte[] buffer = new byte[fis.available()];
			        fis.read(buffer);
			        fis.close();
			        // 清空response
			        response.reset();
			        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			        response.setContentType("application/octet-stream");
			        response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
			        toClient.write(buffer);
			        System.out.println("kao bi");
			        toClient.flush();
			        toClient.close();
			        if(isDelete)
			        {
			            file.delete();  //是否将生成的服务器端文件删除
			        }
					} 
					catch (IOException ex) {
						ex.printStackTrace();
					}
	
				}
}