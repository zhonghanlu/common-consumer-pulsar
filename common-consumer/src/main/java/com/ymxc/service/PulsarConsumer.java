package com.ymxc.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PulsarConsumer {
 
    // Pulsar服务serviceURL
    @Value("${pulsar.url}")
    private String serviceUrl;
 
    // topic
    @Value("${pulsar.topic}")
    private String topic;
 
    // 订阅名
    @Value("${pulsar.subscription}")
    private String subscription;
 
    // PulsarClient
    private PulsarClient client;
 
    // 消费者
    private Consumer<byte[]> consumer = null;
 
    @Resource
    private PulsarConsumerListener pulsarConsumerListener;


    @PostConstruct
    public void initPulsarConsumer() throws PulsarClientException {
        // 一个进程一个PulsarClient，一个PulsarClient下可以创建多个生产和消费者
        client = PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .ioThreads(5) //设置用于处理与broker的连接的线程数（默认值：1个线程）
                .listenerThreads(20) //设置要用于消息侦听器的线程数（默认值：1个线程）
                .connectionsPerBroker(2) //设置客户端库将向单个broker打开的最大连接数。
                .build();
        log.info("消费客户端构建完成======》");
        // 创建消费者consumer
        consumer = client.newConsumer()
                .topic(topic)
                .subscriptionName(subscription)
                .subscriptionType(SubscriptionType.Exclusive)
                .receiverQueueSize(5000) //设置消费者接收队列的大小。
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .negativeAckRedeliveryDelay(60, TimeUnit.SECONDS)
                .batchReceivePolicy(BatchReceivePolicy.builder()
                        .maxNumMessages(1000)
                        .timeout(2,TimeUnit.SECONDS)
                        .build())
                .enableBatchIndexAcknowledgment(true)
 
                /** 这里开启消息监听 */
                .messageListener(pulsarConsumerListener)

                .subscribe();
    }


}
