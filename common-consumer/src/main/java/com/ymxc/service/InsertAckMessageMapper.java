package com.ymxc.service;

import com.ymxc.bean.PulsarMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InsertAckMessageMapper {

    @Insert({"<script>" +
            "INSERT INTO pulsar_message " +
            "values  " +
            "<foreach collection=\"pulsarMessages\" item=\"item\" separator=\",\">" +
            "(#{item.id}, #{item.value})" +
            "</foreach >" +
            "</script>"})
    void insertValue(@Param("pulsarMessages") List<PulsarMessage> pulsarMessages);

}
