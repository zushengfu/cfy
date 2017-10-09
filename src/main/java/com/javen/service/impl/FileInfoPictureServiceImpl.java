package com.javen.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javen.dao.FileInfoPictureDao;
import com.javen.function.AjaxJson;
import com.javen.function.LuceneSearchFile;
import com.javen.model.FileInfoPicture;
import com.javen.service.FileInfoPictureService;

  
@Service("fileInfoPictureService")  
public class FileInfoPictureServiceImpl implements FileInfoPictureService {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired  
    private FileInfoPictureDao fileInfoPictureDao;
	AjaxJson aj=new AjaxJson();
	private LuceneSearchFile lsf=new LuceneSearchFile();
	
	@Override
	public AjaxJson addPicture(String fondsNum, String fileYear, String caseNum, String fileNum, String picName) {
		//卷内表图片挂接
		fileInfoPictureDao.addPicture(fondsNum,fileYear,caseNum,fileNum,picName);
		aj.setMsg("图片插入成功");
		return aj;
	}
	
	@Override
	public List<Map<String,String>> syQw(String condition) { 
		List<Map<String,String>> list=new ArrayList<>();
//		try {
			//搜寻结果被封装在了一个元素为Map<String,String> 的list集合中
//			list=lsf.getSearchResult(fileRecordsDao, fileRecordsPictureDao, request, conditions)
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
		return list;
	}

	@Override
	public int queryPicNameCount(String fondsNum, String fileYear, String caseNum) {
		return fileInfoPictureDao.queryPicNameCount(fondsNum,fileYear,caseNum);
	}

	@Override
	public FileInfoPicture getAllFileInfoPictureByFilePath(String filePath) {
		return fileInfoPictureDao.getAllFileInfoPictureByFilePath(filePath);
	}
	//归档图片删除
	@Override
	public AjaxJson dePicture(HttpServletRequest request,String picName) {
		log.info("进入归档图片删除ServiceImp层");
		String first1=picName.substring(0,picName.indexOf("-"));
		String first2=picName.substring(0,picName.indexOf("-",picName.indexOf("-")+1));
		String first3=picName.substring(0,picName.indexOf("-",picName.indexOf("-",picName.indexOf("-")+1)+1));
		String address="F:\\AcceptanceFile\\"+first1+"\\"+first2+"\\"+first3+"\\"+picName;
//		String address=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+first1+"\\"+first2+"\\"+first3+"\\"+picName;
		fileInfoPictureDao.dePicture(address);
		aj.setMsg("归档已删除");
		return aj;
	}
}  
