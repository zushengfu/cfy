package com.javen.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.javen.controller.FondsInfoController;
import com.javen.dao.SystemLogDao;
import com.javen.model.SystemLog;
import com.javen.model.User;
import com.javen.service.SystemLogService;

@Service("systemLogService")  
public  class SystemLogServiceImpl implements SystemLogService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
	private SystemLogDao systemLogDao;
	
	@Override
	public void record(User user) {
		SystemLog systemLog=new SystemLog();
		systemLog.setUserAction("登陆");
		systemLog.setUserCode(user.getUserName());
		systemLogDao.record(systemLog);
	}
	
   
}
