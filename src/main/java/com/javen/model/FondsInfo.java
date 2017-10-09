package com.javen.model;

import java.util.Date;

public class FondsInfo {
    private Integer id;

    private String fondsName;

    private String fondsNum;

    private String roomNum;

    private String cabNum;

    private String startTime;

    private String endTime;

    private Integer catalogNum;

    private Integer caseNum;

    private Integer fileNum;

    private String fondsYear;

    private Integer fileLen;

    private String remarks;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFondsName() {
        return fondsName;
    }

    public void setFondsName(String fondsName) {
        this.fondsName = fondsName == null ? null : fondsName.trim();
    }

    public String getFondsNum() {
        return fondsNum;
    }

    public void setFondsNum(String fondsNum) {
        this.fondsNum = fondsNum == null ? null : fondsNum.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getCabNum() {
        return cabNum;
    }

    public void setCabNum(String cabNum) {
        this.cabNum = cabNum == null ? null : cabNum.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Integer getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(Integer catalogNum) {
        this.catalogNum = catalogNum;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public String getFondsYear() {
        return fondsYear;
    }

    public void setFondsYear(String fondsYear) {
        this.fondsYear = fondsYear == null ? null : fondsYear.trim();
    }

    public Integer getFileLen() {
        return fileLen;
    }

    public void setFileLen(Integer fileLen) {
        this.fileLen = fileLen;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}