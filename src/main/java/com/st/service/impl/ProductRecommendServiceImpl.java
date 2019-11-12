package com.st.service.impl;

import com.st.mapper.ProductRecommendMapper;
import com.st.service.ProductRecommendService;
import com.st.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/18.
 */
@Service
public class ProductRecommendServiceImpl implements ProductRecommendService{
    @Autowired
    private ProductRecommendMapper productRecommendMapper;


    @Override
    public Map<String, Object> getCouponInfoById(String openid, String recordId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("openid",openid);
        paramMap.put("recordId",recordId);
        return productRecommendMapper.getCouponInfoById(paramMap);
    }

    @Override
    public Map<String, Object> getRecommendProductMapByName(String productName) {

        return productRecommendMapper.getRecommendProductMapByName(productName);
    }

    @Override
    public Map<String, Object> getDefaultRecommendProduct() {
        return productRecommendMapper.getDefaultRecommendProduct();
    }

    @Override
    public Map<String, Object> getRecommendProductMap(Map<String, Object> couponMap) {

        //1、生命返回的map
        Map<String,Object> recommendProductMap = null;
        //2、如果优惠券信息或者推荐产品为空，走默认推荐渠道
        if(StringUtils.isNull(couponMap)||StringUtils.isNull(couponMap.get("recommend_products"))){
            recommendProductMap =  getDefaultRecommendProduct();
            return recommendProductMap;
        }
        //3、查询获取的推荐产品信息
        String productName =(String)couponMap.get("recommend_products");
        recommendProductMap = getRecommendProductMapByName(productName);
        //3.1、如果推荐产品map为空,获取默认产品列表
        if(StringUtils.isNull(recommendProductMap)){
            recommendProductMap = getDefaultRecommendProduct();

        }

        return recommendProductMap;
    }
}
