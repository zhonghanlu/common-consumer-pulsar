package com.ymxc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 生产者
 * * “topicName” : “persistent://public/pulsar-cluster/default/my-topic”,
 * //topicName 由四部分组成 [topic类型://租户名/命名空间/主题名]
 * * “producerName” : “my-producer”, //生产者名称
 * * “sendTimeoutMs” : 30000, //发送超时时间，默认 30s
 * * “blockIfQueueFull” : false, //消息队列已满时是否阻止发送操作 默认false,当消息队列满，发送操作将立即失败
 * * “maxPendingMessages” : 1000,//设置等待接收来自broker的确认消息的队列的最大大小,队列满试,blockIfQueueFull=true才有效
 * * “maxPendingMessagesAcrossPartitions” : 50000,//设置所有分区的最大挂起消息数
 * * “messageRoutingMode” : “CustomPartition”, //消息分发路由模式 CustomPartition；RoundRobinPartition 环形遍历分区；SinglePartition
 * 随机选择一个分区 //参考http://pulsar.apache.org/docs/zh-CN/2.2.0/cookbooks-partitioned/
 * * “hashingScheme” : “JavaStringHash”,//更改用于选择在何处发布特定消息的分区的哈希方案
 * * “cryptoFailureAction” : “FAIL”,//为失效的生产者指定一个默认的特定值
 * * “batchingMaxPublishDelayMicros” : 1000,//设置发送的消息将被成批处理的时间段默认值：如果启用了成批消息，则为1毫秒。
 * * “batchingMaxMessages” : 1000, //设置批处理中允许的最大消息数
 * * “batchingEnabled” : true, //控制是否为生产者启用消息的自动批处理。
 * * “compressionType” : “NONE”, //设置生产者的压缩类型
 * * “initialSequenceId” : null, //为生产者发布的消息设置序列ID的基础值
 * * “properties” : { } //为生产者设置属性
 */

@Data
@Component
@Configurable
@ConfigurationProperties(prefix = "ymxc-pulsar.producer")
public class Producer {

    /**
     * 租户
     */
    private String tenant;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 生产者名称
     */
    private String producerName;

    /**
     * 发送超时时间
     */
    private int sendTimeoutMs;

    /**
     * 队列满了是否继续发送
     */
    private boolean blockIfQueueFull;

    /**
     * 设置等待接收来自broker的确认消息的队列的最大大小,队列满试,blockIfQueueFull=true才有效
     */
    private int maxPendingMessages;

    /**
     * 设置所有分区的最大挂起消息数
     */
    private int maxPendingMessagesAcrossPartitions;

    /**
     * “CustomPartition”, //消息分发路由模式 CustomPartition；RoundRobinPartition 环形遍历分区；SinglePartition
     * 随机选择一个分区 //参考http://pulsar.apache.org/docs/zh-CN/2.2.0/cookbooks-partitioned/
     */
    private String messageRoutingMode;

    /**
     * 设置发送的消息将被成批处理的时间段默认值：如果启用了成批消息，则为1毫秒。
     */
    private int batchingMaxPublishDelayMicros;

    /**
     * 设置批处理中允许的最大消息数
     */
    private int batchingMaxMessages;

    /**
     * 控制是否为生产者启用消息的自动批处理。
     */
    private boolean batchingEnabled;

}
