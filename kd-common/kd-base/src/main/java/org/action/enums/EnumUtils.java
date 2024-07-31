package org.action.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gzw
 * @description： 枚举工具类
 * @since：2023/7/15 10:42
 */
@SuppressWarnings("unused")
public class EnumUtils {

    private EnumUtils() {
    }



    public static <T extends ErrorCode> String getDescriptionByCode(String code, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst().map(ErrorCode::getMessage).orElse("");
    }

    public static <T extends ErrorCode> T getEnumByCode(String code, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst().orElse(null);
    }

    public static <T extends ErrorCode> List<String> getCodeList(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(ErrorCode::getCode)
                .collect(Collectors.toList());
    }
}
