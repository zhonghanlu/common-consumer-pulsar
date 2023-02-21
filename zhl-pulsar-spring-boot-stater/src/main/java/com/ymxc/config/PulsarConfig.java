package com.ymxc.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * pulsarAdmin
 * pulsarAdmin
 * 通用配置
 */
@Data
@Component
public class PulsarConfig {

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
