package com.ymxc.service;

import com.ymxc.template.PulsarProducerService;
import org.apache.pulsar.client.api.ClientBuilder;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试发送消息
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    PulsarProducerService producerService;

    @org.junit.Test
    public void test01() throws PulsarClientException {

        producerService.createBuilder()
                .topic("dev-topic")
                .sendAsync("你好我是第一条api测试数据");

//        //1. 获取pulsar的客户端对象
//        ClientBuilder clientBuilder = PulsarClient.builder();
//        clientBuilder.serviceUrl("pulsar://node1:6650,node2:6650,node3:6650");
//        PulsarClient client = clientBuilder.build();
//        //2. 通过客户端创建生产者的对象
//        Producer<byte[]> producer = client.newProducer()
//                .topic("persistent://dev-tenant/dev-namespace/dev-topic")
//                .create();
//        //3. 发送消息:
//        producer.sendAsync("你好 Pulsar...".getBytes());
//        // 如果采用异步发送数据, 由于需要先放置在缓存区中, 如果立即关闭, 会导致 无法发送
//        client.close();

    }

}
