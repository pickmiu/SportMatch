package com.johntang.springboot.util;

import com.johntang.springboot.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName UserAuthenticationUtils
 * @Description 用户认证工具类
 * @Author JohnTang
 * @LastChangeDate 2020/2/7 0:52
 * @Version v1.0
 **/

public class UserAuthenticationUtils {

    //获取当前访问用户信息
    public static User getCurrentUser(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
