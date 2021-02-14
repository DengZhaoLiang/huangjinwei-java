package com.huangjinwei.constant;

import java.util.Arrays;
import java.util.Objects;

public interface EnumCode {
    static <T extends Enum> T valueOf(int code, Class<T> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(constant -> Objects.equals(((EnumCode) constant).getCode(), code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can not find " + enumType.getSimpleName() + " of value " + code));
    }

    int getCode();
}