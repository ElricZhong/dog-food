package com.dogfood.mybatissample.service;

/**
 * @author zhongfupeng
 */

import com.dogfood.mybatissample.mapper.MemberMapper;
import com.dogfood.mybatissample.mapper.OrderMapper;
import com.dogfood.mybatissample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserMapper userMapper; // Mybatis代理对象 --> Bean
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MemberMapper memberMapper;

    public void test() {
        System.out.println(userMapper.getUserName());
        System.out.println(orderMapper.getOrderName());
        System.out.println(memberMapper.getMemberName());
    }
}
