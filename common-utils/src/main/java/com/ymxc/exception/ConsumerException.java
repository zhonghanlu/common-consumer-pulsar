package com.ymxc.exception;

import com.ymxc.webmvc.Restful;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConsumerException extends RuntimeException {

    /**
     * 错误返回码
     */
    private int code;

    /**
     * 错误信息
     */
    private String error;

    public ConsumerException(int code, String error) {
        super();
        this.code = code;
        this.error = error;
    }

    public ConsumerException(String message) {
        this(500, message);
    }

    public ConsumerException(Throwable cause) {
        this(cause.getMessage());
    }

    public ConsumerException(Restful restful) {
        this(restful.getCode(), restful.getMessage());
    }
}