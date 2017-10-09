package com.javen.service.impl;
 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.javen.dao.FileRecordsSendDao;
import com.javen.function.AjaxJson;
import com.javen.model.FileRecordsSend;
import com.javen.model.User;
import com.javen.service.FileRecordsSendService;
  
@Service("fileRecordsSendService")  
public  class FileRecordsSendServiceImpl implements FileRecordsSendService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FileRecordsSendDao fileRecordsSendDao;
	private  AjaxJson aj=new AjaxJson();
	//发送移交FileRecords文件，将发送内容，写入对应的发送表里
	@Override
	public AjaxJson sendFileRecords(FileRecordsSend fileRecordsSend) {
		log.info("进入移交数据卷内文件，写进对应的发送表里的ServiceImp层");
		fileRecordsSendDao.insert(fileRecordsSend);
		aj.setMsg("移交成功！！");
		return aj;
	}
	//接收发送给用户的卷内信息
	@Override
	public List<FileRecordsSend> acceptFileRecordsSend(FileRecordsSend fileRecordsSend) {
		log.info("进入接收发送给用户的卷内信息ServiceImp层");
		return fileRecordsSendDao.acceptFileREcordsSend(fileRecordsSend);
	}
	//查看移交卷内表中移交的数据
	@Override
	public List<FileRecordsSend> cxYjJn() {
		log.info("进入查看移交卷内表中已存在的数据ServiceImp层");
		return fileRecordsSendDao.cxYjJn();
	}
	//修改移交数据状态（未查看，已通过，打回）
	@Override
	public AjaxJson updateFileRecordsSend(FileRecordsSend fileRecordsSend) {
		 log.info("进入修改移交数据状态的ServiceImp层");
		 fileRecordsSendDao.updateFileRecordsSend(fileRecordsSend);
		 aj.setMsg("成功操作修改移交卷内表");
		 return aj;
	}
	//根据卷内字段备份，查看已移交数据哪些未被查看
	@Override
	public List<FileRecordsSend> cxNoOperateYjJn() {
		log.info("进入根据卷内字段feiyong，查看已移交数据哪些未被查看ServiceImp层");
		List<FileRecordsSend> list=new ArrayList<>();
		List<FileRecordsSend> list1=new ArrayList<>();
		list=fileRecordsSendDao.cxYjJn();
		if(list!=null){
			for(FileRecordsSend send:list){
				if(send.getBeiyong().equals("未审核"))
					list1.add(send);
			}
		}
		return list1;
	}
	//查询用户自己已移交数据
	@Override
	public List<FileRecordsSend> cxSelfYjJn(HttpServletRequest request) {
		log.info("查询用户自己已移交数据ServiceImp层");
		List<FileRecordsSend> list=new ArrayList<>();
		List<FileRecordsSend> list1=new ArrayList<>();
		list=fileRecordsSendDao.cxYjJn();
		if(list!=null){
			for(FileRecordsSend send:list){
				User user=new User();
				user=(User)request.getSession().getAttribute("user");
				if(user.getUserName().equals(send.getSender())){
					list1.add(send);
				}
			}
		}
		return list1;
	}
	//查询未被处理的数据的条数
	@Override
	public int cxYjJnNewCount() {
		log.info("查询未被处理的卷内数据，即beiyong为未查看ServiceImp层");
		return fileRecordsSendDao.cxYjJnNewCount();
	}
}  
