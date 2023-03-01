package com.ymxc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * * “topicNames” : [ ], //消费者订阅的主题
 * * “topicsPattern” : null, //指定此使用者将订阅的主题的模式。它接受正则表达式，并将在内部编译为模式。例如：“persistent://prop/use/ns abc/pattern topic-.*”
 * * “subscriptionName” : “my-subscription”, //消费者的订阅名
 * * “subscriptionType” : “Exclusive”,//选择订阅主题时要使用的订阅类型。 Exclusive 独占；Failover 故障转移 ；Shared 共享
 * * “receiverQueueSize” : 3,//设置消费者接收队列的大小。
 * * “acknowledgementsGroupTimeMicros” : 100000, //按指定时间对消费者分组
 * * “maxTotalReceiverQueueSizeAcrossPartitions” : 10, //设置跨分区的最大总接收器队列大小
 * * “consumerName” : “my-consumer”, //消费者的名字
 * * “ackTimeoutMillis” : 10000,//设置未确认消息的超时
 * * “priorityLevel” : 0, //为共享订阅使用者设置优先级级别，broker 在调度消息时向其提供更高的优先级。
 * * “cryptoFailureAction” : “FAIL”,//为失效的消费者指定一个默认的特定值
 * * “properties” : { }, //设置属性值
 * * “readCompacted” : false, //如果启用，消费者将从压缩的主题中读取消息，而不是读取主题的完整消息积压。
 * * “subscriptionInitialPosition” : “Latest”, //设置消费者的订阅初始位置 Earliest 从最早的位置，即第一条消息。 Latest 从最后的位置，即最后一条消息。
 * * “patternAutoDiscoveryPeriod” : 1, //为主题消费者使用模式时设置主题自动发现周期。
 * * “subscriptionTopicsMode” : “PERSISTENT”,//确定此消费者应订阅哪些主题-持久性主题、非持久性主题或两者都应订阅。
 * * “deadLetterPolicy” : null //死信策略 为消费者设置死信策略，某些消息将尽可能多次重新传递。通过使用死信机制，消息将具有最大重新传递计数，当消息超过最大重新传递数时，消息将发送到死信主题并自动确认。您可以通过设置死信策略来启用死信机制。
 *
 */
@Data
@Component
@Configurable
@ConfigurationProperties(prefix = "ymxc-pulsar.consumer")
public class Consumer {

    /**
     * 消费者订阅名
     */
    private String subscriptionName;


    /**
     * 选择订阅主题时要使用的订阅类型。 Exclusive 独占；Failover 故障转移 ；Shared 共享
     */
    private String subscriptionType;

    /**
     * 设置消费者接收队列的大小。
     */
    private int receiverQueueSize;

    /**
     * 消费者的名字
     */
    private String  consumerName;

    /**
     * “Latest”, //设置消费者的订阅初始位置 Earliest 从最早的位置，即第一条消息。 Latest 从最后的位置，即最后一条消息。
     */
    private String  subscriptionInitialPosition;
}
