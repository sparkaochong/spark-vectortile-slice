package com.ac

import java.sql.{DriverManager, PreparedStatement}

import com.xian80.Impl.VectorTileToolsImpl
import org.apache.spark.{Partition, SparkContext}
import org.apache.spark.rdd.{CustomizedJdbcPartition, CustomizedJdbcRDD}

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-02-26 15:02
  **/
object Util {
  def getRawData(sc: SparkContext): CustomizedJdbcRDD[Array[Object]] = {
    val data: CustomizedJdbcRDD[Array[Object]] = new CustomizedJdbcRDD(sc,
      //创建获取JDBC连接函数
      () => {
        DriverManager.getConnection("jdbc:postgresql://192.168.35.127:5432/test_gis", "postgres", "abc123")
      },
      //设置查询SQL
      "select id, layer_name, st_astext(geom) geom from hz_building h where st_intersects(st_geomfromtext(?, 2385),h.geom) limit 40000",
      //创建分区函数
      () => {
        //POLYGON ((502167.1699999999 3337913.789999999, 502167.1699999999 3357653.5, 524017.6799999997 3357653.5, 524017.6799999997 3337913.789999999, 502167.1699999999 3337913.789999999))
        //POLYGON ((307343.56 3004501.42, 307343.56 3451319.45, 767268.15 3451319.45, 767268.15 3004501.42, 307343.56 3004501.42))  building_area_copy
        //POLYGON((502167.1699999999 3357653.5,524017.6799999997 3357653.5,524017.6799999997 3337913.789999999,502167.1699999999 3337913.789999999,502167.1699999999 3357653.5))  hz_building
        val str = "POLYGON ((502167.1699999999 3337913.789999999, 502167.1699999999 3357653.5, 524017.6799999997 3357653.5, 524017.6799999997 3337913.789999999, 502167.1699999999 3337913.789999999))";
        val tiles: java.util.List[String] = new VectorTileToolsImpl().getPolygonStr(str,2)

        val partitions = new Array[Partition](tiles.size())
        var parameters0 = Map[String, Object]()

        parameters0 += ("1" -> tiles.get(0))
        val partition0 = new CustomizedJdbcPartition(0, parameters0)
        partitions(0) = partition0

        parameters0 += ("1" -> tiles.get(1))
        val partition1 = new CustomizedJdbcPartition(1, parameters0)
        partitions(1) = partition1

        parameters0 += ("1" -> tiles.get(2))
        val partition2 = new CustomizedJdbcPartition(2, parameters0)
        partitions(2) = partition2

        parameters0 += ("1" -> tiles.get(3))
        val partition3 = new CustomizedJdbcPartition(3, parameters0)
        partitions(3) = partition3

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
}
