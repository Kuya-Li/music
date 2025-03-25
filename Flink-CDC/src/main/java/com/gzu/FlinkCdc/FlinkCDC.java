package com.gzu.FlinkCdc;

import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
import com.alibaba.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.alibaba.ververica.cdc.debezium.DebeziumSourceFunction;
import com.gzu.utils.DeserializerUtil;
import com.gzu.utils.MyKafkaUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Description:
 * Author: SuWenJay
 * Date: 2023-08-17
 */
public class FlinkCDC {
    public static void main(String[] args) throws Exception {
        // 1. 获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1); // 设置并行度为1

        // 2. Flink CDC 构建SourceFunction
        DebeziumSourceFunction<String> sourceFunction = MySQLSource.<String>builder()
                .hostname("spark101")
                .port(3306)
                .username("root")
                .password("123456")
                .databaseList("music163")
                .deserializer(new DeserializerUtil())
                .startupOptions(StartupOptions.latest())
                .build();
        DataStreamSource<String> streamSource = env.addSource(sourceFunction);

        // 3. 打印数据并将数据并生产到Kafka
        streamSource.print();
        String sinkTopic = "first";
        streamSource.addSink(MyKafkaUtil.getKafkaProducer(sinkTopic));

        // 4. 启动任务
        env.execute("FlinkCDC");
    }

}
