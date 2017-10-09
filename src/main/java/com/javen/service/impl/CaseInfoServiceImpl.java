package com.javen.service.impl;
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.javen.controller.FondsInfoController;
import com.javen.dao.CaseInfoDao;
import com.javen.dao.FileInfoDao;
import com.javen.dao.FileInfoPictureDao;
import com.javen.dao.FileRecordsDao;
import com.javen.dao.FileRecordsPictureDao;
import com.javen.function.AjaxJson;
import com.javen.model.CaseInfo;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoPicture;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;
import com.javen.service.CaseInfoService;
  
@Service("caseInfoService")  
public  class CaseInfoServiceImpl implements CaseInfoService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private CaseInfoDao caseInfoDao;
	@Resource
	private FileInfoDao fileInfoDao;
	@Resource
	private FileRecordsDao fileRecordsDao;
	@Resource
	private FileInfoPictureDao fileInfoPictureDao;
	@Resource
	private FileRecordsPictureDao fileRecordsPictureDao;
	private  AjaxJson aj=new AjaxJson();
	
	//创建案卷
	@Override
	public AjaxJson createCaseInfo(CaseInfo caseInfo) {
		log.info("进入创建案卷ServiceImp层");
		if(null==caseInfo.getCaseNum()|""==caseInfo.getCaseNum()){
			log.info("进入CaseNum为null或者为空的判断");
			aj.setMsg("案卷号不能为空！");
			return aj;
		}
		if(null!=caseInfoDao.checkCaseExist(caseInfo)){  //检查该案卷是否存在
			log.info("进入CaseNum不为空，或者null的情况下，caseInfo已存在判断");
			aj.setMsg("对不起，您创建的案卷号已存在");
			return aj;
		}
		else{
			log.info("进入caseNum不为空，或者不为null，caseInfo不存在的判断");
			if(null!=caseInfo.getCatalogNum()|""!=caseInfo.getCatalogNum()){
				log.info("进入caseNum不为空，或者不为null，caseInfo不存在的判断下的子判断，"
						+ "catalogNum不为null或者不为空的判断");
				log.info("此处案卷号为"+caseInfo.getCaseNum());
//				caseInfo.setCatalogNum(null);
				caseInfoDao.insert(caseInfo);//不存在就创建
				aj.setMsg("创建成功");
			}
			return aj;
		}
	}
	//删除案卷
	@Override
	public AjaxJson deleteCaseInfo(CaseInfo caseInfo) {
		log.info("进入删除案卷ServiceImp层");
		if(null!=caseInfo){  //检查案卷是否存在
			log.info("进入传过来的caseInfo存在的判断");
			String caseNum=caseInfo.getCaseNum();
			FileInfo fileInfo=new FileInfo();
			FileRecords fileRecords=new FileRecords();
			FileInfoPicture fileInfoPicture=new FileInfoPicture();
			FileRecordsPicture fileRecordsPicture=new FileRecordsPicture();
			fileInfo.setCaseNum(caseNum);
			fileRecords.setCaseNum(caseNum);
			fileInfoPicture.setSafekeepingTerm(caseNum);
			fileRecordsPicture.setCaseNum(caseNum);
			log.info(caseInfo.getId()+"*************");
			caseInfoDao.deleteByPrimaryKey(caseInfo);
			log.info("案卷已删除");
			fileInfoDao.deleteByFileInfo(fileInfo);
			log.info("归档已删除");
			fileRecordsDao.deleteByFileRecords(fileRecords);
			log.info("卷内已删除");
			fileInfoPictureDao.deleteByFileInfoPicture(fileInfoPicture);
			log.info("归档图片已删除");
			fileRecordsPictureDao.deleteByFileRecordsPicture(fileRecordsPicture);
			log.info("卷内图片已删除");
			aj.setMsg("删除成功");
			return aj;
		}
		else{	
			log.info("进入传过来的caseInfo不存在的判断");
			aj.setMsg("对不起，您要删除的案卷不存在，请检查全宗号与案卷是否匹配或是否存在");//不存该安军，提示无法删除原因
			return aj;
		}
	}
	//修改案卷
	@Override
	public AjaxJson updateCaseInfo(CaseInfo caseInfo) {
		log.info("进入修改案卷的ServiceImp层");
		if(null!=caseInfo){
			log.info("进入传过来的saseInfo不为空的判断");
			caseInfoDao.updateByCaseInfo(caseInfo);
			aj.setMsg("案卷修改成功");
			return aj;
		}
		else{
			log.info("进入传过来的caseInfo为null的判断");
			aj.setMsg("对不起，您要修改的案卷不存在，请检查后重新修改");//显示无法修改的原因
			return aj;
		}
	}
	//查询某全宗下的所有案卷
	@Override
	public List<String> queryCaseInfo(CaseInfo caseInfo) {//查询某全宗下的案卷
		log.info("进入查询某全宗下的所有案卷  有用参数fondsNum："+caseInfo.getFondsNum());
		return caseInfoDao.queryCaseInfo(caseInfo);
	}
	//查询某全宗下的所有案卷
	@Override 
	public List<String> queryCaseInfoCaseNum(CaseInfo caseInfo) {//查询某全宗下caseNum
		log.info("进入查询某全宗下的所有caseNum"+"ServiceImp层"+" 有用参数fondsNum："+caseInfo.getFondsNum());
		return caseInfoDao.queryCaseInfoCaseNum(caseInfo);
	}
	//查询归档下对应的caseYear
	@Override
	public List<String> queryCaseInfoGdCaseYear(CaseInfo caseInfo) {//查询归档对应的CaseYear
		log.info("进入查询归档下的caseYear   ServiceImp层");
		return caseInfoDao.queryCaseInfoGdCaseYear(caseInfo);
	}
	//查询归档下对应的caseNum
	@Override
	public List<String> queryCaseInfoGdCaseNum(CaseInfo caseInfo) {//查询归档对应的CaseNum
		log.info("进入查询归档下对应的caseNum  ServiceImp层");
		return caseInfoDao.queryCaseInfoGdCaseNum(caseInfo);
	}
	//管理案卷加载页面进入后要展示的案卷信息
	@Override
	public List<CaseInfo> queryCaseInfoNoCondition() {
		log.info("进入查询所有案卷详细信息的ServiceImp层");
		return caseInfoDao.queryCaseInfoNoCondition();
	}
	//挂接全宗业务的操作（全宗业务改fonsNum后，全宗下挂接的表都相应的要改）
	@Override
	public AjaxJson hookFondsInfo(String fondsNum, String newFondsNum) {
		log.info("进入CaseInfo挂接务修改全宗业务FondsInfo后的ServiceImp层    参数fondsNum："+fondsNum
				+" newFondsNum:"+newFondsNum);
		caseInfoDao.hookFondsInfo(fondsNum,newFondsNum);
		aj.setMsg("案卷挂接全宗处理完毕");
		return aj;
	}
	//根据全宗号查询caseInfo里面的catalogNum和caseYear  catalogNum若为空字符串，赋值null
	@Override
	public List<Map<String,String>> queryCaseInfoForAjSearch(CaseInfo caseInfo) {
		log.info("进入根据全宗号fondsNum："+caseInfo.getFondsNum()
				+"查询catalogNum和caseYear的ServciceImp层");
		List<CaseInfo> list=new ArrayList<>();
		List<Map<String,String>> rlist=new ArrayList<>();
		list=caseInfoDao.queryCaseInfoForAjSearch(caseInfo);
		if(null!=list){
			log.info("进入查询到的caseInfo的list集合不为null的判断");
		for(CaseInfo cinfo:list){
			Map<String,String> map=new HashMap<String, String>();
			String catalogNum;
			String caseYear;
			if(null==cinfo.getCatalogNum() | ""==cinfo.getCatalogNum()){
			catalogNum="";
			}
			else{
				catalogNum=cinfo.getCatalogNum();
			}
			map.put("catalogNum",catalogNum);
			if(null==cinfo.getCaseYear() | ""==cinfo.getCaseYear()){
				caseYear="";
				}
			else{
					caseYear=cinfo.getCaseYear();
				}
			map.put("caseYear",caseYear);
			rlist.add(map);
		}
		}
		return rlist;
	}
	//根据条件查询案卷！
	@Override
	public List<CaseInfo> queryCaseInfoWithCondition(CaseInfo caseInfo) {
		log.info("进入根据条件查询案卷详细信息的ServiceImp层"+caseInfo.getCatalogNum()+caseInfo.getFondsNum());
		if(caseInfo!=null)
			log.info("进入闯过来的caseInfo不为null的判断");
//		if(caseInfo.getCatalogNum().length()<=4){
//			caseInfo.setCaseYear(caseInfo.getCatalogNum());
//			caseInfo.setCatalogNum("");
//		}
		return caseInfoDao.queryCaseInfoWithCondition(caseInfo);
//		}
//		else{
//			return caseInfoDao.queryCaseInfoWithCondition(caseInfo);
//		}
	}
	@Override
	public AjaxJson plXgAj(HttpServletRequest request) {
		log.info("进入批量修改案卷ServiceImp层");
		String fondsNum=request.getParameter("fondsNum");
		String caseNum=request.getParameter("caseNum");
		String ziduan=request.getParameter("ziduan");
		String oldWord=request.getParameter("oldWord");
		String newWord=request.getParameter("newWord");
		log.info("fondsNum:"+fondsNum+"caseNum:"+caseNum+"ziduan:"+ziduan+"oldWord:"+oldWord+"newWord:"+newWord);
		caseInfoDao.updateMoreCaseInfo(fondsNum,caseNum,ziduan,oldWord,newWord);
		return null;
	}
}  
