package com.gzu.realtime.alg
import org.apache.spark.sql.functions.desc
import org.apache.spark.sql.{Encoders, SaveMode, SparkSession}

object popular_music {
  def main(args: Array[String]): Unit = {


    //1.连接clickhouse读取数据
    //创建SparkSession：在Spark应用程序中，首先创建一个SparkSession对象。通过SparkSession，您可以运行Spark SQL查询和操作。
    val spark = SparkSession.builder()
      .appName("Read ClickHouse data")
      .master("local")
      .getOrCreate()

    //创建ClickHouse连接配置：指定ClickHouse的连接参数，包括ClickHouse的IP地址、端口号、数据库名称、用户名和密码。
    val clickhouseUrl = "jdbc:clickhouse://192.168.43.106:8123/music163"
    val clickhouseUser = "default"
    val clickhousePassword = ""

    //读取ClickHouse表数据：使用SparkSession的read方法读取ClickHouse表的数据。在options中，指定ClickHouse的连接URL、驱动程序和表名称。

    val clickhouseTable = "music_recommend"
    val clickhouseDF = spark.read
      .format("jdbc")
      .option("url", clickhouseUrl)
      .option("driver", "ru.yandex.clickhouse.ClickHouseDriver")
      .option("dbtable", clickhouseTable)
      .option("user", clickhouseUser)
      .option("password", clickhousePassword)
      .load()


    //2.进行数据处理
    // 定义元组类型的编码器
    import spark.implicits._

    // 对clickhouseDF进行map操作
    val mappedDF =clickhouseDF.map(row => {
      // 在这里对每一行进行映射操作
      val musicId = row.getAs[String]("musicId")
      val likes = row.getAs[Long]("likes")
      val collects = row.getAs[Long]("collects")
      val plays = row.getAs[Long]("plays")
      val scores  = likes+collects+plays
      // 返回映射后的结果
      (musicId,scores)
    }).toDF("musicId","scores")


    val sortedDF = mappedDF.orderBy(desc("_2"))


    sortedDF.show()



    //3.将数据写入clickhouse

    sortedDF.write
      .format("jdbc")
      .option("url", clickhouseUrl)
      .option("driver", "ru.yandex.clickhouse.ClickHouseDriver")
      .option("dbtable", "popular_music")
      .option("user", clickhouseUser)
      .option("password", clickhousePassword)
      .mode(SaveMode.Append)
      .save()


  }
}
