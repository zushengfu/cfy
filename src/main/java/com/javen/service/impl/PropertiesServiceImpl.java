package com.javen.service.impl;
 


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.javen.dao.FileDescribeNumDao;
import com.javen.dao.FileInfoNewCharactorDao;
import com.javen.dao.FileRecordsNewCharactorDao;
import com.javen.dao.SecurityRankDao;
import com.javen.function.AjaxJson;
import com.javen.model.CaseInfo;
import com.javen.model.FileDescribeNum;
import com.javen.model.FileInfoNewCharactor;
import com.javen.model.FileRecordsNewCharactor;
import com.javen.model.SecurityRank;
import com.javen.service.PropertiesService;

import net.sf.json.JSONArray;
  
@Service("propertiesServiceImpl")  
public  class PropertiesServiceImpl implements PropertiesService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource
	private FileDescribeNumDao fileDescribeNumDao;
	@Resource
	private FileInfoNewCharactorDao fileInfoNewCharatorDao;
	@Resource
	private SecurityRankDao securityRankDao;
	@Resource
	private FileRecordsNewCharactorDao fileRecordsNewCharactorDao;
	private AjaxJson aj=new AjaxJson();
	
	/** 将他们自定义归为一个控制器，不分卷内归档，凡属于自定义，都可在此service层中找到方法
	 * @Param securityRank 实现可以自定义属性值（思路是将securityRank这个属性单建表插入值）
	 * 
	 */
	//新建securityRank属性值
	@Override
	public AjaxJson insertSecurityRank(HttpServletRequest request) {
//		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<>();
		SecurityRank sr=new SecurityRank();
		String mybooksid;
		ServletInputStream inputStream;
		try {
			inputStream = request.getInputStream();
			mybooksid = IOUtils.toString(inputStream);
			JSONArray json=JSONArray.fromObject(mybooksid);
			for(int i=0;i<json.size();i++){
				map=json.getJSONObject(i);
				String key=map.get("skey");
				String value=map.get("svalue");
				sr.setskey(key);
				sr.setsvalue(value);
				System.out.println(value);
				if(null!=securityRankDao.check(sr)){
				}
				else{
					securityRankDao.insert(sr);
					aj.setMsg("添加成功");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aj;
		
		}
		
	/**
	 * @Param fileDescribeNum 实现可以自定义属性值（思路是将fileDescribeNum这个属性单建表插入值）
	 * 
	 */
	//新建fileDescribeNum属性值
	@Override
	public int insertFileDescribeNum(FileDescribeNum fileDescribeNum) {
		return fileDescribeNumDao.insert(fileDescribeNum);
	}
	/**
	 * @param fileInfoNewCharator 用来存储新增字段名和新增字段值的，通过业务fondsNum caseInfo fileYear fileNum newCharactor1 newCharactor2
	 * 分段处理数据，只用于展现。
	 * 
	 */	
	//新建fileInfo表属性及值
	@Override
	public int addNewFileInfoCharator(FileInfoNewCharactor fileInfoNewCharator) {
		return fileInfoNewCharatorDao.insert(fileInfoNewCharator);
	}
	/**
	 * @param fileRecordsNewCharator 用来存储新增字段名和新增字段值的，通过业务fondsNum caseInfo fileYear fileNum newCharactor1 newCharactor2
	 * 分段处理数据，只用于展现。
	 * 
	 */	
	//新建fileRecords表属性及值
	@Override
	public int addNewFileRecordsCharator(FileRecordsNewCharactor fileRecordsNewCharactor) {
		return fileRecordsNewCharactorDao.insert(fileRecordsNewCharactor);
	}
	//根据给出的fondsNum caseNum fileYear fileNum查寻出FielInfoNewCharactor
	@Override
	public FileInfoNewCharactor getFileInfoNewCharactor(FileInfoNewCharactor fileInfoNewCharactor) {
		return fileInfoNewCharatorDao.getSingle(fileInfoNewCharactor);
	}
	//根据给出的fondsNum caseNum catalogNum fileNum查询出FileRecordsNewCharactor
	@Override
	public FileRecordsNewCharactor getFileRecordsNewCharactor(FileRecordsNewCharactor fileRecordsNewCharactor) {
		return fileRecordsNewCharactorDao.getSingle(fileRecordsNewCharactor);
	}
	/**无条件查询属性
	 * @return 返回所有属性
	 */
	//无条件查询出所有SecurityRank
	@Override
	public List<SecurityRank> getSecurityRank() {
		return securityRankDao.getSecurityRank();
	}
	/**无条件查询属性
	 * @return 返回所有属性
	 */
	//无条件查询所有的FileDescribeNum
	@Override
	public List<FileDescribeNum> getFileDescribeNum() {
		return fileDescribeNumDao.getFileDescribeNum();
	}
	/**默认使用id进行控制id 1-4 为默认属性，不可更改，即还原为值为默认值
	 * @return 返回删除数据（还原以实现删除新增，保留原始数据实现，非使用sql还原）
	 */
	//实现恢复默认SecurityRank
	@Override
	public int restoreSecurityRank() {
		return securityRankDao.restoreSecurityRank();
	}
	/**默认使用id进行控制id 1-4 为默认属性，不可更改，即还原为值为默认值
	 * @return 返回删除数据（还原以实现删除新增，保留原始数据实现，非使用sql还原）
	 */
	//实现恢复默认FileDescribeNum
	@Override
	public int restoreFileDescribeNum() {
		return fileDescribeNumDao.restoreFileDescribeNum();
	}
	/**
	 * @Param SecurityRank 对某个自定义属性进行删除
	 * @return 返回删除数据
	 */
	//删除SecurityRank
	@Override
	public int deleteSecurityRank(SecurityRank securityRank) {
		return securityRankDao.deleteByPrimarysKey(securityRank);
	}
	/**
	 * @Param FileDscribeNum 对某个自定义属性进行删除
	 * @return 返回删除数据
	 */
	//删除FileDescribeNum
	@Override
	public int deleteFileDescribeNum(FileDescribeNum fileDescribeNum) {
		return fileDescribeNumDao.deleteByPrimaryfKey(fileDescribeNum);
	}
	
	
	   
	
}  
