package com.dogfood.springsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
public class AService {
    @Autowired
    private BService bService; // 先byType，再byName

    @Autowired
    private CService cService;
}
