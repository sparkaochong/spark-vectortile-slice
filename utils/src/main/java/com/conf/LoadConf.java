package com.conf;

import com.conf.model.*;
import org.jdom2.Element;

import java.util.List;

/**
 * Created by aochong Cotter on 2019/2/20.
 */
public interface LoadConf {
    public ConfBean loadConf(Element node);
    public List<DataSourceInfo> injectDataSourceInfo(Element node);
    public List<LayerInfo> injectLayerInfo(Element node);
    public List<OutputInfo> injectOutputInfo(Element node);
    public TileInfo injectTileInfo(Element node);
}
