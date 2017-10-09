package com.javen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.FileRecordsSend;

public interface FileRecordsSendService {  
	public AjaxJson sendFileRecords(FileRecordsSend fileRecordsSend);
	public List<FileRecordsSend> acceptFileRecordsSend(FileRecordsSend fileRecordsSend);
	public List<FileRecordsSend> cxYjJn();
	public AjaxJson updateFileRecordsSend(FileRecordsSend fileRecordsSend);
	public List<FileRecordsSend> cxNoOperateYjJn();
	public List<FileRecordsSend> cxSelfYjJn(HttpServletRequest request);
	public int cxYjJnNewCount();
}  