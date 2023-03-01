package com.ymxc.base;

import com.ymxc.util.FormatStrForPulsar;
import com.ymxc.webmvc.Restful;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.TenantInfoImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * pulsar基础信息创建
 * 1.用户组
 * 2.命名空间
 * 3.topic的构建
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PulsarAdminApi {

    private PulsarAdmin pulsarAdmin;

    private final com.ymxc.bean.PulsarAdmin admin;

    @PostConstruct
    public void initPulsarAdmin(){
        try {
            pulsarAdmin=PulsarAdmin.builder()
                    .serviceHttpUrl(admin.getServiceHttpUrl())
                    .build();
        } catch (PulsarClientException e) {
            log.error("构建pulsarAdmin异常：【{}】",e);
        }
    }

    /**
     * 创建用户组
     */
    public  void createTenants(String tenantName){
        TenantInfoImpl config = TenantInfoImpl.builder()
                .adminRoles(admin.getAdminRoles())
                .allowedClusters(admin.getCluster())
                .build();
        try {
            pulsarAdmin.tenants().createTenant(tenantName,config);
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * 获得该用户组详细信息
     */
    public Restful getThisTenants(String tenantName){
        try {
          return Restful.SUCCESS().object(pulsarAdmin.tenants().getTenantInfo(tenantName)).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * 获得用户组s
     */
    public Restful getTenants(){
        try {
            return Restful.SUCCESS().object(pulsarAdmin.tenants().getTenants()).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }


    /**
     * 创建命名空间
     */
    public Restful createNamespace(String tenantName,String namespaceName){
        String tnStr = FormatStrForPulsar
                .append(tenantName, namespaceName);
        try {
            pulsarAdmin.namespaces().createNamespace(tnStr);
            return Restful.SUCCESS().object(tnStr).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * 获得该组下的所有命名空间
     */
    public Restful getTenantNamespaces(String tenantName){
        try {
            return Restful.SUCCESS().object(pulsarAdmin.namespaces().getNamespaces(tenantName)).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * 删除此命名空间
     */
    public Restful delNamespaces(String tenantName,String namespaceName){
        String str = FormatStrForPulsar
                .append(tenantName, namespaceName);
        try {
            pulsarAdmin.namespaces().createNamespace(str);
            return Restful.SUCCESS().object(str).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * TODO 配置复制集群
     */

    /**
     * TODO 配置 backlog quota 策略
     */

    /**
     * TODO 配置持久化策略
     */

    /**
     * TODO 配置消息存活时间(TTL)
     */

    /**
     * TODO 配置整个名称空间中Topic的消息发送速率
     */

    /**
     * TODO 配置整个名称空间中Topic的消息接收速率
     */

    /**
     * TODO 配置整个名称空间中Topic的复制集群的速率
     */


    /**
     * 创建topic
     */
    public Restful createTopic(String tenantName,String namespaceName,String topicName
            ,boolean isPaFlag,Integer paNums,boolean isPerFlag){
        try {
            String prefix = FormatStrForPulsar.appendNoPa(tenantName, namespaceName, topicName, isPerFlag);
            // 不分区
            if (!isPaFlag) {
                pulsarAdmin.topics().createNonPartitionedTopic(prefix);
            }
            pulsarAdmin.topics().createPartitionedTopic(prefix,paNums);
            return Restful.SUCCESS().object(prefix).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * 列出命名空间下的所有topic
     */
    public Restful listForNamespaceGetTopics(String tenantName,String namespaceName){
        try {
            String namespace = FormatStrForPulsar.append(tenantName, namespaceName);
            List<String> topics = pulsarAdmin.topics().getList(namespace);
            return Restful.SUCCESS().object(topics).build();
        } catch (PulsarAdminException e) {
            throw new RuntimeException(e);
        }finally {
            pulsarAdmin.close();
        }
    }

    /**
     * TODO 删除topic 分区 持久化
     */


    /**
     * TODO 授权
     */

    /**
     * TODO 获取授权
     */

    /**
     * TODO 取消授权
     */

}
