package com.conf.model;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class DataSourceInfo {
    private int id;
    private String name;
    private String type;
    private String driverClassName;
    private String driverUrl;
    private String userName;
    private String passWord;
    private String sqlTemplate;

    public DataSourceInfo(){}

    public DataSourceInfo(int id, String name, String type, String driverClassName, String driverUrl, String userName, String passWord, String sqlTemplate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.driverClassName = driverClassName;
        this.driverUrl = driverUrl;
        this.userName = userName;
        this.passWord = passWord;
        this.sqlTemplate = sqlTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverUrl() {
        return driverUrl;
    }

    public void setDriverUrl(String driverUrl) {
        this.driverUrl = driverUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSqlTemplate() {
        return sqlTemplate;
    }

    public void setSqlTemplate(String sqlTemplate) {
        this.sqlTemplate = sqlTemplate;
    }

    @Override
    public String toString() {
        return "DataSourceInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", driverUrl='" + driverUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sqlTemplate='" + sqlTemplate + '\'' +
                '}';
    }
}
