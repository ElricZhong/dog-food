package com.dogfood.springmvcsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test() {
        return userService.test();
    }
}
