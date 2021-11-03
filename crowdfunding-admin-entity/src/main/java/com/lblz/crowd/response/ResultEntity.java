package com.lblz.crowd.response;

import com.lblz.crowd.enums.CodeEnum;

import java.io.Serializable;

/**
 * @author lblz
 * @deacription response返回的实体
 * @date 2021/10/30 14:37
 **/
public class ResultEntity<T> implements Serializable {

    private T data;

    private String code;

    private String message;

    /**
     * @deacription 请求成功 且不要返回数据生成的方法
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> success() {
        return new ResultEntity<T>(null, CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage());
    }

    /**
     * @deacription 请求成功 且不要返回数据生成的方法
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> success(T object) {
        return new ResultEntity<T>(object,CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage());
    }

    /**
     * @deacription 请求失败
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> failed() {
        return new ResultEntity<T>(null,CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
    }

    /**
     *
     * @param message 错误消息
     * @param <T>
     * @return
     */
    public static <T> ResultEntity<T> failed(String message) {
        return new ResultEntity<T>(null,CodeEnum.ERROR.getCode(),message);
    }

    /**
     *
     * @param codeEnum 消息枚举类
     * @param <T>
     * @return
     */
    public static <T> ResultEntity<T> failed(CodeEnum codeEnum) {
        return new ResultEntity<T>(null,CodeEnum.ERROR.getCode(),codeEnum.getMessage());
    }

    /**
     * @deacription 请求失败
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> toObject() {
        return new ResultEntity<T>(null,CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
    }

    /**
     *
     * @param object 可传入任意的类型数据
     * @param codeEnum 传入一个codeEnum的枚举
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> of(T object,CodeEnum codeEnum){
        return new ResultEntity<T>(object,codeEnum.getCode(),codeEnum.getMessage());
    }

    ResultEntity() {

    }

    ResultEntity(T data, String code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
