package com.lblz.crowd.mvc.interceptor;

import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.exception.LoginFailedException;
import com.lblz.crowd.response.ConstantMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author lblz
 * @deacription 拦截器,可以实现HandlerInterceptor 也可以继承HandlerInterceptorAdapter
 * @date 2021/11/4 7:51
 **/
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * <p>
     *      在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * </p>
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute(ConstantMessage.ADMIN_USER_KEY);
        if(Objects.isNull(admin)) {
            throw new LoginFailedException(ConstantMessage.SESSION_EXPIRES);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * <p>
     *     在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
     * </p>
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
