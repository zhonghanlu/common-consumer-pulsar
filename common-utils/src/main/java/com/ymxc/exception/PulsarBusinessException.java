package com.ymxc.exception;

/**
 * 自定义Pulsar异常
 **/
public class PulsarBusinessException extends RuntimeException{

    public PulsarBusinessException(String msg){
        super(msg);
    }

    public PulsarBusinessException(String msg,Throwable e){
        super(msg,e);
    }
}