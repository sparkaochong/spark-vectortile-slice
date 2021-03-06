package com.ac

import java.io.{BufferedOutputStream, File, FileOutputStream}
import java.util

import com.conf.InitConfiguration
import com.conf.model.FullExtent
import com.util.Util
import com.xian80.Impl.VectorTileToolsImpl
import no.ecc.vectortile.VectorTileEncoder
import org.apache.hadoop.hbase.client.{ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.log4j.{LogManager, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.io.WKTReader
import com.ac.PolygonInfo

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-02-25 11:01
  **/
object SingleLevel2Hbase {
  private val confBean = InitConfiguration.getInstance().getConfBean

  def main(args: Array[String]): Unit = {

    val startTimeMillis = System.currentTimeMillis()
    val path = confBean.getOutputInfos.get(0).getPath

    val conf = new SparkConf()
    conf.set("spark.seriallizer", "org.apache.spark.seriallizer.KryoSeriallizer")
    conf.set("spark.kryo.registrator", "com.ac.kryo.MyRegistrator")
    conf.set("spark.kryoseriallizer.buffer.max.mb", "512")

    val spark =SparkSession.builder()
      .appName("SingleLevel")
      .config(conf)
      .master("local[8]")
      .getOrCreate()

    import scala.collection.JavaConversions._

    val level = 10
    val tasks = Util.getPolygonStr(confBean,level)

    for(x <- 0 to tasks.size()-1){
      val dataRDD = Util.getRawData(spark.sparkContext,tasks.get(x),level).map(line =>{
        val geom = new WKTReader().read(line(2).toString)
        PolygonInfo(Integer.parseInt(line(0).toString),line(1).toString,geom)
      }).flatMap(polygonInfo =>{
        val tiles = new VectorTileToolsImpl().getColRows(polygonInfo.polygon,level)
        tiles.map((_,polygonInfo))
      }).groupByKey().flatMap{case (tile,polygonInfoIter) =>
        val vte = new VectorTileEncoder(4096, 16, false)
        val xy = tile.split("_")
        polygonInfoIter.foreach { case polygonInfo =>
          val tempGeom = polygonInfo.polygon.copy()
          new VectorTileToolsImpl().convert2Pixel(tempGeom, xy(0).toInt, xy(1).toInt, level)
          val attributes = new java.util.HashMap[String, String]()
          attributes.put("id", polygonInfo.id.toString)
          attributes.put("name", polygonInfo.layerName)
          vte.addFeature("hz_building", attributes, tempGeom)
        }
        val encodedTile = vte.encode()
        if (encodedTile.length > 0) Some((tile, encodedTile)) else None
      }.foreachPartition { iterator =>
        val conf = HBaseConfiguration.create()
        conf.set("hbase.client.keyvalue.maxsize","20971520")
        conf.set("hbase.zookeeper.quorum", "master,slave1,slave2")
        val conn = ConnectionFactory.createConnection(conf)
        val table = conn.getTable(TableName.valueOf("hz_building"))
        val puts = new util.ArrayList[Put]()
        iterator.foreach {case (tile, encodedTile) =>
          println(encodedTile)
          val put = new Put(Bytes.toBytes(s"${tile.reverse}_${0}"))
          put.addColumn(Bytes.toBytes("f"), null, encodedTile)
          puts.add(put)
        }
        table.put(puts)
        table.close()
        conn.close()
      }

    }

    spark.stop()
    println("程序运行时间：" + (System.currentTimeMillis()- startTimeMillis) + "毫秒!")
  }
}
