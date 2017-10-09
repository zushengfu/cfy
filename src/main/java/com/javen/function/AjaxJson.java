package com.javen.function;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javen.model.FileInfoPicture;
import com.javen.model.FileRecordsPicture;
import com.javen.model.User;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
public class AjaxJson {

	private boolean success = false; // 是否成功
	private String msg = "操作成功"; // 提示信息
	private Object obj = null; // 结果集
	private Map<String, AjaxJson> arrayAjaxJson; // 多结果集的JSON信息
	private String sql; // 参数补全后的sql
	private String type;
	private Integer affectedRows; // 受影响的行数
	private Map<String, Object> attributes;
	private String sessionkey = null;
	private String state="success";//sessionkey状态
	private String stateCode = "00";//状态码  00正常 01异常
	private String address;
	private List<Map<String,FileRecordsPicture>> list;
	private List<Map<String,FileInfoPicture>> listFileInfo;
	
	
	public List<Map<String, FileInfoPicture>> getListFileInfo() {
		return listFileInfo;
	}

	public void setListFileInfo(List<Map<String, FileInfoPicture>> listFileInfo) {
		this.listFileInfo = listFileInfo;
	}

	private User user;
	private Integer newCount;
	
	public Integer getNewCount() {
		return newCount;
	}

	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Map<String, FileRecordsPicture>> getList() {
		return list;
	}

	public void setList(List<Map<String, FileRecordsPicture>> list) {
		this.list = list;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public AjaxJson() {
		super();
		arrayAjaxJson = new HashMap<String, AjaxJson>();
	}

	public Map<String, AjaxJson> getArrayAjaxJson() {
		return arrayAjaxJson;
	}

	public void setArrayAjaxJson(Map<String, AjaxJson> arrayAjaxJson) {
		this.arrayAjaxJson = arrayAjaxJson;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(Integer affectedRows) {
		this.affectedRows = affectedRows;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
