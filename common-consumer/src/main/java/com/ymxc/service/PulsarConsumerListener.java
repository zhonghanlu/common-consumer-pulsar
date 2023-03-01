package com.ymxc.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Queues;
import com.ymxc.bean.PulsarMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * pulsar 监听
 */
@Slf4j
@Component
public class PulsarConsumerListener implements MessageListener<byte[]> {

    private final SqlSessionFactory sqlSessionFactory;

    private final LinkedBlockingQueue<PulsarMessage> queue = new LinkedBlockingQueue<>(1024);

    public PulsarConsumerListener(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void received(Consumer<byte[]> consumer, Message<byte[]> msg) {
        String data = new String(msg.getData());
        log.info("【consumer1】：收到消息 { topic=\"{}\", key=\"{}\", " + "data=\"{}\"}，over！", msg.getTopicName(), msg.getKey(), data);

        try {
            consumer.acknowledgeCumulativeAsync(msg);
            log.debug("消息ack成功！！！messageId【{}】", msg.getMessageId());
            PulsarMessage message = PulsarMessage.builder()
                    .id(msg.getSequenceId())
                    .value(new String(msg.getData()))
                    .build();
            queue.put(message);
        } catch (Exception e) {
            consumer.negativeAcknowledge(msg);
        }
    }


    /**
     * 三秒处理一次数据
     */
    @Scheduled(fixedDelay = 3000)
    public void execDataFDB() {
        log.info("监听队列中......");
        AtomicBoolean isRunning = new AtomicBoolean(true);
        List<PulsarMessage> list = new LinkedList<>();
        while (isRunning.get()) {
            try {
                Queues.drain(queue, list, 1000, 2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Optional.ofNullable(CollectionUtil.isEmpty(queue)).ifPresent(e -> isRunning.set(false));
            if (CollectionUtil.isNotEmpty(list)) {
                log.info("批量插入，数量为【「」】",list.size());
                this.savaMessage(list);
            }
        }
    }


    @Async
    public void savaMessage(List<PulsarMessage> pulsarMessages) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getMapper(InsertAckMessageMapper.class).insertValue(pulsarMessages);
        sqlSession.commit();
        sqlSession.close();
    }
}
