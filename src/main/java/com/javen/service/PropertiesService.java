package com.javen.service;  

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javen.function.AjaxJson;
import com.javen.model.DateBR;
import com.javen.model.FileDescribeNum;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoNewCharactor;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsNewCharactor;
import com.javen.model.SecurityRank;
  
  
public interface PropertiesService {  
	public AjaxJson insertSecurityRank(HttpServletRequest request);
	public int insertFileDescribeNum(FileDescribeNum fileDescribeNum);
	public int addNewFileInfoCharator(FileInfoNewCharactor fileInfoNewCharator);
	public int addNewFileRecordsCharator(FileRecordsNewCharactor fileRecordsNewCharactor);
	public FileInfoNewCharactor getFileInfoNewCharactor(FileInfoNewCharactor fileInfoNewCharactor);
	public FileRecordsNewCharactor getFileRecordsNewCharactor(FileRecordsNewCharactor fileRecordsNewCharactor);
	public List<SecurityRank> getSecurityRank();
	public List<FileDescribeNum> getFileDescribeNum();
	public int restoreSecurityRank();
	public int restoreFileDescribeNum();
	public int deleteSecurityRank(SecurityRank securityRank);
	public int deleteFileDescribeNum(FileDescribeNum fileDescribeNum);
}  