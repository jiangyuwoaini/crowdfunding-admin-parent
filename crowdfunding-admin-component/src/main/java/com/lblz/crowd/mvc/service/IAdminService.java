package com.lblz.crowd.mvc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.beans.PageInfoResult;
import com.lblz.crowd.response.ResultEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     *<p>
     *     分页返回admin列表数据
     *</p>
     * @param admin 查询条件
     * @param page 分页条件
     * @param request
     * @return
     */
    ResultEntity<List<Admin>> listPage(Admin admin, PageInfoResult page, HttpServletRequest request);
}
