package com.lblz.crowd.mvc.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.context.session.SessionContext;
import com.lblz.crowd.context.session.SessionContextHolder;
import com.lblz.crowd.enums.SessionEnum;
import com.lblz.crowd.mapper.AdminMapper;
import com.lblz.crowd.mvc.service.IAdminService;
import com.lblz.crowd.response.ConstantMessage;
import com.lblz.crowd.utils.EndecryptUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 * @author lblz
 * @since 2021-11-02
 */
@Service
public class IAdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public boolean LoginAdmin(String loginAcct, String password, HttpServletRequest request, ModelMap modelMap) {
        if(Objects.isNull(loginAcct) || Objects.isNull(password)) {
            modelMap.addAttribute("message", ConstantMessage.USER_PASSWORD_NULL_ERROR);
            return false;
        }
        LambdaQueryWrapper<Admin> adminWrapper = new LambdaQueryWrapper<Admin>();
        adminWrapper.eq(Admin::getLoginAcct,loginAcct).eq(Admin::getPassword, EndecryptUtils.get3DESEncrypt(password));
        List<Admin> admins = this.list(adminWrapper);
        if(CollectionUtils.isEmpty(admins)){
            modelMap.addAttribute("message", ConstantMessage.USER_PASSWORD_ERROR);
            return false;
        }
        request.getSession().setAttribute("s_user",admins.get(0));
        new SessionContextHolder<Admin>().setObjectSession(SessionEnum.USER_SESSION.getName(),admins.get(0));
        return true;
    }
}
