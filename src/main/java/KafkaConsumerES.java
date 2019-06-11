import config.LoadCluster;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerES {

    private final static String TOPIC = "index_name";
    private final static String INDEX = "person";

    public static void main(String[] args) throws IOException {
/**
 * Kafka 配置部分
 */
        Properties props = new Properties();
        // 服务器ip:端口号，集群用逗号分隔
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LoadCluster.load_value("application.properties","kafka_cluster"));
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put("group.id", "test-consumer-group");
        // test-consumer-group
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC));

/**
 * ES 配置部分
 *
 */
        RestClient restClient = RestClient.builder(
                new HttpHost(LoadCluster.load_value("application.properties","eshost1"), Integer.parseInt(LoadCluster.load_value("application.properties","esport1")), "http"),
                new HttpHost(LoadCluster.load_value("application.properties","eshost2"), Integer.parseInt(LoadCluster.load_value("application.properties","esport2")),"http"),
                new HttpHost(LoadCluster.load_value("application.properties","eshost3"), Integer.parseInt(LoadCluster.load_value("application.properties","esport3")),"http")).build();
        Request request = new Request("POST", INDEX+"/_doc");
//      endpoint: {index}/_update/{id}


        /**
         * 消费数据部分
         */
        int count = 1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                // 数据打印测试
//                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
//
                // 数据写入 ES
                request.setJsonEntity(record.value());
                Response response = restClient.performRequest(request);
//                RequestLine requestLine = response.getRequestLine();
                int statusCode = response.getStatusLine().getStatusCode();
//
                // 获取状态信息  打印状态码
                if(count%100 == 0){
                    System.out.println(count + " 状态码 : " + statusCode);
                }
//                if (count%100 == 0)
//                    System.out.println(count);
                count++;



//                System.out.println(requestLine);
//                HttpHost host = response.getHost();
//                System.out.println(host);

//                Header[] headers = response.getHeaders();
//                for(int i = 0; i < headers.length; i++){
//                    System.out.println(headers[i]);
//                }
//                String responseBody = EntityUtils.toString(response.getEntity());
//                System.out.println(responseBody);
            }
        }
    }




}