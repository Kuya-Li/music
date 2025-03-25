package com.gzu.backstage.exception;
import lombok.Getter;


/**
 * Description:这个类是自定义异常类，这个类会被GlobalExceptionHandler捕获
 * Author: SuWenJay
 * Date: 2023-08-10
 */

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private final String code;

    /**
     * 构造方法
     * @param code 状态码
     * @param msg 错误信息
     */
    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }


}

