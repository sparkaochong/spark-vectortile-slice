package com.xian80.Impl;

import com.conf.InitConfiguration;
import com.conf.model.ConfBean;
import com.conf.model.TileInfo;
import com.util.MyException;
import com.util.Util;
import com.xian80.VectorTileTools;
import org.apache.log4j.Logger;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spark-vectortile-slice
 * @description: 矢量瓦片工具类
 * @author: Mr.Ao Mr.Yang
 * @create: 2019-02-22 15:24
 **/
public class VectorTileToolsImpl implements VectorTileTools {

    private static TileInfo tileInfo = InitConfiguration.getInstance().confBean.getTileInfo();
    private static ConfBean confBean = InitConfiguration.getInstance().confBean;
    private static final Logger LOG = Logger.getLogger(VectorTileToolsImpl.class);

    @Override
    public List<String> getColRows(Geometry geom, int level) throws ParseException {
        List<String> colRowNames = new ArrayList<String>();
        Envelope env = geom.getEnvelopeInternal();
        Map<String, Integer> colRows = this.calculateColRows(env, level);
        if ((int) colRows.get("startColumn") == (int) colRows.get("endColumn") && (int) colRows.get("startRow") == (int) colRows.get("endRow")) {
            colRowNames.add(colRows.get("startColumn") + "_" + colRows.get("endRow"));
            return colRowNames;
        } else {
            for (int x = colRows.get("startColumn"); x <= colRows.get("endColumn"); x++) {
                for (int y = colRows.get("startRow"); y <= colRows.get("endRow"); y++) {
                    Geometry frameCoor = new WKTReader().read(parseXyz2Bound(x, y, level));
                    if (frameCoor.intersects(geom)) {
                        colRowNames.add(x + "_" + y);
                    }
                }
            }
        }
        return colRowNames;
    }

    @Override
    public Map<String, Integer> calculateColRows(Envelope env, int level) {
        Map<String, Integer> colRows = new HashMap<String, Integer>();
        double resolution = tileInfo.getBaseResolution() / (1 << level);
        int startColumn = (int) Math.floor((env.getMinX() - tileInfo.getOriginX()) / resolution / tileInfo.getTileSize());
        int startRow = (int) Math.floor((tileInfo.getOriginY() - env.getMaxY()) / resolution / tileInfo.getTileSize());
        int endColumn = (int) Math.ceil((env.getMaxX() - tileInfo.getOriginX()) / resolution / tileInfo.getTileSize());
        int endRow = (int) Math.ceil((tileInfo.getOriginY() - env.getMinY()) / resolution / tileInfo.getTileSize());
        colRows.put("startColumn", startColumn);
        colRows.put("endColumn", endColumn);
        colRows.put("startRow", startRow);
        colRows.put("endRow", endRow);
        return colRows;
    }

    @Override
    public String parseXyz2Bound(int x, int y, int z) {
        StringBuilder sb = new StringBuilder("POLYGON ((");
        double lngLeft = this.tileXToX(x, (byte) z) + tileInfo.getOriginX();
        double latUp = tileInfo.getOriginY() - this.tileYToY(y, (byte) z);
        double lngRight = this.tileXToX(x + 1, (byte) z) + tileInfo.getOriginX();
        double latDown = tileInfo.getOriginY() - this.tileYToY(y + 1, (byte) z);
        sb.append(lngLeft).append(" ").append(latUp).append(",");
        sb.append(lngRight).append(" ").append(latUp).append(",");
        sb.append(lngRight).append(" ").append(latDown).append(",");
        sb.append(lngLeft).append(" ").append(latDown).append(",");
        sb.append(lngLeft).append(" ").append(latUp).append("))");
        return sb.toString();
    }

    @Override
    public void convert2Pixel(Geometry geom, int x, int y, int level) {
        double resolution = tileInfo.getBaseResolution() / (1 << level);
        Coordinate[] cs = geom.getCoordinates();
        for (Coordinate c : cs) {
            c.x = (int)((((c.x - tileInfo.getOriginX()) / resolution / tileInfo.getTileSize()) - x) * 16 * tileInfo.getTileSize());
            c.y = (int)((((tileInfo.getOriginY() - c.y) / resolution / tileInfo.getTileSize()) - y) * 16 * tileInfo.getTileSize());
        }
    }

    /**
     * 计算切分好的POLYGON,行列可以不相等
     * @param geoStr
     * @param list
     * @return
     * @throws MyException
     */
    public List<String> getWindowPolygonStr(String geoStr, List<Integer> list) throws MyException {
        List<String> polygonList = new ArrayList<>();
        StringBuilder polygonStr = null;
        Geometry geo = null;

        try {
            geo = new WKTReader().read(geoStr);
            Envelope env = geo.getEnvelopeInternal();
            double minX = env.getMinX();
            double minY = env.getMinY();
            if(list.size()!=2){
                throw new MyException("输入的taskExtent数量不合适，请调整后输入！");
            }
            if(list.get(0).compareTo(list.get(1)) == 0){
                if(list.get(0) == 0 && list.get(1) == 0){
                    double xMin = confBean.getExtentInfo().getFullExtent().getXmin();
                    double yMin = confBean.getExtentInfo().getFullExtent().getYmin();
                    double xMax = confBean.getExtentInfo().getFullExtent().getXmax();
                    double yMax = confBean.getExtentInfo().getFullExtent().getYmax();
                    Geometry geometry = Util.getPolygon(xMin,yMin,xMax,yMax);
                    polygonList.add(geometry.toString());
                }else{
                    double step = env.getWidth() / list.get(0);
                    for (int x = 1; x <= list.get(0); x++) {
                        for (int y = 1; y <= list.get(1); y++) {
                            polygonStr = new StringBuilder("POLYGON ((");
                            polygonStr.append(minX).append(" ").append(minY).append(",");
                            polygonStr.append(minX).append(" ").append(minY + step).append(",");
                            polygonStr.append(minX + step).append(" ").append(minY + step).append(",");
                            polygonStr.append(minX + step).append(" ").append(minY).append(",");
                            polygonStr.append(minX).append(" ").append(minY).append("))");
                            polygonList.add(polygonStr.toString());
                            minY += step;
                        }
                        minX += step;
                        minY = env.getMinY();
                    }
                }
            }else{
                double rowStep = env.getWidth() / list.get(0);
                double colStep = env.getHeight() / list.get(1);
                for (int x = 1; x <= list.get(0); x++) {
                    for (int y = 1; y <= list.get(1); y++) {
                        polygonStr = new StringBuilder("POLYGON ((");
                        polygonStr.append(minX).append(" ").append(minY).append(",");
                        polygonStr.append(minX).append(" ").append(minY + colStep).append(",");
                        polygonStr.append(minX + rowStep).append(" ").append(minY + colStep).append(",");
                        polygonStr.append(minX + rowStep).append(" ").append(minY).append(",");
                        polygonStr.append(minX).append(" ").append(minY).append("))");
                        polygonList.add(polygonStr.toString());
                        minY += colStep;
                    }
                    minX += rowStep;
                    minY = env.getMinY();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return polygonList;
    }

    public double tileXToX(long tileX, byte zoom) {
        return pixelXToX(tileX * tileInfo.getTileSize(), zoom);
    }

    public double tileYToY(long tileY, byte zoom) {
        return pixelYToY(tileY * tileInfo.getTileSize(), zoom);
    }

    public double pixelXToX(double pixelX, byte zoom) {
        double resolution = tileInfo.getBaseResolution() / (1 << zoom);
        return pixelX * resolution;
    }

    public double pixelYToY(double pixelY, byte zoom) {
        double resolution = tileInfo.getBaseResolution() / (1 << zoom);
        return pixelY * resolution;
    }

}
