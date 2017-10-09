package com.javen.model;

import java.util.Date;

public class CaseInfo {
    private Integer id;

    private String fondsNum;

    private String catalogNum;

    private String caseNum;

    private String caseName;

    private String roomNum;

    private String cabNum;

    private String caseYear;

    private String responsibler;

    private String safekeepingTerm;

    private String classifyNum;

    private String startTime;

    private String endTime;

    private String orgType;

    private String fileNum;

    private String archiveNum;

    private Integer pages;

    private String remarks;

    private Date uploadTime;
    
   

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFondsNum() {
        return fondsNum;
    }

    public void setFondsNum(String fondsNum) {
        this.fondsNum = fondsNum == null ? null : fondsNum.trim();
    }

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum == null ? null : catalogNum.trim();
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum == null ? null : caseNum.trim();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
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

    public String getCaseYear() {
        return caseYear;
    }

    public void setCaseYear(String caseYear) {
        this.caseYear = caseYear == null ? null : caseYear.trim();
    }

    public String getResponsibler() {
        return responsibler;
    }

    public void setResponsibler(String responsibler) {
        this.responsibler = responsibler == null ? null : responsibler.trim();
    }

    public String getSafekeepingTerm() {
        return safekeepingTerm;
    }

    public void setSafekeepingTerm(String safekeepingTerm) {
        this.safekeepingTerm = safekeepingTerm == null ? null : safekeepingTerm.trim();
    }

    public String getClassifyNum() {
        return classifyNum;
    }

    public void setClassifyNum(String classifyNum) {
        this.classifyNum = classifyNum == null ? null : classifyNum.trim();
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

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum == null ? null : fileNum.trim();
    }

    public String getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(String archiveNum) {
        this.archiveNum = archiveNum == null ? null : archiveNum.trim();
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}