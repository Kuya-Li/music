package com.gzu.realtime.app
import com.gzu.realtime.util.MyKafkaUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}


object DwdKafkaApp {
  def main(args: Array[String]): Unit = {

    // 0.准备环境

    val sparkConf: SparkConf = new SparkConf().setAppName("dwd_user_app").setMaster("local[3]")
    val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint("./ckp")

    // 1.从Kafka中消费处理
    val groupId = "spark_demo"
    val topics:String= "first" //订阅的主题
    val kafkaDS: InputDStream[ConsumerRecord[String, String]] =MyKafkaUtils.getKafkaDS(ssc,topics,groupId)

    // 2.处理数据
    val processedStream = kafkaDS
      .map(_.value()) // 获取消息的值
      .map(line => {
        val data: Array[String] = line.split("\\s+") // 以逗号分隔字段
        val musicId = data(2) // 音乐id作为字段索引
//        val userId = data(1) // 用户id作为字段索引
        val like = data(3).toInt // 点赞字段
        val collect = data(4).toInt // 收藏字段
        val play = data(5).toInt // 播放字段
        ((musicId), (like, collect, play)) // 构建键值对，方便之后的累加操作
      })
     .reduceByKey((a,b)=>(a._1 + b._1, a._2 + b._2, a._3 + b._3))

    // 3.存入ClickHouse
    import java.sql.{Connection, DriverManager, PreparedStatement}

    val clickHouseUrl = "jdbc:clickhouse://192.168.43.106:8123/music163"
    val clickHouseUsername = "default"
    val clickHousePassword = ""

    processedStream.foreachRDD { rdd =>
      rdd.foreachPartition { partition =>
        // 创建 ClickHouse 连接
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver")
        val connection: Connection = DriverManager.getConnection(clickHouseUrl, clickHouseUsername, clickHousePassword)

        // 创建预编译语句
        val statement: PreparedStatement = connection.prepareStatement(s"INSERT INTO music_recommend (musicId, likes, collects, plays) VALUES (?, ?, ?, ?)")

        partition.foreach { case (musicId, (likes, collects, plays)) =>
          statement.setString(1, musicId)
          statement.setInt(2, likes)
          statement.setInt(3, collects)
          statement.setInt(4, plays)
          statement.addBatch()
        }

        // 批量执行插入语句
        statement.executeBatch()

        // 关闭连接和语句
        statement.close()
        connection.close()
      }
    }

//    Class.forName("ru.yandex.clickhouse.ClickHouseDriver")
//    val connection: Connection = DriverManager.getConnection(clickHouseUrl, clickHouseUsername, clickHousePassword)
//
//    // 创建预编译语句
//    val statement: PreparedStatement = connection.prepareStatement(s"INSERT INTO $clickHouseTable (musicId, likes, collects, plays, score) VALUES (?, ?, ?, ?, ?)")
//
//    // 处理数据并将结果写入ClickHouse
//    processedStream.foreachRDD { rdd =>
//      rdd.foreach { case (musicId, (likes, collects, plays, score)) =>
//        statement.setString(1, musicId)
//        statement.setInt(2, likes)
//        statement.setInt(3, collects)
//        statement.setInt(4, plays)
//        statement.setFloat(5, score)
//        statement.addBatch()
//      }
//
//      // 批量执行插入语句
//      statement.executeBatch()
//    }
//
//    // 关闭连接和语句
//    statement.close()
//    connection.close()
    //4.输出数据
    processedStream.print(10)
    //5.启动并等待
    ssc.start()
    ssc.awaitTermination()

    //6.关闭资源
    ssc.stop(stopSparkContext = true, stopGracefully = true)

  }

}
