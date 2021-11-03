package com.lblz.crowd.mvc.handler;

import com.lblz.crowd.mvc.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
