package com.gzu.utils;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.errors.UnknownTopicOrPartitionException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * Description:Kafka 工具类
 * Author: SuWenJay
 * Date: 2023-08-15
 */

public class MyKafkaUtil {
    // Kafka 集群地址
    private final static ResourceBundle resourceBundle = PropertiesUtil.getResourceBundle("config");
    private static final String BOOTSTRAP_SERVERS = PropertiesUtil.getProperty("bootstrap.servers");

    public static FlinkKafkaProducer<String> getKafkaProducer(String topic){

        return new FlinkKafkaProducer<String>(BOOTSTRAP_SERVERS, topic, new SimpleStringSchema());
    }

    // 创建 Kafka 生产者
    public static KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();

        // 配置 Kafka 生产者的属性
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS); // 设置 Kafka 集群地址
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // 设置键的序列化器
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // 设置值的序列化器

        // 创建并返回 Kafka 生产者实例
        return new KafkaProducer<>(props);
    }

    // 创建 Kafka 消费者
    public static KafkaConsumer<String, String> createConsumer(String groupId) {
        Properties props = new Properties();

        // 配置 Kafka 消费者的属性
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS); // 设置 Kafka 集群地址
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId); // 设置消费者组 ID
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // 设置键的反序列化器
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // 设置值的反序列化器

        // 创建并返回 Kafka 消费者实例
        return new KafkaConsumer<>(props);
    }

    // 创建 Kafka 主题
    public static void createTopic(String topicName, int numPartitions, short replicationFactor) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        try (AdminClient adminClient = AdminClient.create(props)) {
            // 创建一个新的主题配置
            NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
            // 使用 AdminClient 创建主题，等待操作完成
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof TopicExistsException) {
                // 如果主题已经存在，打印错误信息
                System.err.println("Topic " + topicName + " already exists.");
            } else {
                // 如果出现其他执行异常，打印堆栈跟踪
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            // 如果操作被中断，打印堆栈跟踪并中断当前线程
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    // 删除 Kafka 主题
    public static void deleteTopic(String topicName) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        try (AdminClient adminClient = AdminClient.create(props)) {
            // 使用 AdminClient 删除主题，等待操作完成
            adminClient.deleteTopics(Collections.singletonList(topicName)).all().get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof UnknownTopicOrPartitionException) {
                // 如果主题不存在，打印错误信息
                System.err.println("Topic " + topicName + " does not exist.");
            } else {
                // 如果出现其他执行异常，打印堆栈跟踪
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            // 如果操作被中断，打印堆栈跟踪并中断当前线程
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
