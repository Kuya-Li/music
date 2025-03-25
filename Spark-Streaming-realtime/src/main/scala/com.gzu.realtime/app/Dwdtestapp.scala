package com.gzu.realtime.app

import io.netty.handler.codec.mqtt.MqttMessageBuilders.subscribe
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.codehaus.commons.compiler.java8.java.util.function.Consumer
object Dwdtestapp {
  def main(args: Array[String]): Unit = {
    //1. 准备实时环境
    val sparkConf: SparkConf = new SparkConf().setAppName("dwd_user_app").setMaster("local[3]")
    val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint("./ckp")


    //2.加载数据
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "spark101:9092,spark102:9092,spark103:9092", //kafka集群
      "key.deserializer" -> classOf[StringDeserializer], //key的反序列化规则
      "value.deserializer" -> classOf[StringDeserializer], //value的反序列化规则
      "group.id" -> "spark_demo", //消费者组名称
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (true: java.lang.Boolean) //是否自动提交offset
    )
     //从Kafka中消费处理
    val topics = Array("first") //订阅的主题
    val kafkaDS:InputDStream[ConsumerRecord[String,String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
    )
    //3.处理数据
   val infoDS: DStream[String]= kafkaDS.map(record=>{
      val topic:String = record.topic()
      val partition:Int = record.partition()
      val offset:Long = record.offset()
      val key:String=record.key()
      val value:String = record.value()
      val info:String =s"""topic:${topic},partition:${partition},offset:${offset},key:${key},value:${value}"""
      info
    }
   )
     //4.输出数据
    infoDS.print()
    //5.启动并等待
    ssc.start()
    ssc.awaitTermination()

    //6.关闭资源
    ssc.stop(stopSparkContext = true,stopGracefully = true)

  }
}
