package com.dogfood.authcenter.common.model;

import java.util.Objects;

import com.dogfood.authcenter.common.constants.BaseEnum;
import com.dogfood.authcenter.common.constants.CodeEnum;
import lombok.Data;

/**
 * @author zhongfupeng
 */
@Data
public class JsonObject<E> {
    private Integer code;
    private String msg;
    private E result;

    private JsonObject() {
    }

    public static <E> JsonObject<E> success(E e) {
        JsonObject<E> object = new JsonObject();
        object.setCode(CodeEnum.Success.getCode());
        object.setMsg(CodeEnum.Success.getName());
        object.setResult(e);
        return object;
    }

    public static <E> JsonObject<E> success(E t, String msg) {
        JsonObject<E> obj = success(t);
        obj.setMsg(msg);
        return obj;
    }

    public static JsonObject fail(BaseEnum codeEnum) {
        JsonObject object = new JsonObject();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        return object;
    }

    public static JsonObject fail(String msg) {
        JsonObject object = new JsonObject();
        object.setMsg(msg);
        object.setCode(CodeEnum.Fail.getCode());
        return object;
    }

    public static <E> JsonObject<E> fail(E e, String msg) {
        JsonObject<E> object = new JsonObject();
        object.setCode(CodeEnum.Fail.getCode());
        object.setMsg(msg);
        object.setResult(e);
        return object;
    }

    public static <E> JsonObject<E> res(BaseEnum codeEnum, E e) {
        JsonObject<E> object = new JsonObject();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        object.setResult(e);
        return object;
    }

    public boolean isSuccess() {
        return Objects.equals(CodeEnum.Success.getCode(), this.getCode());
    }
}
