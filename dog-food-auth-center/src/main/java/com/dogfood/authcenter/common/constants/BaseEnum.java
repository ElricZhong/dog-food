package com.dogfood.authcenter.common.constants;

/**
 * @author zhongfupeng
 */
public interface BaseEnum<T extends Enum<T> & BaseEnum<T>> {
    Integer getCode();

    String getName();

    static <T extends Enum<T> & BaseEnum<T>> T parseByCode(Class<T> cls, Integer code) {
        for (T enumConstant : cls.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)) {
                return enumConstant;
            }
        }

        return null;
    }
}
