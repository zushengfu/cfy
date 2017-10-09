package com.javen.model;

public class SecurityRank {
    private Integer id;

    private String skey;

    private String svalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getskey() {
        return skey;
    }

    public void setskey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    public String getsvalue() {
        return svalue;
    }

    public void setsvalue(String svalue) {
        this.svalue = svalue == null ? null : svalue.trim();
    }
}