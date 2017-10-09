package com.javen.model;

public class FileInfoPicture {
    private Integer id;

    private String fondsNum;

    private String fileYear;

    private String safekeepingTerm;

    private String fileNum;

    private String picName;

    private Boolean txtIndex;

    private String orderNum;
    
    private String orgType;

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

    public String getFileYear() {
        return fileYear;
    }

    public void setFileYear(String fileYear) {
        this.fileYear = fileYear == null ? null : fileYear.trim();
    }

    public String getSafekeepingTerm() {
        return safekeepingTerm;
    }

    public void setSafekeepingTerm(String safekeepingTerm) {
        this.safekeepingTerm = safekeepingTerm == null ? null : safekeepingTerm.trim();
    }
    
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType= orgType == null ? null :orgType.trim();
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum == null ? null : fileNum.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public Boolean getTxtIndex() {
        return txtIndex;
    }

    public void setTxtIndex(Boolean txtIndex) {
        this.txtIndex = txtIndex;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }
}