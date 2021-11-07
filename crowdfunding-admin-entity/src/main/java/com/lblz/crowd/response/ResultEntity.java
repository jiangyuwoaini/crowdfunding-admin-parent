package com.lblz.crowd.response;

import com.lblz.crowd.beans.PageInfoResult;
import com.lblz.crowd.enums.CodeEnum;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author lblz
 * @deacription response返回的实体
 * @date 2021/10/30 14:37
 **/
public class ResultEntity<T> implements Serializable {

    private T data;

    private String code;

    private String message;

    private PageInfoResult page;

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
     * @deacription 请求成功 且不要返回数据生成的方法
     * @param <T> <T>代表着声明的泛型,后面ResultEntity<T>表示使用前面生成的泛型
     * @return
     */
    public static <T> ResultEntity<T> listPage(T object, PageInfoResult page) {
        if(Objects.isNull(object)) {
            return new ResultEntity<T>(null,CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(),page);
        }
        List<T> lists = ((List<T>) object);
        page.setSize(lists.size());
        int size = lists.size();
        //(1,总页数 = 总数据 /每页数) || (1,剩余页数 = 总数据 / 每页数 -当前页) (2,剩余页数 = 总数据 -(当前页 * 每页页数) /每页页数)
        //如果剩余数量不到一页直接赋值1
        int countPage = size / page.getPageCount();
        if(size % page.getPageCount() == 0){ //求总页数
            page.setCountPage(countPage);
        }else{
            page.setCountPage(countPage + 1);
        }
        if(page.getPageCount() * page.getCurrentPage() > size){ //如果当前页 * 每页最大数量 大于总集合
            lists = lists.subList(page.getPageCount() * (page.getCurrentPage()-1) ,lists.size());
        }else{
            lists = lists.subList(page.getPageCount() * (page.getCurrentPage()-1) ,page.getPageCount() * page.getCurrentPage());
        }
        return new ResultEntity<T>((T)lists,CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(),page);
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


    public ResultEntity() {

    }

    public ResultEntity(T data, String code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public ResultEntity(T data, String code, String message, PageInfoResult page) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
        this.page = page;
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

    public PageInfoResult getPage() {
        return page;
    }

    public void setPage(PageInfoResult page) {
        this.page = page;
    }
}
