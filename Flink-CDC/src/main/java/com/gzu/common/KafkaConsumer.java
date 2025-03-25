package com.gzu.common;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * Description:KafKa消费者案例
 * Author: SuWenJay
 * Date: 2023-08-17
 */

public class KafkaConsumer {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // kafka消费者配置
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "spark101:9092");
        properties.setProperty("group.id", "my-consumer-group");

        // 创建kafka消费者
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "ods_db",         // Kafka topic name
                new SimpleStringSchema(), // Deserialization schema
                properties
        );

        // 添加Kafka Consumer作为数据源
        DataStream<String> stream = env.addSource(kafkaConsumer);

        //处理并进一步转换消费数据

        //例如，你可以在这里应用额外的转换



        //输出消耗的数据
        stream.print();

        // 提交flink job
        env.execute("Kafka Consumer Example");
    }
}
