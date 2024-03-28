package com.dogfood.bowlsample;

/**
 * @author zhongfupeng
 */

import com.dogfood.bowl.Autowired;
import com.dogfood.bowl.BeanNameAware;
import com.dogfood.bowl.Component;
import com.dogfood.bowl.InitializingBean;
import com.dogfood.bowl.Scope;

@Component
@Scope("singleton")
public class UserService implements UserInterface, BeanNameAware, InitializingBean {
    @Autowired
    private OrderService orderService;

    private String beanName;

    public UserService() {
        System.out.println("===实例化bean===");
    }

    @Override
    public void test() {
        System.out.println("执行test方法：" + orderService);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("===Aware回调===");
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("===初始化===");
    }
}
