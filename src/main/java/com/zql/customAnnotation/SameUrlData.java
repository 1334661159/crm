package com.zql.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:zhangqinglei
 * Description:一个用户 同时提交 相同数据 验证重复提交
 * Created by qwert on 2018/2/5.
 * Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SameUrlData {

}
