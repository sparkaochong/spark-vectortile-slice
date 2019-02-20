package com.conf.Impl;

import com.conf.LoadConf;
import com.conf.model.*;
import org.jdom2.Element;

import java.util.List;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class LoadConfImpl implements LoadConf {
    @Override
    public ConfBean loadConf(Element node) {
        return null;
    }

    @Override
    public List<DataSourceInfo> injectDataSourceInfo(Element node) {
        return null;
    }

    @Override
    public List<LayerInfo> injectLayerInfo(Element node) {
        return null;
    }

    @Override
    public List<OutputInfo> injectOutputInfo(Element node) {
        return null;
    }

    @Override
    public TileInfo injectTileInfo(Element node) {
        return null;
    }
}
