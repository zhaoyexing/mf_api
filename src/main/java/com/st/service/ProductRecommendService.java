package com.st.service;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface ProductRecommendService {
    /**
     * 根据记录id和opendid获取优惠券信息
     * @param openid
     * @param recordId
     * @return
     */
    Map<String,Object> getCouponInfoById(String openid, String recordId);

    /**
     * 根据产品name获取推荐产品map
     * @param productName
     * @return
     */
    Map<String,Object> getRecommendProductMapByName(String productName);

    /**
     * 获取默认推荐产品
     * @return
     */
    Map<String,Object> getDefaultRecommendProduct();


    /**
     * 获取推荐俺产品
     * @param couponMap
     * @return
     */
    Map<String,Object> getRecommendProductMap(Map<String, Object> couponMap);
}
