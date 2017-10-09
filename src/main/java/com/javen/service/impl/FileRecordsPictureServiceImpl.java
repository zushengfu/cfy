package com.javen.service.impl;
 

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.javen.controller.FondsInfoController;
import com.javen.dao.FileRecordsDao;
import com.javen.dao.FileRecordsPictureDao;
import com.javen.function.AjaxJson;
import com.javen.function.LuceneSearchFile;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;
import com.javen.service.FileRecordsPictureService;
  
@Service("fileRecordsPictureService")  
public  class FileRecordsPictureServiceImpl implements FileRecordsPictureService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FileRecordsPictureDao fileRecordsPictureDao;
	@Resource
	private FileRecordsDao fileRecordsDao;
	private  AjaxJson aj=new AjaxJson();
	//卷内图片挂接
	@Override
	public AjaxJson addPicture(String fondsNum, String catalogNum, String caseNum, String fileNum, String picName) {
		log.info("进入卷内图片挂接ServiceImp层");
		fileRecordsPictureDao.addPicture(fondsNum,catalogNum,caseNum,fileNum,picName);//插入对应的卷内的图片表数据
		aj.setMsg("图片插入成功");
		return aj;
	}
	
	//卷内多图片删除（可用于单图片）
	@Override
	public AjaxJson dePicture(HttpServletRequest request,String picName) {
		log.info("进入卷内图片挂接ServiceImp层");
//		String first1=picName.substring(0,picName.indexOf("-"));
//		String first2=picName.substring(0,picName.indexOf("-",picName.indexOf("-")+1));
//		String first3=picName.substring(0,picName.indexOf("-",picName.indexOf("-",picName.indexOf("-")+1)+1));
//		String address="F:\\AcceptanceFile\\"+first1+"\\"+first2+"\\"+first3+"\\"+picName;
//		String address=request.getSession().getServletContext().getRealPath("/")+"AcceptanceFile\\"+first1+"\\"+first2+"\\"+first3+"\\"+picName;
		fileRecordsPictureDao.dePicture(picName);//删除对应的卷内的图片表数据
		aj.setMsg("删除成功");
		return aj;
	}
		
	//修改图片（根据返回过来的原图片名，查出挂接的全部信息，再根据新图片名修改）
	@Override
	public AjaxJson updatePicture(HttpServletRequest request,String picName,String newPictureName) {
		log.info("进入卷内图片挂接ServiceImp层");
		FileRecordsPicture fileRecordsPicture;
		fileRecordsPicture=fileRecordsPictureDao.getAllFileRecordsPictureByFilePath(picName);
		fileRecordsPicture.setPicName(newPictureName);
		fileRecordsPictureDao.updateByPicName(fileRecordsPicture);
		aj.setMsg("删除成功");
		return aj;
	}
		
	//全文索引
	@Override
	public List<FileRecords> syQw(HttpServletRequest request,String condition){ 
		System.out.println("condition="+condition+"       fdsafdsdsfsdfsdfsdf");
		LuceneSearchFile lsf=new LuceneSearchFile();
		List<FileRecords> list=new ArrayList<>();
		try {
			list= lsf.getSearchResult(fileRecordsDao, fileRecordsPictureDao, request, condition);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int queryfileRecordsPictureCount(String fondsNum, String catalogNum, String caseNum) {
		return fileRecordsPictureDao.queryPicNmaeCount(fondsNum,catalogNum,caseNum);
	}
	@Override
	public FileRecordsPicture getAllFileRecordsPictureByFilePath(String filePath) {
		return fileRecordsPictureDao.getAllFileRecordsPictureByFilePath(filePath);
	}
	//查询图片地址
	@Override
	public List<Map<String,String>> getJnTpName(String fondsNum, String catalogNum, String fileNum) {
		List<String> list=new ArrayList<>();
		List<Map<String,String>> nList=new ArrayList<>();
		list=fileRecordsPictureDao.getJnTpAdd(fondsNum,catalogNum,fileNum);
		for(String picAdd:list){
			Map<String,String> map=new HashMap<>();
//			String picName=picAdd.substring(picAdd.lastIndexOf("/")+1);
//			System.out.println(picAdd+"dfsadfsadfjsalkdfjklsadjfsjaldkfsad");
			map.put("img",picAdd);
			nList.add(map);
		}
		return nList;
	}
	
	   
}  
