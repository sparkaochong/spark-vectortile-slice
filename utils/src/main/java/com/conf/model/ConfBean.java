package com.conf.model;

import java.util.List;


/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class ConfBean {
    private TileInfo		tileInfo;
    private List<LayerInfo>		layerInfos;
    private List<OutputInfo>	outputInfos;
    private List<DataSourceInfo>	dataSourceInfos;

    public ConfBean()
    {
    }


    public ConfBean( TileInfo tileInfo, List<LayerInfo> layerInfos, List<OutputInfo> outputInfos, List<DataSourceInfo> dataSourceInfos )
    {
        this.tileInfo		= tileInfo;
        this.layerInfos		= layerInfos;
        this.outputInfos	= outputInfos;
        this.dataSourceInfos	= dataSourceInfos;
    }


    public TileInfo getTileInfo()
    {
        return(tileInfo);
    }


    public void setTileInfo( TileInfo tileInfo )
    {
        this.tileInfo = tileInfo;
    }


    public List<LayerInfo> getLayerInfos()
    {
        return(layerInfos);
    }


    public void setLayerInfos( List<LayerInfo> layerInfos )
    {
        this.layerInfos = layerInfos;
    }


    public List<OutputInfo> getOutputInfos()
    {
        return(outputInfos);
    }


    public void setOutputInfos( List<OutputInfo> outputInfos )
    {
        this.outputInfos = outputInfos;
    }


    public List<DataSourceInfo> getDataSourceInfos()
    {
        return(dataSourceInfos);
    }


    public void setDataSourceInfos( List<DataSourceInfo> dataSourceInfos )
    {
        this.dataSourceInfos = dataSourceInfos;
    }


    @Override
    public String toString()
    {
        return("ConfBean{" +
                "tileInfo=" + tileInfo +
                ", layerInfos=" + layerInfos +
                ", outputInfos=" + outputInfos +
                ", dataSourceInfos=" + dataSourceInfos +
                '}');
    }
}
