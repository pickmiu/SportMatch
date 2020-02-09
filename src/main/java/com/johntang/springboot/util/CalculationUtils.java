package com.johntang.springboot.util;

/**
 * @ClassName Calculation
 * @Description 计算类 负责一些计算方法
 * @Author JohnTang
 * @LastChangeDate 2020/2/6 21:29
 * @Version v1.0
 **/

public class CalculationUtils {

    public static Integer calculateTotalPageNum(Integer totalNum,Integer pageSize){
        if (totalNum % pageSize == 0) {
            return totalNum / pageSize;
        }else {
            return totalNum / pageSize + 1;
        }
    }
}
