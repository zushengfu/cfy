package com.javen.service;

import java.util.List;

import com.javen.function.AjaxJson;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoSend;

public interface FileInfoSendService {  
	public AjaxJson sendFileInfo(FileInfoSend fileInfoSend);
	public List<FileInfoSend> acceptFileInfo(FileInfoSend fileInfoSend);
    
}  