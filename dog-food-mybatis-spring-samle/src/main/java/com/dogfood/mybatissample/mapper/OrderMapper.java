package com.dogfood.mybatissample.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhongfupeng
 */
public interface OrderMapper {

    @Select("select 'order'")
    String getOrderName();
}
