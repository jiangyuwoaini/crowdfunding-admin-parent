package com.lblz.crowd.context.session;

import javax.servlet.http.HttpSession;

/**
 * @author lblz
 * @deacription
 * @date 2021/11/2 19:13
 **/
public class SessionContextHolder<T> {

    private static SessionContext strategy;

    private static int initializeCount = 0;

    public static void clearContext() {
        strategy.clearContext();
    }

    public static <T> void setObjectSession(String sessionName,T object) {
        HttpSession session = strategy.getContext();
        if(session != null) {
            session.setAttribute(sessionName,object);
        }
    }

    public static HttpSession getHttpSession() {
        return strategy.getContext();
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    private static void initialize() {
        strategy = new SessionContext();
        initializeCount++;
    }

    public static void setContext(HttpSession context) {
        strategy.setContext(context);
    }

    static {
        initialize();
    }
}
