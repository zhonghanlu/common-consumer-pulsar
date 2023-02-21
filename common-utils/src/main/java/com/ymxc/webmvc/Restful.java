package com.ymxc.webmvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restful<T> {

    public static <T> RestfulBuilder<T> SUCCESS() {
        return Restful.<T>builder().code(200).message("操作成功");
    }

    public static RestfulBuilder ERROR_PARAMS() {
        return Restful.builder().code(400).message("参数错误");
    }

    public static RestfulBuilder ERROR_SERVER() {
        return Restful.builder().code(500).message("服务器错误");
    }

    public static RestfulBuilder NOT_FOUNT() {
        return Restful.builder().code(404).message("数据不存在");
    }

    public static RestfulBuilder ERROR_LOGIN() {
        return Restful.builder().code(403).message("未登录");
    }

    public static RestfulBuilder LOGIN_EXPIRE() {
        return Restful.builder().code(405).message("登陆已失效");
    }

    public static RestfulBuilder FORBIDDEN() {
        return Restful.builder().code(401).message("无权限，禁止访问");
    }

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T object;

    @JsonProperty("info")
    private Object info;

}
