package com.xian80.Impl;

import com.xian80.VectorTileTools;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class VectorTileToolsImpl implements VectorTileTools {

    @Override
    public List<String> getColRows(Geometry geom, int level) {
        return null;
    }

    @Override
    public String parseXyz2Bound(int x, int y, int z) {
        return null;
    }

    @Override
    public void convert2Pixel(Geometry geom, int x, int y, int level) {

    }
}
