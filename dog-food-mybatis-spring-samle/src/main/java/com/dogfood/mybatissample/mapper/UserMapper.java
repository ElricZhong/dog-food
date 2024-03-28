package com.dogfood.mybatissample.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhongfupeng
 */
public interface UserMapper {

    @Select("select 'user'")
    String getUserName();
}
