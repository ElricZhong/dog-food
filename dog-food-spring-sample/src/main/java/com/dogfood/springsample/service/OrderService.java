package com.dogfood.springsample.service;

import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
public class OrderService {

    //private UserService userService;

    // 循环依赖错误
    //public OrderService(UserService userService) {
    //    this.userService = userService;
    //}
}
