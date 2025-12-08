package io.admin.modules.api;

import cn.hutool.crypto.SecureUtil;

public class ApiSignUtils {

    public static String sign(String appId, String appSecret, long timestamp) {
        String signStr = appId + appSecret + timestamp;
        return SecureUtil.md5(signStr);
    }

}
