package com.ymxc.listener;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.DeadLetterPolicy;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 初始化Consumer的配置参数
 * @author winfun
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Component
public @interface PulsarListener {

    /**
     * 数据源名称，默认："default"
     */
    String sourceName() default "default";
    /**
     * 是否持久化
     */
    boolean persistent() default true;
    /**
     * 租户
     */
    String tenant() default "";
    /**
     * 命名空间
     */
    String namespace() default "";
    /**
     * 监听topic
     */
    String[] topics() default {};

    /**
     * 接收消息的队列大小
     */
    int receiverQueueSize() default 1000;

    /**
     * 订阅名称
     */
    String subscriptionName() default "";

    /**
     * 订阅模式
     */
    SubscriptionType subscriptionType() default SubscriptionType.Shared;

    /**
     * 应答超时事件，单位毫秒
     * @see Consumer#acknowledge(Message)
     */
    String ackTimeout() default "1000";

    /**
     * 重新投递时延，单位毫秒
     * @see Consumer#negativeAcknowledge(Message) 
     */
    String negativeAckRedeliveryDelay() default "1000";

    /**
     * 是否开启重试，默认false
     */
    boolean enableRetry() default false;

    /**
     * 最大重新投递次数，超过此次数，进入死信队列
     * @see DeadLetterPolicy
     */
    int maxRedeliverCount() default 2;

    /**
     * 重试队列
     * @see DeadLetterPolicy
     */
    String retryLetterTopic() default "";

    /**
     * 死信队列
     * @see DeadLetterPolicy
     */
    String deadLetterTopic() default "";

    String subscriptionInitialPosition() default "Earliest";

    /**
     * 消费线程池
     */
    ThreadPool threadPool() default @ThreadPool;
}
