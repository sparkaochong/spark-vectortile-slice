import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.sql.SparkSession
import org.datasyslab.geospark.serde.GeoSparkKryoRegistrator
import org.datasyslab.geosparksql.utils.GeoSparkSQLRegistrator

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-02-25 14:06
  **/
object EnvlopeTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.set("spark.serializer", classOf[KryoSerializer].getName)
    conf.set("spark.kryo.registrator", classOf[GeoSparkKryoRegistrator].getName)
    conf.set("spark.seriallizer", "org.apache.spark.seriallizer.KryoSeriallizer")
    conf.set("spark.kryo.registrator", "com.kryo.MyRegistrator")

    val spark = SparkSession.builder()
      .appName("EnvlopeTest")
      .config(conf)
      .master("local[8]")
      .getOrCreate()

    //This function will register GeoSpark User Defined Type, User Defined Function and optimized join query strategy.
    GeoSparkSQLRegistrator.registerAll(spark)

    val properties = new Properties()
    properties.put("user", "postgres")
    properties.put("password", "abc123")

    val readOpts = Map[String, String]("numPartitions" -> "2", "partitionColumn" -> "gid", "lowerBound" -> "2"
      , "upperBound" -> "39646", "fetchsize" -> "1000")
    val jdbcDF = spark.read.options(readOpts).jdbc("jdbc:postgresql://192.168.35.127:5432/test_gis", "hz_building", properties)
    jdbcDF.createTempView("hz_building")

    val rawDF = spark.sql("select gid,id,layer_name,shape_leng,shape_area,ST_GeomFromWKB(geom) AS geom,hight, shape from hz_building")
    rawDF.createOrReplaceTempView("hz_building1")
    val rawDF1 = spark.sql("select ST_Envelope_Aggr(geom) from hz_building1")
    rawDF1.show(false)
  }
}
