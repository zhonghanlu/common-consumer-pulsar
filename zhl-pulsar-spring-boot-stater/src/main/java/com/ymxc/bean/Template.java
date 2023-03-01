package com.ymxc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * pulsarClient 属性注入类
 *
 *  自行配置处理
 *
 * * “serviceUrl” : “pulsar://localhost:6650”, //broker集群地址
 * * “operationTimeoutMs” : 30000, //操作超时设置
 * * “statsIntervalSeconds” : 60, //设置每个统计信息之间的间隔（默认值：60秒）统计信息将以正值激活状态间隔秒数应设置为至少1秒
 * * “numIoThreads” : 1,//设置用于处理与broker的连接的线程数（默认值：1个线程）
 * * “numListenerThreads” : 1,// 设置要用于消息侦听器的线程数（默认值：1个线程）
 * * “connectionsPerBroker” : 1, //设置客户端库将向单个broker打开的最大连接数。
 * * “useTcpNoDelay” : true, //配置是否在连接上使用延迟tcp,默认为true。无延迟功能确保数据包尽快发送到网络上，实现低延迟发布至关重要。另一方面，发送大量的小数据包可能会限制整体吞吐量。
 * * “useTls” : false, // 启用ssl,在serviceurl中使用“pulsar+ssl://”启用
 * * “tlsTrustCertsFilePath” : “”,//设置受信任的TLS证书文件的路径
 * * “tlsAllowInsecureConnection” : false, //配置pulsar客户端是否接受来自broker的不受信任的TLS证书（默认值：false）
 * * “tlsHostnameVerificationEnable” : false,//它允许在客户端通过TLS连接到代理时验证主机名验证
 * * “concurrentLookupRequest” : 5000,//允许在每个broker连接上发送的并发查找请求数，以防止代理过载。
 * * “maxLookupRequest” : 50000,//为防止broker过载，每个broker连接上允许的最大查找请求数。
 * * “maxNumberOfRejectedRequestPerConnection” : 50,//设置在特定时间段（30秒）内被拒绝的broker请求的最大数目，在此时间段后，当前连接将关闭，客户端将创建一个新连接，以便有机会连接其他broker（默认值：50）
 * * “keepAliveIntervalSeconds” : 30 //为每个客户端broker连接设置以秒为单位的心跳检测时间
 *
 */
@Data
@Component
@Configurable
@ConfigurationProperties(prefix = "ymxc-pulsar.template")
public class Template {

    /**
     * name
     */
    private String templateName;

    /**
     * client地址
     */
    private String serviceUrl;

    /**
     * 配置是否在连接上使用延迟tcp,默认为true。无延迟功能确保数据包尽快发送到网络上，
     * 实现低延迟发布至关重要。另一方面，发送大量的小数据包可能会限制整体吞吐量。
     */
    private boolean enableTcpNoDelay;

    /**
     * //设置用于处理与broker的连接的线程数（默认值：1个线程）
     */
    private int ioThreads;


    /**
     * 1,// 设置要用于消息侦听器的线程数（默认值：1个线程）
     */
    private int listenerThreads;
}
