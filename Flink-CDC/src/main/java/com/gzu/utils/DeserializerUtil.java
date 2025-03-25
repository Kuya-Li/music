package com.gzu.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ververica.cdc.debezium.DebeziumDeserializationSchema;

import io.debezium.data.Envelope;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

/**
 * Description:反序列化工具,方便下游处理
 * Author: SuWenJay
 * Date: 2023-08-16
 */
public class DeserializerUtil implements DebeziumDeserializationSchema<String> {

    /**
     * {
     *   "db" : "",
     *   "tableName: "",
     *   "before: "{"id":"...",}" ,
     *   "after: "{"id":"...",}"  ,
     *   "op": "c/r/u/d"
     */
    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) throws Exception {
        JSONObject result = new JSONObject();

        // 提取主题以获取数据库和表名
        String topic = sourceRecord.topic();
        //将数据库和表面通过.进行分割,需要进行转义
        String[] fields = topic.split("\\.");
        result.put("db", fields[1]);
        result.put("tableName", fields[2]);

        // 提取 op 数据
        Struct value = (Struct) sourceRecord.value();
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);

        // 捕获 before 和after数据
        Struct before = value.getStruct("before");
        Struct after = value.getStruct("after");

        // 为“before”和“after”数据构建JSON对象
        JSONObject beforeJson = extractJsonFromStruct(before);
        JSONObject afterJson = extractJsonFromStruct(after);

        result.put("before", beforeJson);
        result.put("after", afterJson);
        result.put("op", operation);

        // 将结果输出为JSON字符串
        collector.collect(result.toJSONString());
    }


    // 该方法用于从Struct中提取JSON
    private JSONObject extractJsonFromStruct(Struct struct) {
        JSONObject json = new JSONObject();
        if (struct != null) {
            Schema schema = struct.schema();
            List<Field> fieldList = schema.fields();
            for (Field field : fieldList) {
                json.put(field.name(), struct.get(field));
            }
        }
        return json;
    }


    @Override
    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }
}
