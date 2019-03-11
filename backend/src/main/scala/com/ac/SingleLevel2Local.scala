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

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-02-25 11:01
  **/
object SingleLevel2Local {
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
      }.foreach { case (tile, encodedTile) =>
        try {
          val pbfFile = new File(path + File.separator + level)
          //如果文件夹目录不存在，就创建
          if (!pbfFile.exists()) {
            pbfFile.mkdirs()
          }
          val bos = new BufferedOutputStream(new FileOutputStream(path + level + File.separator + tile + ".pbf"))
          bos.write(encodedTile)
          System.out.println(Thread.currentThread().getName + " 写入文件: " + tile + ".pbf")
          bos.flush()
          bos.close()
        } catch {
          case e: Exception =>
            Console.err.println(s"error: ${e}")
        }
      }
    }

    spark.stop()
    println("程序运行时间：" + (System.currentTimeMillis()- startTimeMillis) + "毫秒!")
  }
}
