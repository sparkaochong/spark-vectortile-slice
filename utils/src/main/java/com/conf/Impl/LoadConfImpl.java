package com.conf.Impl;

import com.conf.LoadConf;
import com.conf.model.*;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spark-vectortile-slice
 * @description: 配置文件加载工具类
 * @author: Mr.Ao Mr.Yang
 * @create: 2019-02-22 15:24
 **/
public class LoadConfImpl implements LoadConf {

    private static final Logger LOG = Logger.getLogger(LoadConfImpl.class);
    public static final LoadConfImpl INSTANCE = new LoadConfImpl();

    private LoadConfImpl(){}

    public static LoadConfImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public ConfBean loadConf(Element node) {
        LOG.info("当前配置文件名称：" + node.getName() + ".xml");
        List<Element> confChilds = node.getChildren();
        ConfBean confBean = new ConfBean();
        for(Element confChild: confChilds){
            String childName = confChild.getName();
            if("tileInfo".equals(childName)){
                TileInfo tileInfo = injectTileInfo(confChild);
                confBean.setTileInfo(tileInfo);
            }else if("dataSources".equals(childName)){
                List<DataSourceInfo> dataSourceInfos = injectDataSourceInfo(confChild);
                confBean.setDataSourceInfos(dataSourceInfos);
            }else if("layers".equals(childName)){
                List<LayerInfo> layerInfos = injectLayerInfo(confChild);
                confBean.setLayerInfos(layerInfos);
            }else if("outputs".equals(childName)){
                List<OutputInfo> outputInfos = injectOutputInfo(confChild);
                confBean.setOutputInfos(outputInfos);
            }
        }
        return confBean;
    }

    @Override
    public List<DataSourceInfo> injectDataSourceInfo(Element node) {
        List<DataSourceInfo> dataSourceInfos = new ArrayList<DataSourceInfo>();
        try{
            for(Element dataSource: node.getChildren()){
                String id = dataSource.getChild("id").getValue();
                String name = dataSource.getChild("name").getValue();
                String type = dataSource.getChild("type").getValue();
                String subtype = dataSource.getChild("subtype").getValue();
                String driverClassName = dataSource.getChild("description").getChild("driverClassName").getValue();
                String driverUrl = dataSource.getChild("description").getChild("url").getValue();
                String userName = dataSource.getChild("description").getChild("userName").getValue();
                String passWord = dataSource.getChild("description").getChild("passWord").getValue();
                DataSourceInfo dataSourceInfo = new DataSourceInfo(id,name,type,subtype,driverClassName,driverUrl,userName,passWord);
                dataSourceInfos.add(dataSourceInfo);
            }
        }catch(Exception e){
            LOG.info("配置文件customMap.xml的table标签内容配置错误,(提示!可能有标签内容为空)");
            e.printStackTrace();
        }
        return dataSourceInfos;
    }

    @Override
    public List<LayerInfo> injectLayerInfo(Element node) {
        List<LayerInfo> layerInfos = new ArrayList<LayerInfo>();
        try{
            for(Element layer: node.getChildren()){
                int id = Integer.parseInt(layer.getChild("id").getValue());
                String dataSourceID = layer.getChild("dataSourceID").getValue();
                int wkid = Integer.parseInt(layer.getChild("wkid").getValue());
                String layerName = layer.getChild("layerName").getValue();
                int minScale = Integer.parseInt(layer.getChild("minScale").getValue());
                int maxScale = Integer.parseInt(layer.getChild("maxScale").getValue());
                String dataDesc = layer.getChild("dataDesc").getValue();

                LayerInfo layerInfo = new LayerInfo(id,dataSourceID,wkid,layerName,minScale,maxScale,dataDesc);
                layerInfos.add(layerInfo);
            }
        }catch(Exception e){
            LOG.info("配置文件customMap.xml的table标签内容配置错误,(提示!可能有标签内容为空)");
            e.printStackTrace();
        }
        return layerInfos;
    }

    @Override
    public List<OutputInfo> injectOutputInfo(Element node) {
        List<OutputInfo> outputInfos = new ArrayList<OutputInfo>();
        try{
            for(Element output: node.getChildren()){
                int id = Integer.parseInt(output.getChild("id").getValue());
                String outputType = output.getChild("type").getValue();
                String path = output.getChild("path").getValue();
                String level = output.getChild("level").getValue();

                OutputInfo outputInfo = new OutputInfo(id,outputType,path,level);
                outputInfos.add(outputInfo);
            }
        }catch(Exception e){
            LOG.info("配置文件customMap.xml的table标签内容配置错误,(提示!可能有标签内容为空)");
            e.printStackTrace();
        }
        return outputInfos;
    }

    @Override
    public TileInfo injectTileInfo(Element node) {
        TileInfo tileInfo = null;
        try{
            int dpi = Integer.parseInt(node.getChild("dpi").getValue());
            double originX = Double.parseDouble(node.getChild("originPoint").getChild("x").getValue());
            double originY = Double.parseDouble(node.getChild("originPoint").getChild("y").getValue());
            int tileSize = Integer.parseInt(node.getChild("tileSize").getValue());
            double baseResolution = Double.parseDouble(node.getChild("baseResolution").getValue());
            String forMat = node.getChild("format").getValue();

            tileInfo = new TileInfo(dpi,originX,originY,tileSize,baseResolution,forMat);
        }catch(Exception e){
            LOG.info("配置文件customMap.xml的table标签内容配置错误,(提示!可能有标签内容为空)");
            e.printStackTrace();
        }
        return tileInfo;
    }}
