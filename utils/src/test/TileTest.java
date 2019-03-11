import com.conf.InitConfiguration;
import com.conf.model.ConfBean;
import com.conf.model.ExtentInfo;
import com.conf.model.FullExtent;
import com.conf.model.TileInfo;
import com.util.Util;
import com.xian80.Impl.VectorTileToolsImpl;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;

import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: 瓦片测试类
 * @author: Mr.Ao
 * @create: 2019-03-06 10:12
 **/
public class TileTest {
    public static ConfBean confBean;
    public static TileInfo tileInfo;
    public static ExtentInfo extentInfo;

    public static void main(String[] args) {
        confBean = InitConfiguration.getInstance().getConfBean();
        tileInfo = confBean.getTileInfo();
        extentInfo = confBean.getExtentInfo();
        FullExtent fullExtent = extentInfo.getFullExtent();
        try{
            Geometry fullGeometry = Util.getPolygon(fullExtent.getXmin(),fullExtent.getYmin(),fullExtent.getXmax(),fullExtent.getYmax());
            System.out.println(fullGeometry);
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    public static boolean isOverlap(Geometry geom, List<Integer> list){
        Envelope env = geom.getEnvelopeInternal();
        double width = env.getWidth()/16;
        if(isEqual(list)){
            double step = env.getWidth()/list.get(0);
            if(step%width==0){
                return true;
            }
        }
        return false;
    }

    public static boolean isEqual(List<Integer> list){
        if(list.get(0).compareTo(list.get(1))==0){
            return true;
        }
        return false;
    }
}
