package com.dogfood.authcenter.web;

import com.dogfood.authcenter.common.model.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
@RequestMapping(value = "user/auth")
public class UserAuthController {

    @RequestMapping(value = "passwordLogin")
    public JsonObject<String> passwordLogin() {
        return JsonObject.fail("配置出现问题");
    }

    @RequestMapping(value = "smsLogin")
    public JsonObject<String> smsLoginLogin() {
        return JsonObject.fail("配置出现问题");
    }
}
