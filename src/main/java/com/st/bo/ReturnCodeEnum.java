package com.st.bo;

/**
 * Created by shenjiachun on 2015/10/9.
 * 异常枚举
 */
public enum ReturnCodeEnum {
    STATUS_OK(200,"request return ok"),
    PARAM_CHECK_ERROR(1001,"check param error"),
    SERVER_EXCEPTION(1002,"server exception"),
    ERROR_10001(10001,"appkey is null"),
    ERROR_10002(10002,"itemid is null"),
    ERROR_10003(10003,"actionid is null"),
    ERROR_10004(10004,"uid is null"),
    ERROR_10005(10005,"utype is null"),
    ERROR_10006(10006,"clientIP is null"),
    ERROR_10007(10007,"itemtype is null"),
    ERROR_10008(10008,"actiontime is null"),
    ERROR_10009(10009,"expends info type is null"),
    ERROR_10010(100010,"logs exists"),
    ERROR_10011(10011,"appid is not exists"),
    ERROR_10012(10012,"actionid is not exists");

    private Integer code;
    private String desc;


    ReturnCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReturnCodeEnum codeOf(Integer code) {
        for (ReturnCodeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }


    public static ReturnCodeEnum descOf(String desc) {
        for (ReturnCodeEnum type : values()) {
            if (type.getDesc().equals(desc)) {
                return type;
            }
        }
        return null;
    }
}
