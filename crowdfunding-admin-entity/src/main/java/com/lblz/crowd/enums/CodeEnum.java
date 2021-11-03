package com.lblz.crowd.enums;

/**
 * @author lblz
 * @deacription 状态码
 * @date 2021/10/30 14:43
 **/
public enum CodeEnum {
    SUCCESS("20000", "成功"),
    ERROR("20001", "系统繁忙,请稍后重试"),
    NOT_LOGIN("20002", "未登录,请登陆后再操作"),
    PARAMETER_ERROR("20003", "参数错误"),
    REJECT("20004", "拒绝执行"),
    NOT_FOUND("20005", "资源不存在"),
    FAILED_INSERT("20006", "新增失败"),
    FAILED_UPDATE("20007", "更新失败"),
    FAILED_DELETE("20008", "删除失败"),
    NO_OPERATION_PERMISSION("20009", "无操作权限"),
    SIGN_ERROR("20010", "签名校验失败，签名错误!"),
    REPEAT_OPERATION_ERROR("20011", "请勿重复操作"),
    FAILED_REPLACE("20012", "替换失败"),
    ERR_USERNAME_PASSWORD("20013", "用户名或密码错误！"),
    ERR_ACCOUNT_DISABLE("20014", "此账户已禁用，请联系管理员!"),
    ERR_ACCOUNT_NOT_APPROVED("20015", "此账户审核未通过，请联系管理员!"),
    ERR_ACCOUNT_WAIT_CHECK("20016", "注册中，等待审核!"),
    DATA_NOT_EXIST("20017", "数据不存在"),
    USER_ID_NOT_EXIST("20018", "用户编号不存在"),
    RESULT_ERROR("20020", "题库数量不足"),
    ERR_CHECK_CODE_EMPTY("20021", "验证码不能为空！"),
    PARAMETER_PASSWORD_RESET_ERROR("20022", "密码不一致，重置失败！"),
    SITE_NOT_FOUND("20023", "请求失败，站点资源不存在！"),
    PARAMETER_LONG_ERROR("20024", "内容过大"),
    PARAMETER_NULL_ERROR("21000", "参数不能为空"),
    PARAMETER_REGEX_ERROR("21001", "格式有误，请重新输入！");

    private String code;
    private String message;

    private CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String code) {
        CodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CodeEnum s = var1[var3];
            if (null == code) {
                break;
            }

            if (s.code.equals(code)) {
                return s.message;
            }
        }

        return null;
    }
}
