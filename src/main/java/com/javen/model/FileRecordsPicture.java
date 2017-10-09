package com.javen.model;

public class FileRecordsPicture {
    private Integer id;

    private String fondsNum;

    private String catalogNum;

    private String caseNum;

    private String fileNum;

    private String picName;

    private Boolean txtIndex;

    private String orderNum;

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