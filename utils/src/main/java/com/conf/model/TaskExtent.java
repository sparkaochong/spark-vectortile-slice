package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: TaskExtent实体
 * @author: Mr.Ao
 * @create: 2019-02-28 17:34
 **/
public class TaskExtent {
    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;
    private int taskNum;

    public TaskExtent() {
    }

    public TaskExtent(double xmin, double ymin, double xmax, double ymax,int taskNum) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.taskNum = taskNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public double getXmin() {
        return this.xmin;
    }

    public double getYmin() {
        return this.ymin;
    }

    public double getXmax() {
        return this.xmax;
    }

    public double getYmax() {
        return this.ymax;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TaskExtent;
    }

    @Override
    public String toString() {
        return "TaskExtent{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                ", taskNum=" + taskNum +
                '}';
    }
}
