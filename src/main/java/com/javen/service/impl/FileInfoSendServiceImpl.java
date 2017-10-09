package com.javen.service.impl;
 


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.javen.dao.FileInfoSendDao;
import com.javen.function.AjaxJson;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoSend;
import com.javen.service.FileInfoSendService;
  
@Service("fileInfoSendService")  
public  class FileInfoSendServiceImpl implements FileInfoSendService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FileInfoSendDao fileInfoSendDao;
	private  AjaxJson aj=new AjaxJson();
	//发送移交FileInfo文件，将发送内容，写入对应的发送表里
	@Override
	public AjaxJson sendFileInfo(FileInfoSend fileInfoSend) {
		log.info("进入移交归档文件，将发送内容，写入对应的发送表里ServiceImp层");
		fileInfoSendDao.insert(fileInfoSend);
		aj.setMsg("移交成功！！");
		return aj;
	}
	//接收发送的归档文件
	@Override
	public List<FileInfoSend> acceptFileInfo(FileInfoSend fileInfoSend) {
		log.info("进入接收归档文件的ServiceImp层");
		return fileInfoSendDao.acceptFileInfo(fileInfoSend);
	}
	
}  
