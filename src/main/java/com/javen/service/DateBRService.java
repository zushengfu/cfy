package com.javen.service;  

import java.util.List;

import com.javen.function.AjaxJson;
import com.javen.model.DateBR;
import com.javen.model.FileInfo;
import com.javen.model.FileRecords;
  
  
public interface DateBRService {  
	public int dateBackUp(DateBR dateBR);
	public List<DateBR> showRestoreDB();
}  