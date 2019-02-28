package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: 图层信息实体类
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public class LayerInfo {
    private int id;
    private String dataSourceID;
    private int wkid;
    private String layerName;
    private int minScale;
    private int maxScale;
    private String dataDesc;

    public LayerInfo() {
    }

    public LayerInfo(int id, String dataSourceID, int wkid, String layerName, int minScale, int maxScale, String dataDesc) {
        this.id = id;
        this.dataSourceID = dataSourceID;
        this.wkid = wkid;
        this.layerName = layerName;
        this.minScale = minScale;
        this.maxScale = maxScale;
        this.dataDesc = dataDesc;
    }

    public int getId() {
        return this.id;
    }

    public String getDataSourceID() {
        return this.dataSourceID;
    }

    public int getWkid() {
        return this.wkid;
    }

    public String getLayerName() {
        return this.layerName;
    }

    public int getMinScale() {
        return this.minScale;
    }

    public int getMaxScale() {
        return this.maxScale;
    }

    public String getDataDesc() {
        return this.dataDesc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataSourceID(String dataSourceID) {
        this.dataSourceID = dataSourceID;
    }

    public void setWkid(int wkid) {
        this.wkid = wkid;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public void setMinScale(int minScale) {
        this.minScale = minScale;
    }

    public void setMaxScale(int maxScale) {
        this.maxScale = maxScale;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LayerInfo)) {
            return false;
        }
        final LayerInfo other = (LayerInfo) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        final Object this$dataSourceID = this.dataSourceID;
        final Object other$dataSourceID = other.dataSourceID;
        if (this$dataSourceID == null ? other$dataSourceID != null : !this$dataSourceID.equals(other$dataSourceID)) {
            return false;
        }
        if (this.wkid != other.wkid) {
            return false;
        }
        final Object this$layerName = this.layerName;
        final Object other$layerName = other.layerName;
        if (this$layerName == null ? other$layerName != null : !this$layerName.equals(other$layerName)) {
            return false;
        }
        if (this.minScale != other.minScale) {
            return false;
        }
        if (this.maxScale != other.maxScale) {
            return false;
        }
        final Object this$dataDesc = this.dataDesc;
        final Object other$dataDesc = other.dataDesc;
        if (this$dataDesc == null ? other$dataDesc != null : !this$dataDesc.equals(other$dataDesc)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LayerInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.id;
        final Object $dataSourceID = this.dataSourceID;
        result = result * PRIME + ($dataSourceID == null ? 43 : $dataSourceID.hashCode());
        result = result * PRIME + this.wkid;
        final Object $layerName = this.layerName;
        result = result * PRIME + ($layerName == null ? 43 : $layerName.hashCode());
        result = result * PRIME + this.minScale;
        result = result * PRIME + this.maxScale;
        final Object $dataDesc = this.dataDesc;
        result = result * PRIME + ($dataDesc == null ? 43 : $dataDesc.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LayerInfo(id=" + this.id + ", dataSourceID=" + this.dataSourceID + ", wkid=" + this.wkid + ", layerName=" + this.layerName + ", minScale=" + this.minScale + ", maxScale=" + this.maxScale + ", dataDesc=" + this.dataDesc + ")";
    }
}
