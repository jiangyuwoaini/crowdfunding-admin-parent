package com.lblz.crowd.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author lblz
 * @deacription 判断请求类型
 * @date 2021/10/30 16:33
 **/
public class JudgeRequestTypeUtils {

    /**
     * <p>
     *     判断是否是ajax true:真 | false:假
     * </p>
     * @param request
     * @return
     */
    public static Boolean checkRequestAjax(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String requestWithHeader = request.getHeader("x-requested-with");
        return (Objects.nonNull(acceptHeader) && acceptHeader.contains("application/json"))
                ||
                (Objects.nonNull(requestWithHeader) && requestWithHeader.contains("XMLHttpRequest"));


    }
}
