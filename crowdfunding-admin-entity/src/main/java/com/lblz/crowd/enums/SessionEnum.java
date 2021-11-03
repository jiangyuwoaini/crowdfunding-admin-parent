package com.lblz.crowd.enums;

/**
 * @author lblz
 * @deacription session名称
 * @date 2021/11/2 19:24
 **/
public enum SessionEnum {
    USER_SESSION("USER_SESSION");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SessionEnum(String name) {
        this.name = name;
    }
}
