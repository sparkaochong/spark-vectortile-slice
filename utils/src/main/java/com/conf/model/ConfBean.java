package com.conf.model;

import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: 配置文件信息实体类
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public class ConfBean {
    private TileInfo tileInfo;
    private List<LayerInfo> layerInfos;
    private List<OutputInfo> outputInfos;
    private List<DataSourceInfo> dataSourceInfos;

    public ConfBean() {
    }

    public ConfBean(TileInfo tileInfo, List<LayerInfo> layerInfos, List<OutputInfo> outputInfos, List<DataSourceInfo> dataSourceInfos) {
        this.tileInfo = tileInfo;
        this.layerInfos = layerInfos;
        this.outputInfos = outputInfos;
        this.dataSourceInfos = dataSourceInfos;
    }

    public TileInfo getTileInfo() {
        return this.tileInfo;
    }

    public List<LayerInfo> getLayerInfos() {
        return this.layerInfos;
    }

    public List<OutputInfo> getOutputInfos() {
        return this.outputInfos;
    }

    public List<DataSourceInfo> getDataSourceInfos() {
        return this.dataSourceInfos;
    }

    public void setTileInfo(TileInfo tileInfo) {
        this.tileInfo = tileInfo;
    }

    public void setLayerInfos(List<LayerInfo> layerInfos) {
        this.layerInfos = layerInfos;
    }

    public void setOutputInfos(List<OutputInfo> outputInfos) {
        this.outputInfos = outputInfos;
    }

    public void setDataSourceInfos(List<DataSourceInfo> dataSourceInfos) {
        this.dataSourceInfos = dataSourceInfos;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ConfBean)) {
            return false;
        }
        final ConfBean other = (ConfBean) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$tileInfo = this.tileInfo;
        final Object other$tileInfo = other.tileInfo;
        if (this$tileInfo == null ? other$tileInfo != null : !this$tileInfo.equals(other$tileInfo)) {
            return false;
        }
        final Object this$layerInfos = this.layerInfos;
        final Object other$layerInfos = other.layerInfos;
        if (this$layerInfos == null ? other$layerInfos != null : !this$layerInfos.equals(other$layerInfos)) {
            return false;
        }
        final Object this$outputInfos = this.outputInfos;
        final Object other$outputInfos = other.outputInfos;
        if (this$outputInfos == null ? other$outputInfos != null : !this$outputInfos.equals(other$outputInfos)) {
            return false;
        }
        final Object this$dataSourceInfos = this.dataSourceInfos;
        final Object other$dataSourceInfos = other.dataSourceInfos;
        if (this$dataSourceInfos == null ? other$dataSourceInfos != null : !this$dataSourceInfos.equals(other$dataSourceInfos)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConfBean;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tileInfo = this.tileInfo;
        result = result * PRIME + ($tileInfo == null ? 43 : $tileInfo.hashCode());
        final Object $layerInfos = this.layerInfos;
        result = result * PRIME + ($layerInfos == null ? 43 : $layerInfos.hashCode());
        final Object $outputInfos = this.outputInfos;
        result = result * PRIME + ($outputInfos == null ? 43 : $outputInfos.hashCode());
        final Object $dataSourceInfos = this.dataSourceInfos;
        result = result * PRIME + ($dataSourceInfos == null ? 43 : $dataSourceInfos.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ConfBean(tileInfo=" + this.tileInfo + ", layerInfos=" + this.layerInfos + ", outputInfos=" + this.outputInfos + ", dataSourceInfos=" + this.dataSourceInfos + ")";
    }
}
