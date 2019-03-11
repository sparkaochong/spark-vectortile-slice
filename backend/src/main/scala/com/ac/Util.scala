package com.ac

import java.sql.{DriverManager, PreparedStatement}

import com.conf.InitConfiguration
import com.conf.model.ConfBean
import com.xian80.Impl.VectorTileToolsImpl
import org.apache.spark.rdd.{CustomizedJdbcPartition, CustomizedJdbcRDD}
import org.apache.spark.{Partition, SparkContext}
import org.locationtech.jts.geom.Geometry

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-02-26 15:02
  **/
object Util {

  val confBean:ConfBean = InitConfiguration.getInstance().getConfBean

  def getRawData(sc: SparkContext,geoStr: String,level: Int): CustomizedJdbcRDD[Array[Object]] = {
    val data: CustomizedJdbcRDD[Array[Object]] = new CustomizedJdbcRDD(sc,
      //创建获取JDBC连接函数
      () => {
        DriverManager.getConnection(
          confBean.getDataSourceInfos.get(1).getDriverUrl(),
          confBean.getDataSourceInfos.get(1).getUserName(),
          confBean.getDataSourceInfos.get(1).getPassWord()
        )
      },
      //设置查询SQL
      confBean.getLayerInfos.get(0).getDataDesc,
      //创建分区函数
      () => {
        val list = com.util.Util.factor(confBean.getExtentInfo.getTaskExtent.getTaskNum)
        val tiles: java.util.List[String] = new VectorTileToolsImpl().getWindowPolygonStr(geoStr,list,level)
        var parameters = Map[String, Object]()
        val partitions = new Array[Partition](tiles.size())
        for(x <- 0 to tiles.size()-1){
          parameters += ("1" -> tiles.get(x))
          partitions(x) = new CustomizedJdbcPartition(x,parameters)
        }

        partitions
      },
      //为每个分区设置查询条件(基于上面设置的SQL语句)
      (stmt: PreparedStatement, partition: CustomizedJdbcPartition) => {
        stmt.setString(1, partition.asInstanceOf[CustomizedJdbcPartition].partitionParameters.get("1").get.asInstanceOf[String])
        stmt
      }
    )
    return data
  }

  def getPolygonStr(confBean: ConfBean,level: Int):java.util.List[String] = {
    val initialExtent = confBean.getExtentInfo.getInitialExtent
    val fullExtent = confBean.getExtentInfo.getFullExtent
    val initGeometry: Geometry  = com.util.Util.getPolygon(initialExtent.getXmin, initialExtent.getYmin, initialExtent.getXmax, initialExtent.getYmax)
    val fullGeometry: Geometry = com.util.Util.getPolygon(fullExtent.getXmin, fullExtent.getYmin, fullExtent.getXmax, fullExtent.getYmax)
    val extent: Geometry = com.util.Util.compare(initGeometry, fullGeometry).asInstanceOf[Geometry]
    if(initGeometry.compareTo(extent) == 0){
      return com.util.Util.splitFullExtent(extent,initialExtent.getTaskExtent,level)
    }else{
      return com.util.Util.splitFullExtent(extent,fullExtent.getTaskExtent,level)
    }
  }

  def

}
