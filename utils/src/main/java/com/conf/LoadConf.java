package com.conf;

import com.conf.model.*;
import org.jdom2.Element;

import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: 矢量瓦片工具类
 * @author: Mr.Ao Mr.Yang
 * @create: 2019-02-22 15:24
 **/
public interface LoadConf {
    public ConfBean loadConf(Element node);

    public List<DataSourceInfo> injectDataSourceInfo(Element node);

    public List<LayerInfo> injectLayerInfo(Element node);

    public List<OutputInfo> injectOutputInfo(Element node);

    public TileInfo injectTileInfo(Element node);
}
