package io.admin.common.utils;


public class InnerTokenUtils {


    // 由门户中心调用
    public static String createToken(String account) {
        return AesUtils.encryptHex(account);
    }

    // 由子系统验证， 后期调整为可配置
    public static String validateToken(String token) {
        return AesUtils.decryptHex(token);
    }


}
