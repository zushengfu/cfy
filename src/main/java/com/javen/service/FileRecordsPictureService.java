package com.javen.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.http.HttpRequest;

import com.javen.function.AjaxJson;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;

public interface FileRecordsPictureService {  
	public AjaxJson addPicture(String fondsNum,String catalogNum,String caseNum,String fileNum,String picName);
	public List<FileRecords> syQw(HttpServletRequest request,String condition);
	public int queryfileRecordsPictureCount(String fondsNum,String catalogNum,String caseNum);
	public FileRecordsPicture getAllFileRecordsPictureByFilePath(String filePath);
	//获取卷内某文件下面挂接的图片的名字
	public List<Map<String,String>> getJnTpName(String fondsNum,String catalogNum,String fileNum);
	public AjaxJson dePicture(HttpServletRequest request,String picName);
	public AjaxJson updatePicture(HttpServletRequest request,String picName,String newPictureName);
}  