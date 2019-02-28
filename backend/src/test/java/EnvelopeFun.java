import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

/**
 * @program: spark-vectortile-slice
 * @description: Envelope函数测试
 * @author: Mr.Ao
 * @create: 2019-02-25 15:43
 **/
public class EnvelopeFun {
    public static void main(String[] args) {
        String polygon = "POLYGON ((502167.1699999999 3337913.789999999, 502167.1699999999 3357653.5, 524017.6799999997 3357653.5, 524017.6799999997 3337913.789999999, 502167.1699999999 3337913.789999999))";
        try{
            Geometry geometry = new WKTReader().read(polygon);
//            System.out.println(geometry.getLength());   //获取周长
            Envelope env = geometry.getEnvelopeInternal();
//            System.out.println(env.getWidth());
//            System.out.println(env.getHeight());
//            System.out.println(env.getWidth()*2 + env.getHeight()*2);
            double d = env.getWidth()/3;
            System.out.println(d);
//            env.getMinX() + d
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
