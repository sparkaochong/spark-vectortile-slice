package com.conf;

import com.conf.Impl.LoadConfImpl;
import com.conf.model.ConfBean;
import com.conf.model.FullExtent;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: spark-vectortile-slice
 * @description: 初始化配置文件
 * @author: Mr.Ao
 * @create: 2019-03-01 15:56
 **/
public class InitConfiguration {

    public ConfBean confBean;
    public FullExtent fullExtent;
    public static final InitConfiguration INSTANCE= new InitConfiguration();

    public static InitConfiguration getInstance(){
        return INSTANCE;
    }

    private InitConfiguration(){
        InputStream in = LoadConfImpl.class.getClassLoader().getResourceAsStream("customMap.xml");
        SAXBuilder sax = new SAXBuilder();
        Document doc;
        try {
            doc = sax.build(in);
            Element root = doc.getRootElement();
            confBean = LoadConfImpl.getInstance().loadConf(root);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfBean getConfBean() {
        return confBean;
    }

    public void setConfBean(ConfBean confBean) {
        this.confBean = confBean;
    }
}
