package com.xian80;

import com.conf.model.TileInfo;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;

import java.util.List;
import java.util.Map;

/**
 * @program: spark-vectortile-slice
 * @description: 矢量瓦片工具接口
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public interface VectorTileTools {
    public List<String> getColRows(Geometry geom,int level) throws ParseException;
    public String parseXyz2Bound(int x,int y,int z);
    public void convert2Pixel(Geometry geom,int x,int y,int level);
    public Map<String,Integer> calculateColRows(Envelope env,int level);
}
