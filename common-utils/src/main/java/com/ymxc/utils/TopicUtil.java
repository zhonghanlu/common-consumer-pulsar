package com.ymxc.utils;

import com.ymxc.constant.PulsarConstant;

import java.util.StringJoiner;

/**
 * 主题工具类
 **/
public final class TopicUtil {

    private TopicUtil() {
    }

    /**
     * 拼接topic
     *
     * @return 完整topic路径
     */
    public static String generateTopic(Boolean persistent, String tenant, String namespace, String topic) {

        StringJoiner stringJoiner = new StringJoiner(PulsarConstant.PATH_SPLIT);
        stringJoiner.add(tenant).add(namespace).add(topic);
        if (Boolean.TRUE.equals(persistent)) {
            return PulsarConstant.PERSISTENT + stringJoiner.toString();
        } else {
            return PulsarConstant.NON_PERSISTENT + stringJoiner.toString();
        }
    }
}
