package com.lblz.crowd.mvc.config;

import com.google.gson.Gson;

import com.lblz.crowd.exception.LoginFailedException;
import com.lblz.crowd.response.ResultEntity;
import com.lblz.crowd.utils.JudgeRequestTypeUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lblz
 * @deacription 注解版异常处理类
 * @date 2021/10/30 19:42
 **/
@ControllerAdvice //表示基于注解的异常处理器类
public class ExceptionResolver {

    /**
     * @deacription NullPointerException异常处理
     * @param exception 异常类
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(NullPointerException exception, HttpServletRequest request,
                                                    HttpServletResponse response) throws IOException {
        return commonResolveException("system-error",exception,request,response);
    }

    /**
     * @deacription ArithmeticException异常处理
     * @param exception 异常类
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveNullPointerException(ArithmeticException exception, HttpServletRequest request,
                                                    HttpServletResponse response) throws IOException {
        return commonResolveException("system-error",exception,request,response);
    }

    /**
     * @deacription LoginFailedException异常处理
     * @param exception 异常类
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request,
                                                    HttpServletResponse response) throws IOException {
        return commonResolveException("admin-login",exception,request,response);
    }

    /**
     *
     * @param viewName 视图名称
     * @param exception 异常子类吧,这边用的是父类因为父类可以接受任何子类
     * @param request
     * @param response
     * @return
     */
    public ModelAndView commonResolveException(String viewName,
                                               Exception exception,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        Boolean aBoolean = JudgeRequestTypeUtils.checkRequestAjax(request);
        if(aBoolean) { //真ajax
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity); //字符串转换为json
            response.getWriter().write(json); //向浏览器输出流
            return null;            //由于上面原生的response已经做出回应了,所以不会走到null这边
        }
        //如果不是ajax请求则返回modelAndView
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName(viewName); //设置指定视图
        return modelAndView;
    }
}
