package com.lblz.crowd.exception;

/**
 * @author lblz
 * @deacription 登陆失败异常
 * @date 2021/10/31 7:51
 **/
public class LoginFailedException extends Exception{
    LoginFailedException(String message) {
        super(message);
    }
}
