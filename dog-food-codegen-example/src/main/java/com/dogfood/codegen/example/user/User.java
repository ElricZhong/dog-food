package com.dogfood.codegen.example.user;

import com.dogfood.codegen.annotation.FieldDesc;
import com.dogfood.codegen.processor.creator.GenCreator;
import com.dogfood.codegen.processor.creator.IgnoreCreator;
import com.dogfood.codegen.processor.updater.GenUpdater;
import com.dogfood.codegen.processor.updater.IgnoreUpdater;
import lombok.Data;

/**
 *
 * @author zhongfupeng
 */
@GenCreator(pkgName = "com.dogfood.codegen.example.user.creator")
@GenUpdater(pkgName = "com.dogfood.codegen.example.user.updater")
@Data
public class User {
    @FieldDesc(name = "姓名")
    private String name;
    @FieldDesc(name = "年龄")
    private Integer age;

    @IgnoreCreator
    @IgnoreUpdater
    private Integer validStatus;
}
