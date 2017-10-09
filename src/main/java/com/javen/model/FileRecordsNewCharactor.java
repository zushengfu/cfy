package com.javen.model;

public class FileRecordsNewCharactor {
    private Integer id;

    private String fondsNum;

    private String caseNum;

    private String catalogNum;

    private String fileNum;

    private String newCharactor1;

    private String value1;

    private String newCharactor2;

    private String value2;

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

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum == null ? null : caseNum.trim();
    }

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum == null ? null : catalogNum.trim();
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum == null ? null : fileNum.trim();
    }

    public String getNewCharactor1() {
        return newCharactor1;
    }

    public void setNewCharactor1(String newCharactor1) {
        this.newCharactor1 = newCharactor1 == null ? null : newCharactor1.trim();
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }

    public String getNewCharactor2() {
        return newCharactor2;
    }

    public void setNewCharactor2(String newCharactor2) {
        this.newCharactor2 = newCharactor2 == null ? null : newCharactor2.trim();
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2 == null ? null : value2.trim();
    }
}