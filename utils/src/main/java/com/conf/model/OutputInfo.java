package com.conf.model;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class OutputInfo {
    private int id;
    private String outputType;
    private String path;
    private String level;
    private String suffixName;

    public OutputInfo(){}

    public OutputInfo(int id, String outputType, String path, String level, String suffixName) {
        this.id = id;
        this.outputType = outputType;
        this.path = path;
        this.level = level;
        this.suffixName = suffixName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    @Override
    public String toString() {
        return "OutputInfo{" +
                "id=" + id +
                ", outputType='" + outputType + '\'' +
                ", path='" + path + '\'' +
                ", level='" + level + '\'' +
                ", suffixName='" + suffixName + '\'' +
                '}';
    }
}
