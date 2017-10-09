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
import com.javen.dao.DateBRDao;
import com.javen.dao.FileRecordsDao;
import com.javen.dao.FileRecordsPictureDao;
import com.javen.function.AjaxJson;
import com.javen.function.LuceneSearchFile;
import com.javen.model.DateBR;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;
import com.javen.service.FileRecordsPictureService;
import com.javen.service.DateBRService;
  
@Service("dateBRService")  
public  class DateBRServiceImpl implements DateBRService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource
	private DateBRDao dateBRDao;
	
	@Override
	public int dateBackUp(DateBR dateBR) {
		return dateBRDao.insert(dateBR);
	}

	@Override
	public List<DateBR> showRestoreDB() {
		return dateBRDao.showRestoreDB();
	}
	
	
	   
}  
