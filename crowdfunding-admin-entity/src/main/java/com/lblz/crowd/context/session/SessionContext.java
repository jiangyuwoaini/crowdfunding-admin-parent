package com.lblz.crowd.context.session;

import javax.servlet.http.HttpSession;

/**
 * @author lblz
 * @deacription Session容器，用于管理和引用session
 * @date 2021/11/2 19:09
 **/
public class SessionContext {
    //存储HttpSession,ThreadLocal是一个数据结构
    private static ThreadLocal<HttpSession> contextHolder = new ThreadLocal<HttpSession>();

    public void clearContext() {
        contextHolder.set(null);
    }

    public HttpSession getContext() {
        return contextHolder.get();
    }

    public void setContext(HttpSession context) {
        contextHolder.set(context);
    }
}
