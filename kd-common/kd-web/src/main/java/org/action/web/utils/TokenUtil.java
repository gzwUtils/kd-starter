package org.action.web.utils;

import cn.hutool.crypto.SecureUtil;
import org.action.web.constant.Constant;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.action.cache.constant.CacheConstant.CACHE_PREFIX;

public class TokenUtil {

    private TokenUtil(){}


    public static String getTokenValueByKey(String tokenKey) {
        if (tokenKey == null) {
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        String tokenValue = tokenKey + CACHE_PREFIX + uuid;

        return SecureUtil.aes(Constant.TOKEN_MY.getBytes(StandardCharsets.UTF_8)).encryptBase64(tokenValue);
    }

    public static String getTokenKeyByValue(String tokenValue) {
        if (tokenValue == null) {
            return null;
        }
        String decryptTokenValue = SecureUtil.aes(Constant.TOKEN_MY.getBytes(StandardCharsets.UTF_8)).decryptStr(tokenValue);

        return decryptTokenValue.substring(0, decryptTokenValue.lastIndexOf(CACHE_PREFIX));
    }
}
