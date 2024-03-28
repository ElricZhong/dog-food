package com.dogfood.bowlbootsample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
public class UserController {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
