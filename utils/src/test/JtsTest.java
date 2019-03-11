import com.conf.InitConfiguration;
import com.conf.model.*;
import com.util.MyException;
import com.util.Util;
import com.xian80.Impl.VectorTileToolsImpl;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.List;
import java.util.Map;

/**
 * @program: spark-vectortile-slice
 * @description: JTS包测试
 * @author: Mr.Ao
 * @create: 2019-02-28 18:26
 **/
public class JtsTest {
    public static ConfBean confBean;
    public static TileInfo tileInfo;
    public static ExtentInfo extentInfo;

    public static void main(String[] args) {

        try {
            confBean = InitConfiguration.getInstance().getConfBean();
            tileInfo = confBean.getTileInfo();
            extentInfo = confBean.getExtentInfo();
            FullExtent fullExtent = extentInfo.getFullExtent();
            InitialExtent initialExtent = extentInfo.getInitialExtent();
            Geometry initGeometry = Util.getPolygon(initialExtent.getXmin(), initialExtent.getYmin(), initialExtent.getXmax(), initialExtent.getYmax());

            System.out.println("initGeometry：" + initGeometry);
            Geometry fullGeometry = Util.getPolygon(fullExtent.getXmin(), fullExtent.getYmin(), fullExtent.getXmax(), fullExtent.getYmax());
            System.out.println("fullGeometry：" + fullGeometry);
            //大于返回1,等于返回0，小于返回-1
            Geometry extent = (Geometry) Util.compare(initGeometry, fullGeometry);
            /************************************************************************************/
//            System.out.println("面积为：" + extent.getArea()/4);
            Envelope env = extent.getEnvelopeInternal();
            List<String> geometryList = null;
            geometryList = Util.splitFullExtent(extent, 4,0);
            for (String geo : geometryList) {
                System.out.println("taskExtent：" + geo);
            }
            String str = "POLYGON ((502167.1699999999 3337913.789999999,502167.1699999999 3348839.044999999,513092.4249999998 3348839.044999999,513092.4249999998 3337913.789999999,502167.1699999999 3337913.789999999))";
            Map<String, Integer> map = new VectorTileToolsImpl().calculateColRows(env, 0);
            System.out.println("开始列：" + map.get("startColumn"));
            System.out.println("结束列：" + map.get("endColumn"));
            System.out.println("开始行：" + map.get("startRow"));
            System.out.println("结束行：" + map.get("endRow"));

            List<String> colRows0 = new VectorTileToolsImpl().getColRows(fullGeometry, 2);
            double tileSizeWidth = 0.0;
            for (int x = 0; x < colRows0.size(); x++) {
                System.out.println(colRows0.get(x));
                String strs[] = colRows0.get(x).split("_");
                System.out.println(new VectorTileToolsImpl().parseXyz2Bound(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), 0));
                String polygon = new VectorTileToolsImpl().parseXyz2Bound(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), 0);
                Geometry geom = new WKTReader().read(polygon);
                System.out.println(geom.getEnvelopeInternal().getWidth());
                tileSizeWidth = geom.getEnvelopeInternal().getWidth();
            }
            List<String> colRows1 = new VectorTileToolsImpl().getColRows(fullGeometry, 3);
            for (int x = 0; x < colRows1.size(); x++) {
                System.out.println(colRows1.get(x));
                String strs[] = colRows1.get(x).split("_");
                System.out.println(new VectorTileToolsImpl().parseXyz2Bound(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), 0));
                String polygon = new VectorTileToolsImpl().parseXyz2Bound(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), 0);
                Geometry geom = new WKTReader().read(polygon);
                System.out.println(geom.getEnvelopeInternal().getWidth());
            }
            System.out.println("****************************************************************************");
        } catch (ParseException | MyException e) {
            e.printStackTrace();
        }
    }
}
