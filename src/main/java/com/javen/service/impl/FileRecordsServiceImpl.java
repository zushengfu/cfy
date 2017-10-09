package com.javen.service.impl;
 

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.javen.controller.FondsInfoController;
import com.javen.dao.FileRecordsDao;
import com.javen.dao.FileRecordsNewCharactorDao;
import com.javen.dao.FileRecordsPictureDao;
import com.javen.function.AjaxJson;
import com.javen.function.FileRecordsDefined;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsNewCharactor;
import com.javen.model.FileRecordsPicture;
import com.javen.model.FileRecordsVo;
import com.javen.service.FileRecordsService;
import com.javen.service.PropertiesService;
  
@Service("fileRecordsService")  
public  class FileRecordsServiceImpl implements FileRecordsService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FileRecordsDao fileRecordsDao;
	@Resource  
	private FileRecordsPictureDao fileRecordsPictureDao;
	@Resource  
	private PropertiesService propertiesServcie;
	@Resource
	private FileRecordsNewCharactorDao fileRecordsNewCharactorDao;
	private  AjaxJson aj=new AjaxJson();
	
//	//创建卷内
//	@Override
//	public AjaxJson createFileRecords(FileRecords fileRecords) {
//		log.info("进入创建卷内ServiceImp层");
//		if(null==fileRecordsDao.checkFileRecordsExist(fileRecords)){
//			log.info("进入传过来的卷内fileRecords在数据库中不存在的判断");
//			fileRecordsDao.insert(fileRecords);//不存在就创建？？？
//			aj.setMsg("创建成功");
//			return aj;
//		}
//		else{
//			log.info("进入闯过来的卷内fileRecords在数据库已存在的判断");
//			aj.setMsg("对不起，卷内已存在");
//			return aj;
//		}
//	}
	
	//创建卷内
	@Override
	public AjaxJson createFileRecords(@RequestBody FileRecordsVo fileRecordsVo) {
		log.info("进入创建卷内ServiceImp层");
		FileRecords fileRecords=new FileRecords();
		fileRecords.setAddress(fileRecordsVo.getAddress());
		fileRecords.setCaseNum(fileRecordsVo.getCaseNum());
		fileRecords.setCatalogNum(fileRecordsVo.getCatalogNum());
		fileRecords.setFileDescribeNum(fileRecordsVo.getFileDescribeNum());
		fileRecords.setFileNum(fileRecordsVo.getFileNum());
		fileRecords.setFilePath(fileRecordsVo.getFilePath());
		fileRecords.setFileYear(fileRecordsVo.getFileYear());
		fileRecords.setfondsNum(fileRecordsVo.getfondsNum());
		fileRecords.setId(fileRecordsVo.getId());
		fileRecords.setOrgType(fileRecordsVo.getOrgType());
		fileRecords.setPageNum(fileRecordsVo.getPageNum());
		fileRecords.setRemarks(fileRecordsVo.getRemarks());
		fileRecords.setResponsibler(fileRecordsVo.getResponsibler());
		fileRecords.setSafekeepingTerm(fileRecordsVo.getSafekeepingTerm());
		fileRecords.setSecurityRank(fileRecordsVo.getSecurityRank());
		fileRecords.setState(fileRecordsVo.getState());
		fileRecords.setSubjectName(fileRecordsVo.getSubjectName());
		fileRecords.setTime(fileRecordsVo.getTime());
		fileRecords.setUploadTime(fileRecordsVo.getUploadTime());
		//判断是否有新的属性，没有就不添加
		FileRecordsNewCharactor fileRecordsNewCharactor=new FileRecordsNewCharactor();
		fileRecordsNewCharactor.setCaseNum(fileRecordsVo.getCaseNum());
		fileRecordsNewCharactor.setFileNum(fileRecordsVo.getFileNum());
		fileRecordsNewCharactor.setCatalogNum(fileRecordsVo.getCatalogNum());
		fileRecordsNewCharactor.setFondsNum(fileRecordsVo.getfondsNum());
		fileRecordsNewCharactor.setNewCharactor1(fileRecordsVo.getNewCharactor1());
		fileRecordsNewCharactor.setNewCharactor2(fileRecordsVo.getNewCharactor2());
		fileRecordsNewCharactor.setValue1(fileRecordsVo.getValue1());
		fileRecordsNewCharactor.setValue2(fileRecordsVo.getValue2());
		if(null!=fileRecordsNewCharactor.getNewCharactor1() | null!=fileRecordsNewCharactor.getNewCharactor2()){
			fileRecordsNewCharactorDao.insert(fileRecordsNewCharactor);
		}
		log.info("test fileRecordsVo是否可行！！！！");
		if(null==fileRecordsDao.checkFileRecordsExist(fileRecords)){
			log.info("进入传过来的卷内fileRecords在数据库中不存在的判断");
			fileRecordsDao.insert(fileRecords);//不存在就创建？？？
			aj.setMsg("创建成功");
			return aj;
		}
		else{
			log.info("进入闯过来的卷内fileRecords在数据库已存在的判断");
			aj.setMsg("对不起，卷内已存在");
			return aj;
		}
	}
	//单个删除某一卷内
	@Override
	public AjaxJson deleteFileRecords(FileRecords fileRecords) {
		log.info("进入单个删除某一卷内ServiceImp层");
		if(null!=fileRecords){  //检查该卷内是否存在？？？
			log.info("进入要删除的fileRecords不为null的判断");
			String catalogNum=fileRecords.getCatalogNum();
			FileRecordsPicture fileRecordsPicture=new FileRecordsPicture();
			fileRecordsPicture.setCatalogNum(catalogNum);
			fileRecordsDao.deleteByPrimaryKey(fileRecords);
			log.info("卷内已删除");
			fileRecordsPictureDao.deleteByFileRecordsPicture(fileRecordsPicture);
			log.info("卷内图片已删除");
			aj.setMsg("删除成功");
			return aj;
		}
		else{
			log.info("进入要删除的fileRecords为null的判断");
			aj.setMsg("对不起，您要删除的该卷内不存在，请仔细检查");//返回删除失败原因
			return aj;
		}
	}
	//修改卷内
	@Override
	public AjaxJson updateFileRecords(FileRecords fileRecords) {
		log.info("进入修改卷内ServiceImp层");
		if(null!=fileRecordsDao.checkFileRecordsExist(fileRecords)){  //检查该卷内是否存在
			log.info("进入要修改的卷内存在的判断");
			fileRecordsDao.updateFileRecords(fileRecords);
			aj.setMsg("修改成功");
			return aj;
		}
		else{
			log.info("进入要修改的卷内不存在的判断");
			aj.setMsg("对不起，您要修改的该卷内不存在，或者输入有误，请仔细检查");//返回修改失败原因
			return aj;
		}
	}
	//根据全宗号，目录号，案卷号，查询卷内文件号         *！*！*
	@Override
	public List<String> queryFileRecords(FileRecords fileRecords) {//根据全宗号，目录号，案卷号查询卷内文件
		log.info("进入根据全宗号，目录号，案卷号，查询卷内文件号list集合的ServiceImp层");//*!*!*
		return fileRecordsDao.queryFileRecords(fileRecords);
	}
	//挂接案卷业务的操作，（参数：fondsNum，原caseNum，newCaseNum）
	@Override
	public AjaxJson hookCaseInfo(String fondsNum, String caseNum, String newCaseNum) {
		log.info("进入案卷修改后，挂接案卷的卷内业务ServiceImp层");
		fileRecordsDao.hookCaseInfo(fondsNum,caseNum,newCaseNum);
		aj.setMsg("卷内成功挂接案卷");
		return aj;
	}
	//挂接全宗业务的操作，（参数：fondsNum，newFondsNum）
	@Override
	public AjaxJson hookFondsInfo(String fondsNum, String newFondsNum) {
		log.info("进入全宗修改后，挂接全宗的卷内业务ServiceImp层");
		fileRecordsDao.hookFondsInfo(fondsNum,newFondsNum);
		aj.setMsg("卷内成功挂接全宗");
		return aj;
	}
	//无条件查询卷内业务
	@Override
	public List<FileRecordsDefined> queryFileRecordsNoCondition() {
		log.info("进入无条件查询卷内业务的ServiceImp层");
		List<FileRecordsDefined> rlist=new ArrayList<FileRecordsDefined>();
		List<FileRecords> list = new ArrayList<FileRecords>();
		list=fileRecordsDao.queryFileRecordsNoCondition();
		for(FileRecords fileRecords:list){
			FileRecordsNewCharactor fileRecordsNewCharactor=new FileRecordsNewCharactor();
			FileRecordsDefined fileRecordsDefined=new FileRecordsDefined();
			fileRecordsNewCharactor.setCaseNum(fileRecords.getCaseNum());
			fileRecordsNewCharactor.setFondsNum(fileRecords.getfondsNum());
			fileRecordsNewCharactor.setCatalogNum(fileRecords.getCatalogNum());
			fileRecordsNewCharactor.setFileNum(fileRecords.getFileNum());
			fileRecordsNewCharactor=propertiesServcie.getFileRecordsNewCharactor(fileRecordsNewCharactor);
			fileRecordsDefined.setfileRecords(fileRecords);
			if(null!=fileRecordsNewCharactor){
				String newCharactor1=fileRecordsNewCharactor.getNewCharactor1();
				String value1=fileRecordsNewCharactor.getValue1();
				String newCharactor2=fileRecordsNewCharactor.getNewCharactor2();
				String value2=fileRecordsNewCharactor.getValue2();
				fileRecordsDefined.setNewCharactor1(newCharactor1);
				fileRecordsDefined.setValue1(value1);
				fileRecordsDefined.setNewCharactor2(newCharactor2);
				fileRecordsDefined.setValue2(value2);
//				fileRecordsDefined.setNewCharactor1("操逼");
//				fileRecordsDefined.setValue1("疼");
//				fileRecordsDefined.setNewCharactor2("打飞机");
//				fileRecordsDefined.setValue2("累");
			}
			rlist.add(fileRecordsDefined);
		}
		return rlist;
	}
	//模糊查询卷内业务
	@Override
	public List<FileRecords> queryFileRecordsByThreeCondition(FileRecords fileRecords) {
		log.info("进入模糊查询卷内业务的ServiceImp层");
		return fileRecordsDao.queryFileInfoByThreeCondition(fileRecords);
	}
	//无条件查询卷内业务数量
	@Override
	public int cxZs(){
		return fileRecordsDao.cxZs();
	}
	//批量修改卷内
	@Override
	public AjaxJson plXgJn(HttpServletRequest request) {
		log.info("进入批量修改卷内ServiceImp层");
		String fondsNum=request.getParameter("fondsNum");
		String caseNum=request.getParameter("caseNum");
		String fileYear=request.getParameter("fileYear");
		String fileNum=request.getParameter("fileNum");
		String ziduan=request.getParameter("ziduan");
		String oldWord=request.getParameter("oldWord");
		String newWord=request.getParameter("newWord");
		log.info("fondsNum:"+fondsNum+" caseNum:"+caseNum+" fileYear:"+fileYear+" fileNum:"+fileNum+" ziduan:"+ziduan+" oldWord:"+oldWord+"newWord:"+newWord);
		fileRecordsDao.updateMoreFileRecords(fondsNum,caseNum,fileYear,fileNum,ziduan,oldWord,newWord);
		aj.setMsg("批量修改成功");
		return aj;
	}

}  
