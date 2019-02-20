package com.xian80;

import org.locationtech.jts.geom.Geometry;

import java.util.List;

/**
 * Created by aochong Cotter on 2019/2/20.
 */
public interface VectorTileTools {
    public List<String> getColRows(Geometry geom,int level);
    public String parseXyz2Bound(int x,int y,int z);
    public void convert2Pixel(Geometry geom,int x,int y,int level);
}
