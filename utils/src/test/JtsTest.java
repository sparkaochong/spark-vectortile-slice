import com.conf.Impl.LoadConfImpl;
import com.conf.InitConfiguration;
import com.conf.model.*;
import com.util.MyException;
import com.util.Util;
import com.xian80.Impl.VectorTileToolsImpl;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
            Geometry initGeometry = Util.getPolygon(initialExtent.getXmin(),initialExtent.getYmin(),initialExtent.getXmax(),initialExtent.getYmax());
            System.out.println("initGeometry："+initGeometry);
            Geometry fullGeometry = Util.getPolygon(fullExtent.getXmin(),fullExtent.getYmin(),fullExtent.getXmax(),fullExtent.getYmax());
            System.out.println("fullGeometry："+fullGeometry);
            //大于返回1,等于返回0，小于返回-1
            Geometry extent = (Geometry) Util.compare(initGeometry,fullGeometry);
            System.out.println("extent："+extent);
            /************************************************************************************/
//            System.out.println("面积为：" + extent.getArea()/4);
            Envelope env = extent.getEnvelopeInternal();
            List<String> geometryList = null;
            try {
                geometryList = Util.splitFullExtent(extent,4);
                for(String geo: geometryList){
                    System.out.println("taskExtent："+geo);
                }
                String str = "POLYGON ((502167.1699999999 3337913.789999999,502167.1699999999 3348839.044999999,513092.4249999998 3348839.044999999,513092.4249999998 3337913.789999999,502167.1699999999 3337913.789999999))";
                List<Integer> list = Util.factor(4);
                List<String> geolist =  new VectorTileToolsImpl().getWindowPolygonStr(str,list);
                for(String s: geolist){
                    System.out.println("task："+s);
                }
            } catch (MyException e) {
                e.printStackTrace();
            }

        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
