package com.util;

import com.xian80.Impl.VectorTileToolsImpl;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: 工具类
 * @author: Mr.Ao
 * @create: 2019-02-28 17:58
 **/
public class Util {
    /**
     * 根据最小x,最小y，最大x，最大y生成String类型的Polygon
     * @param xmin  最小x
     * @param ymin  最小y
     * @param xmax  最大x
     * @param ymax  最大y
     * @return  POLYGON((xmin ymin,xmin ymax,xmax ymax,xmax ymin,xmin ymin))
     */
    public static String getPolygonStr(double xmin,double ymin,double xmax,double ymax){
        StringBuilder sb = new StringBuilder("POLYGON ((");
        sb.append(xmin).append(" ").append(ymin).append(",");
        sb.append(xmin).append(" ").append(ymax).append(",");
        sb.append(xmax).append(" ").append(ymax).append(",");
        sb.append(xmax).append(" ").append(ymin).append(",");
        sb.append(xmin).append(" ").append(ymin).append("))");
        return sb.toString();
    }

    /**
     * 根据最小x,最小y，最大x，最大y生成一个Polygon对象
     * @param xmin
     * @param ymin
     * @param xmax
     * @param ymax
     * @return Geometry
     * @throws ParseException
     */
    public static Geometry getPolygon(double xmin,double ymin,double xmax,double ymax) throws ParseException {
        StringBuilder sb = new StringBuilder("POLYGON ((");
        sb.append(xmin).append(" ").append(ymin).append(",");
        sb.append(xmin).append(" ").append(ymax).append(",");
        sb.append(xmax).append(" ").append(ymax).append(",");
        sb.append(xmax).append(" ").append(ymin).append(",");
        sb.append(xmin).append(" ").append(ymin).append("))");
        Geometry geo = new WKTReader().read(sb.toString());
        return geo;
    }

    /**
     * 对象比较
     * @param geo1 对象1
     * @param geo2 对象2
     * @return   Object
     */
    public static Object compare(Geometry geo1, Geometry geo2){
        if(geo1.compareTo(geo2) == 0){
            return geo1;
        }else if(geo1.compareTo(geo2) > 0){
            return geo1;
        }else{
            return geo2;
        }
    }

    /**
     * 得到taskExtent
     * @param geo       要切的范围
     * @param taskNum   task的数量
     * @return List<Geometry>
     * @throws ParseException
     * @throws MyException
     */
    public static List<String> splitFullExtent(Geometry geo,int taskNum,int level) throws ParseException, MyException {
        List<Integer> list = Util.factor(taskNum);
        List<String> polyStr = new VectorTileToolsImpl().getWindowPolygonStr(geo.toString(),list,level);
        return polyStr;
    }

    /**
     * 保留指定小数位，并实现四舍五入
     * @param num   要操作的数
     * @param scale 小数位数
     * @return double
     */
    public static double round(double num,int scale){
        return new BigDecimal(num).divide(new BigDecimal(1),scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static List<Integer> factor(int number) {
        List<Integer> list = new ArrayList<Integer>();
        if(number !=0){
            for(int i=2;i<number;i++) {
                if(number%i==0) {
                    list.add(i);
                    if(isPrime(number/i)) {
                        list.add(number/i);
                    }
                    else {
                        factor(number/i);
                    }
                    break;
                }
            }
        }else{
            list.add(0);
            list.add(0);
        }
        return list;
    }

    //函数：判断是不是素数
    public static boolean isPrime(int number) {
        for(int i=2;i<number;i++) {
            if(number%i==0) {
                return false;
            }
        }
        return true;
    }

}
