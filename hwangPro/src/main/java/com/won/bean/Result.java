package com.won.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.won.common.MessageCode;

import lombok.Data;

/**
 * 请求响应Bean
 * 使用JSON包装请求返回，使用jackson库
 *
 * @author spz
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {
	private int status ;
    private String message ;
    private Map<String,Object> result = new HashMap<String, Object>();

    /**
     * 自定义返回
     * @param code
     * @param message
     * @return
     */
    public Result setMessage(int code, String message){
        this.status = code;
        this.message = message;
        return this;
    }

    /**
     * 返回成功
     * @return
     */
    public Result setSuccessMessage(){
        this.status = MessageCode.SUCCESS ;
        this.message = "操作成功" ;
        return this;
    }

    /**
     * 返回成功
     * @param message
     * @return
     */
    public Result setSuccessMessage(String message){
        this.status = MessageCode.SUCCESS ;
        this.message = message ;
        return this;
    }

    /**
     * 返回错误
     * @param message
     * @return
     */
    public Result setErrorMessage(String message){
        this.status = MessageCode.ERROR ;
        this.message = message ;
        return this;
    }

    /**
     * 返回警告
     * @param message
     * @return
     */
    public Result setWarnMessage(String message){
        this.status = MessageCode.WARN ;
        this.message = message ;
        return this;
    }

    /**
     * 返回登录失败
     * @param message
     * @return
     */
    public Result setLoginFailMessage(String message){
        this.status = MessageCode.LOGIN_FAILED ;
        this.message = message ;
        return this;
    }

    /**
     * 返回没有权限
     * @param message
     * @return
     */
    public Result setPermissionDeniedMessage(String message){
        this.status = MessageCode.PERMISSION_DENIED ;
        this.message = message ;
        return this;
    }
}