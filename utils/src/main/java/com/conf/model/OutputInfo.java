package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: 输出信息实体类
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public class OutputInfo {
    private int id;
    private String outputType;
    private String path;
    private String level;

    public OutputInfo() {
    }

    public OutputInfo(int id, String outputType, String path, String level) {
        this.id = id;
        this.outputType = outputType;
        this.path = path;
        this.level = level;
    }

    public int getId() {
        return this.id;
    }

    public String getOutputType() {
        return this.outputType;
    }

    public String getPath() {
        return this.path;
    }

    public String getLevel() {
        return this.level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutputInfo)) {
            return false;
        }
        final OutputInfo other = (OutputInfo) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        final Object this$outputType = this.outputType;
        final Object other$outputType = other.outputType;
        if (this$outputType == null ? other$outputType != null : !this$outputType.equals(other$outputType)) {
            return false;
        }
        final Object this$path = this.path;
        final Object other$path = other.path;
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) {
            return false;
        }
        final Object this$level = this.level;
        final Object other$level = other.level;
        if (this$level == null ? other$level != null : !this$level.equals(other$level)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OutputInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.id;
        final Object $outputType = this.outputType;
        result = result * PRIME + ($outputType == null ? 43 : $outputType.hashCode());
        final Object $path = this.path;
        result = result * PRIME + ($path == null ? 43 : $path.hashCode());
        final Object $level = this.level;
        result = result * PRIME + ($level == null ? 43 : $level.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "OutputInfo(id=" + this.id + ", outputType=" + this.outputType + ", path=" + this.path + ", level=" + this.level + ")";
    }
}
