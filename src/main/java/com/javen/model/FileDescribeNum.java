package com.javen.model;

public class FileDescribeNum {
    private Integer id;

    private String fkey;

    private String fvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfkey() {
        return fkey;
    }

    public void setfkey(String fkey) {
        this.fkey = fkey == null ? null : fkey.trim();
    }

    public String getfvalue() {
        return fvalue;
    }

    public void setfvalue(String fvalue) {
        this.fvalue = fvalue == null ? null : fvalue.trim();
    }
}