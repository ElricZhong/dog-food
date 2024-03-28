package com.dogfood.springsample.service;

/**
 * @author zhongfupeng
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {

    //@Autowired
    //private OrderService orderService; // 与构造方法同理，先ByType、再ByName

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //public UserService() {
    //    System.out.println(0);
    //}

    //public UserService(OrderService orderService1) { //先ByType、再ByName
    //    this.orderService = orderService1;
    //    System.out.println(1);
    //}

    //@Autowired
    //public UserService(OrderService orderService, OrderService orderService1) {
    //    this.orderService = orderService;
    //    System.out.println(2);
    //}

    //public void test() {
    //    System.out.println("执行test方法：" + orderService);
    //}

    @Transactional
    public void test() {
        jdbcTemplate.execute("INSERT INTO t1 VALUES(1, 'test1');");

        // throw new NullPointerException();

        // 这里不会抛异常，因为实际执行a方法的是普通对象，此时不会进入到事务的切面。
        a();
    }

    // Propagation.NEVER：当存在事务时直接抛异常
    @Transactional(propagation = Propagation.NEVER)
    public void a() {
        jdbcTemplate.execute("INSERT INTO t1 VALUES(2, 'test2');");
    }
}
