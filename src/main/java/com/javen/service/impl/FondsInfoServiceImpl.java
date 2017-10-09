package com.javen.service.impl;
 

import java.util.List;
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
import com.javen.dao.FondsInfoDao;
import com.javen.function.AjaxJson;
import com.javen.model.CaseInfo;
import com.javen.model.FileInfo;
import com.javen.model.FileInfoPicture;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;
import com.javen.model.FondsInfo;
import com.javen.service.FondsInfoService;
  
@Service("fondsInfoService")  
public class FondsInfoServiceImpl implements FondsInfoService {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Resource  
    private FondsInfoDao fondsInfoDao;
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
	//创建全宗
	@Override
	public AjaxJson createFondsInfo(FondsInfo fondsInfo) {
		log.info("进入创建全宗ServiceImp层");
		if(null!=fondsInfoDao.checkFondExist(fondsInfo)){//根据全宗名和全宗号检查全宗是否存在
			log.info("所要创建的全宗信息已存在");
			aj.setMsg("对不起，您创建的宗卷名或宗卷号已存在");
			return aj;
		}
		else{
			log.info("所要创建的全宗信息不存在准备创建该全宗");
			fondsInfoDao.insert(fondsInfo);//不存在就创建
			aj.setMsg("创建成功");
			return aj;
		}
	}
	//删除某一全宗(挂接删除案卷，卷内，归档业务)
	@Override
	public AjaxJson deleteFondsInfo(String fondsNum) {
		log.info("进入删除某一全宗ServiceImp层，参数fondsNum："+fondsNum);
		if(null!=fondsNum){//根据全宗名和全宗号检查全宗是否存在
			log.info("要删除的该存在在数据库存在，准备删除");
			CaseInfo caseInfo=new CaseInfo();
			caseInfo.setFondsNum(fondsNum);
			FileInfo fileInfo=new FileInfo();
			fileInfo.setfondsNum(fondsNum);
			FileRecords fileRecords=new FileRecords();
			fileRecords.setfondsNum(fondsNum);
			FileInfoPicture fileInfoPicture=new FileInfoPicture();
			fileInfoPicture.setFondsNum(fondsNum);
			FileRecordsPicture fileRecordsPicture=new FileRecordsPicture();
			fileRecordsPicture.setFondsNum(fondsNum);
			fondsInfoDao.deleteByFondsNum(fondsNum);
			log.info("全宗已删除");
			caseInfoDao.deleteByCaseInfo(caseInfo);
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
			log.info("要删除的全宗不存在，进入返回提示");
			aj.setMsg("对不起，您要删除的全宗不存在，请检查全宗名与全宗号是否匹配或是否存在");//不存在改全宗，返回原因
			return aj;
		}
	}
	//无条件查询所有全宗号，返回list集合
	@Override
	public List<String> queryFondsInfo() {//查询所有全宗号
		log.info("进入无条件查询所有全宗号，返回list集合ServiceImp层");
		return fondsInfoDao.queryFondsInfo();
	}
	//无条件查询fondsInfo表中所有详细信息
	@Override
	public List<FondsInfo> getFondsInfoForAll() {
		log.info("进入无条件查询所有全宗信息ServiceImp");
		return fondsInfoDao.getFondsInfoForAll();
	}
	//修改全宗信息
	@Override
	public AjaxJson updateFondsInfo(FondsInfo fondsInfo) {
		log.info("进入修改全宗信息ServiceImp");
		fondsInfoDao.updateByPrimaryKey(fondsInfo);
		aj.setMsg("修改成功");
		return aj;
	}
	//根据条件fondsName fondsNum模糊查询全宗
	@Override
	public List<FondsInfo> queryFondsInfoByFondsNameAndFondsNum(FondsInfo fondsInfo) {
		log.info("进入根据全宗条件fondsName："+fondsInfo.getFondsName()+" fondsNum:"+fondsInfo.getFondsNum()
		+"模糊查询全宗ServiceImp层");
		return fondsInfoDao.queryFondsInfoByFondsNameAndFondsNum(fondsInfo);
	}
	
	//批量修改全宗
		@Override
		public AjaxJson updateMoreFondsInfo(HttpServletRequest request) {
			log.info("进入批量修改全宗ServiceImp层");
			String ziduan=request.getParameter("ziduan");
			String oldWord=request.getParameter("oldWord");
			String newWord=request.getParameter("newWord");
			String fondsNum=request.getParameter("fondsNum");
			log.info("ziduan："+ziduan+"oldWord"+oldWord+"newWord"+newWord+"fondsNum"+fondsNum);
			fondsInfoDao.updateMoreFondsInfo(ziduan,oldWord,newWord,fondsNum);
			aj.setMsg("批量修改成功");
			return aj;
		}
	
    
}  
