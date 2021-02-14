package com.huangjinwei.utils;


import com.huangjinwei.constant.EnumCode;

import java.util.Arrays;
import java.util.Objects;

public class EnumUtils {

    public static <T extends EnumCode> T codeOf(Integer code, Class<T> type) {
        return codeOf(code, type, null);
    }

    public static <T extends EnumCode> T codeOf(Integer code, Class<T> type, T defaultValue) {
        return Arrays.stream(type.getEnumConstants())
                .filter((constant) -> Objects.equals(constant.getCode(), code))
                .findAny()
                .orElse(defaultValue);
    }

    public static boolean equalsCode(Integer code, EnumCode enumCode) {
        return Objects.equals(code, enumCode.getCode());
    }

    public static boolean notEqualsCode(Integer code, EnumCode enumCode) {
        return !equalsCode(code, enumCode);
    }
}
