package com.lblz.crowd.mvc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.beans.PageInfoResult;
import com.lblz.crowd.context.session.SessionContextHolder;
import com.lblz.crowd.enums.SessionEnum;
import com.lblz.crowd.mapper.AdminMapper;
import com.lblz.crowd.mvc.service.IAdminService;
import com.lblz.crowd.response.ConstantMessage;
import com.lblz.crowd.response.ResultEntity;
import com.lblz.crowd.utils.EndecryptUtils;
import org.springframework.stereotype.Service;
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
            modelMap.addAttribute(ConstantMessage.MESSAGE_KEY, ConstantMessage.USER_PASSWORD_NULL_ERROR);
            return false;
        }
        LambdaQueryWrapper<Admin> adminWrapper = new LambdaQueryWrapper<Admin>();
        adminWrapper.eq(Admin::getLoginAcct,loginAcct).eq(Admin::getPassword, EndecryptUtils.get3DESEncrypt(password,null));
        List<Admin> admins = this.list(adminWrapper);
        if(CollectionUtils.isEmpty(admins)){
            modelMap.addAttribute(ConstantMessage.MESSAGE_KEY, ConstantMessage.USER_PASSWORD_ERROR);
            return false;
        }
        request.getSession().setAttribute(ConstantMessage.ADMIN_USER_KEY,admins.get(0));
        new SessionContextHolder<Admin>().setObjectSession(SessionEnum.USER_SESSION.getName(),admins.get(0));
        return true;
    }

    @Override
    public ResultEntity<List<Admin>> listPage(Admin admin, PageInfoResult page, HttpServletRequest request) {
        List<Admin> admins = null;
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(Objects.nonNull(admin.getRealName()) && StrUtil.isNotBlank(admin.getRealName())) {
            wrapper.like(Admin::getRealName,admin.getRealName());
            wrapper.orderByDesc(Admin::getCreateTime);
        }
        admins = this.list(wrapper);
        return ResultEntity.listPage(admins,page);
    }

    @Override
    public Boolean remove(Long id, HttpServletRequest request, ModelMap modelMap) {
        boolean deleted = this.removeById(id);
        if(deleted) {
            modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.SUCCESS_DELETED);
        }else{
            modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.FAILED_DELETED);
        }
        return deleted;
    }

    @Override
    public void insert(Admin admin, HttpServletRequest request, ModelMap modelMap) {
        if(Objects.nonNull(admin) && Objects.nonNull(admin.getPassword())) {
            admin.setPassword(EndecryptUtils.get3DESEncrypt(admin.getPassword(),null));
        }
        int rows = baseMapper.insert(admin);
        if(rows > 0) {
            modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.SUCCESS_INSERT);
            return;
        }
        modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.FAILED_INSERT);
    }

    @Override
    public ResultEntity<String> check(Admin admin, HttpServletRequest request, ModelMap modelMap) {
        LambdaQueryWrapper<Admin> wrapper = null;
        if(Objects.nonNull(admin.getLoginAcct())) {
            wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Admin::getLoginAcct,admin.getLoginAcct());
            List<Admin> admins = this.list(wrapper);
            if(CollectionUtil.isNotEmpty(admins)){
                return ResultEntity.failed(ConstantMessage.USERNAME_REPETITION);
            }
        }
        return ResultEntity.success();
    }

    @Override
    public Admin getById(Long id, HttpServletRequest request, ModelMap modelMap) {
        Admin admin = this.getById(id);
        if(Objects.nonNull(admin) && Objects.nonNull(admin.getPassword())) {
            admin.setPassword(EndecryptUtils.get3DESDecrypt(admin.getPassword(),null));
        }
        return admin;
    }

    @Override
    public void modify(Admin admin, HttpServletRequest request, ModelMap modelMap) {
        admin.setPassword(EndecryptUtils.get3DESEncrypt(admin.getPassword(),null));
        int rows = baseMapper.updateById(admin);
        if(rows > 0) {
            modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.SUCCESS_UPDATE);
            return;
        }
        modelMap.put(ConstantMessage.MESSAGE_KEY, ConstantMessage.FAILED_UPDATE);
    }

}
