package com.lblz.crowd.mvc.handler;

import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.beans.PageInfoResult;
import com.lblz.crowd.mvc.service.IAdminService;
import com.lblz.crowd.response.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lblz
 * @deacription
 * @date 2021/11/2 7:26
 **/
@Controller
@RequestMapping("/admin/")
public class AdminHandler {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("login.html")
    public String login(@RequestParam("loginAcct") String loginAcct,
                        @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response,
                        ModelMap modelMap) {
        boolean loginSuccess = adminService.LoginAdmin(loginAcct, password, request, modelMap);
        if(loginSuccess){
            return "admin-main";
        }else{
            return "admin-login";
        }
    }

    @RequestMapping("logout.html")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); //解除当前所有session
        return "admin-login";
    }

    @RequestMapping("/listPage.html")
    public String listPage(Admin admin, PageInfoResult page, HttpServletRequest request,ModelMap modelMap) {
            ResultEntity<List<Admin>> listResultEntity = adminService.listPage(admin, page, request);
        modelMap.addAttribute("listResultEntity",listResultEntity);
        return "admin-page";
    }

}
