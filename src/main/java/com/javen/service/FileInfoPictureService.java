package com.javen.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.FileInfoPicture;

public interface FileInfoPictureService {  
	public AjaxJson addPicture(String fondsNum,String fileYear,String caseNum,String fileNum,String picName);
	public List<Map<String, String>> syQw(String condition);
	public int queryPicNameCount(String fondsNum,String fileYear,String caseNum);
	public FileInfoPicture getAllFileInfoPictureByFilePath(String filePath);//模糊查询给出的filePath下的所有信息
	public AjaxJson dePicture(HttpServletRequest request,String picName);
}  