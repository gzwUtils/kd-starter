package org.action.user.common.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import java.util.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * @author gzw
 * @description：
 * @since：2025/2/10 23:31
 */
public class AesUtil {

    private static final String key = "uTfe6WtWICU/6rk0Gr7qKrAvHaRvQj+HRaHKvSe9UJI=";
    private static final AES AES = SecureUtil.aes(Base64.getDecoder().decode(key));

    public static String encrypt(String content) {
        //判空修改
        if (StringUtils.isBlank(content)) {
            return content;
        }

        return AES.encryptHex(content);
    }

    public static String decrypt(String content) {
        //判空修改
        if (StringUtils.isBlank(content)) {
            return content;
        }

        return AES.decryptStr(content);
    }

}
