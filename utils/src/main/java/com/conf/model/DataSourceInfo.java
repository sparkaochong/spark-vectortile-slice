package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: 数据源信息实体类
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public class DataSourceInfo {
    private String id;
    private String name;
    private String type;
    private String subType;
    private String driverClassName;
    private String driverUrl;
    private String userName;
    private String passWord;

    public DataSourceInfo() {
    }

    public DataSourceInfo(String id, String name, String type, String subType, String driverClassName, String driverUrl, String userName, String passWord) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.driverClassName = driverClassName;
        this.driverUrl = driverUrl;
        this.userName = userName;
        this.passWord = passWord;
    }


    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getSubType() {
        return this.subType;
    }

    public String getDriverClassName() {
        return this.driverClassName;
    }

    public String getDriverUrl() {
        return this.driverUrl;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setDriverUrl(String driverUrl) {
        this.driverUrl = driverUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DataSourceInfo)) {
            return false;
        }
        final DataSourceInfo other = (DataSourceInfo) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$id = this.id;
        final Object other$id = other.id;
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        final Object this$subType = this.subType;
        final Object other$subType = other.subType;
        if (this$subType == null ? other$subType != null : !this$subType.equals(other$subType)) {
            return false;
        }
        final Object this$driverClassName = this.driverClassName;
        final Object other$driverClassName = other.driverClassName;
        if (this$driverClassName == null ? other$driverClassName != null : !this$driverClassName.equals(other$driverClassName)) {
            return false;
        }
        final Object this$driverUrl = this.driverUrl;
        final Object other$driverUrl = other.driverUrl;
        if (this$driverUrl == null ? other$driverUrl != null : !this$driverUrl.equals(other$driverUrl)) {
            return false;
        }
        final Object this$userName = this.userName;
        final Object other$userName = other.userName;
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) {
            return false;
        }
        final Object this$passWord = this.passWord;
        final Object other$passWord = other.passWord;
        if (this$passWord == null ? other$passWord != null : !this$passWord.equals(other$passWord)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DataSourceInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.id;
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $subType = this.subType;
        result = result * PRIME + ($subType == null ? 43 : $subType.hashCode());
        final Object $driverClassName = this.driverClassName;
        result = result * PRIME + ($driverClassName == null ? 43 : $driverClassName.hashCode());
        final Object $driverUrl = this.driverUrl;
        result = result * PRIME + ($driverUrl == null ? 43 : $driverUrl.hashCode());
        final Object $userName = this.userName;
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $passWord = this.passWord;
        result = result * PRIME + ($passWord == null ? 43 : $passWord.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "DataSourceInfo(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", subType=" + this.subType + ", driverClassName=" + this.driverClassName + ", driverUrl=" + this.driverUrl + ", userName=" + this.userName + ", passWord=" + this.passWord + ")";
    }
}
