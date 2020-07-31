package com.kafka.origin;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;


/**
 *   kafka下载和安装  http://mirrors.hust.edu.cn/apache/kafka/0.9.0.0/
 *   http://czj4451.iteye.com/blog/2041096
 *   window(在当前安装目录的CMD下) 启动：
 bin\windows\zookeeper-server-start.bat config\zookeeper.properties
 bin\windows\kafka-server-start.bat config\server.properties
 */
public class KafkaProducer{
    private final Producer<String, String> producer;
    public final static String TOPIC = "mvl";

    private KafkaProducer(){
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "127.0.0.1:9092");

        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        /**
         0：这意味着生产者producer不等待来自broker同步完成的确认继续发送下一条（批）消息。此选项提供最低的延迟但最弱的耐久性保证（当服务器发生故障时某些数据会丢失，如leader已死，但producer并不知情，发出去的信息broker就收不到）。
         1：这意味着producer在leader已成功收到的数据并得到确认后发送下一条message。此选项提供了更好的耐久性为客户等待服务器确认请求成功（被写入死亡leader但尚未复制将失去了唯一的消息）。
         -1：这意味着producer在follower副本确认接收到数据后才算一次发送完成。 此选项提供最好的耐久性，我们保证没有信息将丢失，只要至少一个同步副本保持存活。三种机制，性能依次递减 (producer吞吐量降低)，数据健壮性则依次递增。
          **/
        props.put("request.required.acks","-1");

        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 100;
        final int COUNT = 1000;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
            System.out.println(data);
            messageNo ++;
        }
    }
    //http://www.open-open.com/lib/view/open1412991579999.html
    public static void main( String[] args )
    {
        new KafkaProducer().produce();
    }
}