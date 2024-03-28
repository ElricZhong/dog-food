package com.dogfood.springsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
public class CService {
    @Autowired
    private AService aService;
}
