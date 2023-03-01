package com.ymxc.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * pulsarAdmin
 * 通用配置
 */
@Data
@Component
@Configurable
@ConfigurationProperties(prefix = "ymxc-pulsar.admin")
public class PulsarAdmin {

    /**
     * pulsar地址
     */
    private String serviceHttpUrl;


    //组相关
    /**
     * pulsar组的归属集群
     */
    private HashSet<String> cluster;

    /**
     * pulsar组的权限
     */
    private Set<String> adminRoles;
}
