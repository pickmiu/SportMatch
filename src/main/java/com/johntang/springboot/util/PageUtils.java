package com.johntang.springboot.util;
import	java.util.List;

/**
 * @ClassName PageUtils
 * @Description 关于分页的工具
 * @Author JohnTang
 * @LastChangeDate 2020/2/8 7:35
 * @Version v1.0
 **/

public class PageUtils {

    public static BackFrontMessage generateMessage(Integer totalNum,Integer currentPageNum,Integer pageSize,List<?> currentPageList){
        Integer totalPageNum = CalculationUtils.calculateTotalPageNum(totalNum,pageSize);

        if(totalPageNum == 0){
            return new BackFrontMessage(200,"获取成功",null);
        }

        //如果请求页数大于总页数返回空
        if(currentPageNum > totalPageNum) {
            return new BackFrontMessage(500,"参数错误：请求页数不能大于总页数",null);
        }

        return new BackFrontMessage(200,"获取成功",new BackFrontPage(pageSize,totalPageNum,currentPageNum,currentPageList));
    }

}
