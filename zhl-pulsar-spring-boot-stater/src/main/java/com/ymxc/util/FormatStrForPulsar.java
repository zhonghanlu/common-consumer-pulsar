package com.ymxc.util;

import com.ymxc.constant.PulsarConstant;

public class FormatStrForPulsar{

    private  static final StringBuffer buffer = new StringBuffer();

    public static String append(String tenantName,String namespaceName){
        return buffer.append(tenantName)
                .append("/")
                .append(namespaceName).toString();
    }

    public static String appendNoPa(String tenantName, String namespaceName,
                                    String topicName,boolean isPerFlag) {
        if (!isPerFlag) {
            buffer.append(PulsarConstant.NON_PERSISTENT);
        }else {
            buffer.append(PulsarConstant.PERSISTENT);
        }
        buffer.append(tenantName)
                .append("/")
                .append(namespaceName)
                .append("/")
                .append(topicName);
        return buffer.toString();
    }
}
