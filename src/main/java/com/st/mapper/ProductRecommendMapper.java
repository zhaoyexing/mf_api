package com.st.mapper;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface ProductRecommendMapper {
    /**
     * 根据记录id和opendid获取优惠券信息
     * @param paramMap
     * @return
     */
    Map<String,Object> getCouponInfoById(Map<String, Object> paramMap);

    /**
     * 获取产品name推荐产品
     * @param productName
     * @return
     */
    Map<String,Object> getRecommendProductMapByName(String productName);

    /**
     * 获取默认推荐产品
     * @return
     */
    Map<String,Object> getDefaultRecommendProduct();
}
