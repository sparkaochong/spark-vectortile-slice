import com.conf.Impl.LoadConfImpl;
import com.conf.model.ConfBean;
import com.conf.model.TileInfo;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: spark-vectortile-slice
 * @description: 配置文件加载测试
 * @author: Mr.Ao
 * @create: 2019-02-25 18:00
 **/
public class LoadConfTest {
    //加载配置文件信息
    public static ConfBean confBean;
    public static TileInfo tileInfo;

    public static void main(String[] args) {
        InputStream in = LoadConfImpl.class.getClassLoader().getResourceAsStream("customMap.xml");
        SAXBuilder sax = new SAXBuilder();
        Document doc;
        try {
            doc = sax.build(in);//构造输入流
            Element root = doc.getRootElement();//得到根元素
            confBean = LoadConfImpl.getInstance().loadConf(root);//加载配置文件初始值
            tileInfo = confBean.getTileInfo();
            System.out.println(tileInfo.getTileSize());
            System.out.println(tileInfo.getOriginX());
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
