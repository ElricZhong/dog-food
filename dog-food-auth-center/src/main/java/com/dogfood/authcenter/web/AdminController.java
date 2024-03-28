package com.dogfood.authcenter.web;

import com.dogfood.authcenter.common.model.JsonObject;
import com.dogfood.authcenter.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping
    public JsonObject<String> home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return JsonObject.fail("hello world!" + user.getUsername());
    }
}
