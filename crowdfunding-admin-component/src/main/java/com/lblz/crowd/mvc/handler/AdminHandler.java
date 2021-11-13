package com.lblz.crowd.mvc.handler;

import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.beans.PageInfoResult;
import com.lblz.crowd.mvc.service.IAdminService;
import com.lblz.crowd.response.ConstantMessage;
import com.lblz.crowd.response.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @RequestMapping("listPage.html")
    public String listPage(Admin admin, PageInfoResult page, String message, HttpServletRequest request,ModelMap modelMap) {
        ResultEntity<List<Admin>> listResultEntity = adminService.listPage(admin, page, request);
        modelMap.addAttribute("listResultEntity",listResultEntity);
        modelMap.addAttribute(ConstantMessage.MESSAGE_KEY,message);
        return "admin-page";
    }

    @RequestMapping("remove.html")
    public String remove(Admin admin, PageInfoResult page, HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        adminService.remove(admin.getId(), request, modelMap);
        return "redirect:/admin/listPage.html?message="+ URLEncoder.encode(modelMap.get(ConstantMessage.MESSAGE_KEY).toString(),"UTF-8")+"&realName="+URLEncoder.encode(admin.getRealName(),"UTF-8");
    }

    @RequestMapping("add.html")
    public String add(Admin admin, HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        adminService.insert(admin, request, modelMap);
        return "redirect:/admin/listPage.html?message="+ URLEncoder.encode(modelMap.get(ConstantMessage.MESSAGE_KEY).toString(),"UTF-8");
    }

    @ResponseBody
    @RequestMapping("check.json")
    public ResultEntity<String> check(Admin admin,HttpServletRequest request, ModelMap modelMap) {
        return adminService.check(admin,request,modelMap);
    }

    @RequestMapping("edit.html")
    public String edit(Long id,HttpServletRequest request, ModelMap modelMap) {
        Admin admin = adminService.getById(id, request, modelMap);
        modelMap.put("admin",admin);
        return "admin-edit";
    }

    @RequestMapping("edit.do")
    public String toEdit(Admin admin,HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        adminService.modify(admin,request,modelMap);
        return "redirect:/admin/listPage.html?message="+ URLEncoder.encode(modelMap.get(ConstantMessage.MESSAGE_KEY).toString(),"UTF-8");
    }
}
