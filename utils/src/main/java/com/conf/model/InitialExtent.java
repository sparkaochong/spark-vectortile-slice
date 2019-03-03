package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: InitialExtent实体
 * @author: Mr.Ao
 * @create: 2019-02-28 18:09
 **/
public class InitialExtent {
    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;
    private int taskExtent;

    public InitialExtent() {
    }

    public InitialExtent(double xmin, double ymin, double xmax, double ymax,int taskExtent) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.taskExtent = taskExtent;
    }

    public int getTaskExtent() {
        return taskExtent;
    }

    public void setTaskExtent(int taskExtent) {
        this.taskExtent = taskExtent;
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
        return "InitialExtent{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                ", taskExtent=" + taskExtent +
                '}';
    }
}
