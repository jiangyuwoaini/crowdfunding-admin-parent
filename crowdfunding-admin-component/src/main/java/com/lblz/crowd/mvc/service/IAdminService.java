package com.lblz.crowd.mvc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lblz.crowd.beans.Admin;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 * @author lblz
 * @since 2021-11-02
 */
@Service
public interface IAdminService extends IService<Admin> {

    /**
     * <p>
     *     管理员登陆操作
     * </p>
     * @param loginAcct 用户名
     * @param password 密码
     * @param request request请求主要用于存放session用的
     * @param modelMap
     * @return
     */
    boolean LoginAdmin(String loginAcct, String password,
                       HttpServletRequest request, ModelMap modelMap);
}
