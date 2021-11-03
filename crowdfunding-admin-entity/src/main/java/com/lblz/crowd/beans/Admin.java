package com.lblz.crowd.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lblz
 * @deacription
 * @date 2021/11/2 9:51
 **/
@Data
@TableName(value = "t_admin")
public class Admin extends GeneralProperty{
    /**
     * 用户名
     */
    private String loginAcct;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 邮箱
     */
    private String email;
}
