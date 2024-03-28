package com.dogfood.mybatissample.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhongfupeng
 */
public interface MemberMapper {

    @Select("select 'member'")
    String getMemberName();
}
