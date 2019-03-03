package com.conf.model;

import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: ExtentInfo实体类
 * @author: Mr.Ao
 * @create: 2019-02-28 16:46
 **/
public class ExtentInfo {
    private InitialExtent initialExtent;
    private FullExtent fullExtent;
    private TaskExtent taskExtent;

    public ExtentInfo() {
    }

    public ExtentInfo(InitialExtent initialExtent, FullExtent fullExtent, TaskExtent taskExtent) {
        this.initialExtent = initialExtent;
        this.fullExtent = fullExtent;
        this.taskExtent = taskExtent;
    }

    public TaskExtent getTaskExtent() {
        return taskExtent;
    }

    public void setTaskExtent(TaskExtent taskExtent) {
        this.taskExtent = taskExtent;
    }

    public InitialExtent getInitialExtent() {
        return this.initialExtent;
    }

    public FullExtent getFullExtent() {
        return this.fullExtent;
    }


    public void setInitialExtent(InitialExtent initialExtent) {
        this.initialExtent = initialExtent;
    }

    public void setFullExtent(FullExtent fullExtent) {
        this.fullExtent = fullExtent;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExtentInfo;
    }

    @Override
    public String toString() {
        return "ExtentInfo{" +
                "initialExtent=" + initialExtent +
                ", fullExtent=" + fullExtent +
                ", taskExtentList=" + taskExtent +
                '}';
    }
}
