package com.javen.service.impl;
 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.javen.controller.FondsInfoController;
import com.javen.dao.FileInfoDao;
import com.javen.dao.FileInfoNewCharactorDao;
import com.javen.dao.FileInfoPictureDao;
import com.javen.function.AjaxJson;
import com.javen.function.FileInfoDefined;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoNewCharactor;
import com.javen.model.FileInfoPicture;
import com.javen.model.FileInfoVo;
import com.javen.model.FondsInfo;
import com.javen.service.FileInfoService;
import com.javen.service.PropertiesService;
  
@Service("fileInfoService")  
public  class FileInfoServiceImpl implements FileInfoService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FileInfoDao fileInfoDao;
	@Resource
	private FileInfoPictureDao fileInfoPictureDao;
	@Resource
	private FileInfoNewCharactorDao fileInfoNewCharactorDao;
	@Resource
	private PropertiesService propertiesService;
	private  AjaxJson aj=new AjaxJson();
//	//创建归档
//	@Override
//	public AjaxJson createFileInfo(FileInfo fileInfo) {
//			log.info("进入创建卷内的ServiceRecodsImp层");
//			fileInfo.setFileYear(fileInfo.getCaseYear());//数据库caseInfo表中的CaseYear对应归档表中的FileYear
//			log.info("开始测试创建归档，看是否能ca进业务");
//			fileInfoDao.insert(fileInfo);//不存在就创建
//			aj.setMsg("创建成功");
//			return aj;
//	}
	//创建归档
	@Override
	public AjaxJson createFileInfo(FileInfoVo fileInfoVo) {
			log.info("进入创建卷内的ServiceRecodsImp层");
			log.info("test  fileInfoVo接收fileInfo信息，以及字符串");
			FileInfo fileInfo=new FileInfo();
			fileInfo.setAddress(fileInfoVo.getAddress());
			fileInfo.setCaseNum(fileInfoVo.getCaseNum());
			fileInfo.setCaseYear(fileInfoVo.getCaseYear());
			fileInfo.setFileDescribeNum(fileInfoVo.getFileDescribeNum());
			fileInfo.setFileNum(fileInfoVo.getFileNum());
			System.out.println(fileInfoVo.getFileYear()+"，这可是归档的fileYear？");
			fileInfo.setFilePath(fileInfoVo.getFilePath());
			fileInfo.setFileYear(fileInfoVo.getFileYear());
			fileInfo.setfondsNum(fileInfoVo.getfondsNum());
			fileInfo.setId(fileInfoVo.getId());
			fileInfo.setOrgType(fileInfoVo.getOrgType());
			fileInfo.setPageNum(fileInfoVo.getPageNum());
			fileInfo.setRemarks(fileInfoVo.getRemarks());
			fileInfo.setResponsibler(fileInfoVo.getResponsibler());
			fileInfo.setSafekeepingTerm(fileInfoVo.getSafekeepingTerm());
			fileInfo.setSecurityRank(fileInfoVo.getSecurityRank());
			fileInfo.setState(fileInfoVo.getState());
			fileInfo.setSubjectName(fileInfoVo.getSubjectName());
			fileInfo.setTime(fileInfoVo.getTime());
			fileInfo.setUploadTime(fileInfoVo.getUploadTime());
			//判断是否有新增属性，如果没有，则不触发
			FileInfoNewCharactor fileInfoNewCharactor=new FileInfoNewCharactor();
			fileInfoNewCharactor.setCaseNum(fileInfoVo.getCaseNum());
			fileInfoNewCharactor.setFileNum(fileInfoVo.getFileNum());
			fileInfoNewCharactor.setFileYear(fileInfoVo.getFileYear());
			fileInfoNewCharactor.setFondsNum(fileInfoVo.getfondsNum());
			fileInfoNewCharactor.setNewCharactor1(fileInfoVo.getNewCharactor1());
			fileInfoNewCharactor.setNewCharactor2(fileInfoVo.getNewCharactor2());
			fileInfoNewCharactor.setValue1(fileInfoVo.getValue1());
			fileInfoNewCharactor.setValue2(fileInfoVo.getVlaue2());
			if(null!=fileInfoNewCharactor.getNewCharactor1() | null!=fileInfoNewCharactor.getNewCharactor2()){
				fileInfoNewCharactorDao.insert(fileInfoNewCharactor);
			}
			fileInfoDao.insert(fileInfo);//不存在就创建
			aj.setMsg("创建成功");
			return aj;
	}
	//根据fileInfo单个删除指定的归档
	@Override
	public AjaxJson deleteFileInfoByFileInfo(FileInfo  fileInfo) {
		log.info("进入删除归档ServiceImp层");
		if(null!=fileInfo){  //检查归档文件是否存在
			log.info("进入fileInfo不为null的判断");
			String fileYear=fileInfo.getFileYear();
			FileInfoPicture fileInfoPicture=new FileInfoPicture();
			fileInfoPicture.setFileYear(fileYear);
			fileInfoDao.deleteByPrimaryKey(fileInfo);//根据fileInfo.id删除指定的归档
			log.info("删除归档");
			fileInfoPictureDao.deleteByFileInfoPicture(fileInfoPicture);
			log.info("删除归档图片");
			aj.setMsg("归档已删除");
			return aj;
		}
		else{
			log.info("进入fileInfo为null的判断");
			aj.setMsg("删除失败，请检查全宗号，案卷号与归档号是否存在");//返回删除归档失败原因
			return aj;
		}
	}
	
	//修改归档
	@Override
	public AjaxJson updateFileInfo(FileInfo fileInfo) {
		log.info("进入修改归档ServiceImp层");
		if(null!=fileInfoDao.checkFileExist(fileInfo)){//检查归档文件是否存在
			log.info("进入要修改的归档存在的判断");
			fileInfoDao.updateByFileInfo(fileInfo);
			aj.setMsg("修改成功");
			return aj;
		}
		else{
			log.info("进入要修改的归档不存在的判断");
			aj.setMsg("对不起，您修改的归档不存在，请检查之后再操作");//返回无法修改原因
			return aj;
		}
	}
	//根据宗卷号和案卷号，查询归档文件
	@Override
	public List<String> queryFileInfo(FileInfo fileInfo) {//根据宗卷号和案卷号查询归档文件
		log.info("进入根据宗卷号和案卷号查询归档文件ServiceImp层");
		return fileInfoDao.queryFileInfo(fileInfo);
	}
	//根据条件删除指定的归档
	@Override
	public AjaxJson deleteFileInfo(FileInfo fileInfo) {//根据条件删除指定的归档
		log.info("进入根据条件删除指定的归档文件");
		fileInfoDao.deleteByFileInfo(fileInfo);
		aj.setMsg("删除成功");
		return aj;
	}
	//根据条件fondsNum catalogNum fileNum查询归档
	@Override
	public List<FileInfo> queryFileInfoByThreeCondition(FileInfo fileInfo) {//根据条件fonsNum catalogNum fileNum查询归档
		log.info("进入根据条件fondsNum："+fileInfo.getfondsNum()+" catalogNum:"+fileInfo.getFileYear()
		+"  fileNum:"+fileInfo.getFileNum()+"查询归档ServiceImp层");
		return fileInfoDao.queryFileInfoByThreeCondition(fileInfo);
	}
	//挂接案卷业务的操作，（参数：fondsNum，原caseNum，newCaseNum）
	@Override
	public AjaxJson hookCaseInfo(String fondsNum, String caseNum, String newCaseNum) {
		log.info("进入挂接修改案卷业务的归档操作ServiceImp层");
		fileInfoDao.hookCaseInfo(fondsNum,caseNum,newCaseNum);
		aj.setMsg("归档挂接成功挂接案卷");
		return aj;
	}
	//挂接全宗业务的操作，（参数：fondsNum，newFondsNum）
	@Override
	public AjaxJson hookFondsInfo(String fondsNum,String newFondsNum) {
		log.info("进入挂接修改全宗业务后归档业务操作ServiceImp层");
		fileInfoDao.hookFondsInfo(fondsNum,newFondsNum);
		aj.setMsg("归档挂接成功挂接案卷");
		return aj;
	}
	//无条件查询所有归档信息
	@Override
	public List<FileInfoDefined> queryFileInfoNoCondition() {
		log.info("进入无条件查询所有归档业务操作ServiceImp层");
		List<FileInfoDefined> rlist=new ArrayList<FileInfoDefined>();
		List<FileInfo> lists=new ArrayList<FileInfo>();
		 lists=fileInfoDao.queryFileInfoNoCondition();
		 for(FileInfo fileInfo:lists){
			 FileInfoNewCharactor fileInfoNewCharactor=new FileInfoNewCharactor();
			 FileInfoDefined fileInfoDefined=new FileInfoDefined();
			 fileInfoNewCharactor.setCaseNum(fileInfoNewCharactor.getCaseNum());
			 fileInfoNewCharactor.setFondsNum(fileInfo.getfondsNum());
			 fileInfoNewCharactor.setFileYear(fileInfo.getFileYear());
			 fileInfoNewCharactor.setFileNum(fileInfo.getFileNum());
			 fileInfoNewCharactor=propertiesService.getFileInfoNewCharactor(fileInfoNewCharactor);
			 fileInfoDefined.setFileInfo(fileInfo);
//			 if(null!=fileInfoNewCharactor){
//				 String newCharactor1=fileInfoNewCharactor.getNewCharactor1();
//				 String value1=fileInfoNewCharactor.getValue1();
//				 String newCharactor2=fileInfoNewCharactor.getNewCharactor2();
//				 String value2=fileInfoNewCharactor.getValue2();
//				 fileInfoDefined.setNewCharactor1(newCharactor1);
//				 fileInfoDefined.setValue1(value1);
//				 fileInfoDefined.setNewCharactor2(newCharactor2);
//				 fileInfoDefined.setValue2(value2);
				 fileInfoDefined.setNewCharactor1("我草");
				 fileInfoDefined.setValue1("不舒服");
				 fileInfoDefined.setNewCharactor2("她草");
				 fileInfoDefined.setValue2("蛮舒服");
//			 }
			 rlist.add(fileInfoDefined);
		 }
		 return rlist;
	}
	//批量修改归档信息
	@Override
	public AjaxJson plXgGd(HttpServletRequest request) {
		log.info("进入批量修改归档ServiceImp层");
		String fondsNum=request.getParameter("fondsNum");
		String caseNum=request.getParameter("caseNum");
		String catalogNum=request.getParameter("catalogNum");
		String fileNum=request.getParameter("fileNum");
		String ziduan=request.getParameter("ziduan");
		String oldWord=request.getParameter("oldWord");
		String newWord=request.getParameter("newWord");
		log.info("fondsNum:"+fondsNum+" caseNum:"+caseNum+" catalogNum:"+catalogNum
				+" fileNum:"+fileNum+" ziduan:"+ziduan+" oldWord:"+oldWord+" newWord:"+newWord);
		fileInfoDao.updateMoreFileInfo(fondsNum,caseNum,catalogNum,fileNum,ziduan,oldWord,newWord);
		return null;
	}
	@Override
	public int cxZs() {
		return fileInfoDao.cxZs();
	}
	
	
}  
