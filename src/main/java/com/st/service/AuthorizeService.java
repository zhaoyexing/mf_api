package com.st.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class AuthorizeService {

    private Logger logger = LoggerFactory.getLogger(AuthorizeService.class);
    /** 授权地址 */
    public static final String AUTHORIZE_URL = "http://app.biz.social-touch.com/app-api/oauth/oauth2";

    @Value("${wx.config.source}")
    private String source;
    @Value("${wx.config.appid}")
    private String appid;
    @Value("${wx.config.secret}")
    private String secret;
    /**
     * 获取微信授权链接
     * @return
     */
    public String authUrl(String backUrl){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AUTHORIZE_URL)
                .append("?backurl=").append(backUrl)
                .append("&source=").append(source)
                .append("&secret=").append(secret)
                .append("&appid=").append(appid);
        logger.info("微信授权链接: {}", urlBuilder);
        return urlBuilder.toString();
    }
}
