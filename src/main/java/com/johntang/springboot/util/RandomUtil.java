package com.johntang.springboot.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Description 生成随机数
 * @Author JohnTang
 * @LastChangeDate 2020/3/8 2:50
 * @Version v1.0
 **/

@Component
public class RandomUtil {
    public String generateSixRandomCode() {
        Random random = new Random();
        return String.format("%06d",random.nextInt(1000000));
    }
}
