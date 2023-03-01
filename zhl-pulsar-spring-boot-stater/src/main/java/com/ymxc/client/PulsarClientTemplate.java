package com.ymxc.client;

import com.ymxc.bean.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

/**
 * 初始化Pulsar客户端对象
 * 进行发送消息，处理消息
 */
@Slf4j
@Component
public class PulsarClientTemplate extends HashMap<String, PulsarClient> implements DisposableBean {

    public PulsarClientTemplate(Template template) {

        if (Objects.nonNull(template)) {
            try {
                PulsarClient client = PulsarClient.builder().serviceUrl(template.getServiceUrl())
                        .enableTcpNoDelay(template.isEnableTcpNoDelay())
                        //.operationTimeout(e.get, TimeUnit.SECONDS)
                        .listenerThreads(template.getListenerThreads())
                        .ioThreads(template.getIoThreads())
                        .build();
                put(template.getTemplateName(), client);
                log.error("pulsarClient 构建成功");
            } catch (PulsarClientException ex) {
                log.error("pulsarClient 构建失败【{}】", ex);
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        this.values().forEach(e -> {
            try {
                e.close();
                log.info("[Pulsar] 客户端关闭成功");
            } catch (PulsarClientException ee) {
                log.error("[Pulsar] 客户端关闭失败", ee);
            }
        });
    }
}
