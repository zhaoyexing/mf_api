package com.st.web.controller;

import com.st.service.AuthorizeService;
import com.st.service.ProductRecommendService;
import com.st.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/17.
 */
@Controller
@RequestMapping("/views")
public class MFViewController {

    private Logger logger = LoggerFactory.getLogger(MFViewController.class);

    @Value("${wx.config.backUrl}")
    private String backUrl;
    @Autowired
    private AuthorizeService authService;
    @Autowired
    private ProductRecommendService productRecommendService;
    /**
     * 微信网页授权
     * @return
     */
    @ApiIgnore
    @RequestMapping(method= RequestMethod.GET,value = "/menuEntry/{recordId}")
    public String menuEntry(HttpServletRequest request,@PathVariable String recordId){

        //1、判断session中是否存在openid,如果存在直接跳到页面
        if(!StringUtils.isNull(request.getSession().getAttribute("openid"))){
            logger.info("step1===>>session中保存有用户openid:{}",request.getSession().getAttribute("openid"));
            return "/coupon";
        }
        //2、session中不存在openid 走授权获取openid
        StringBuilder authUrlBuilder = new StringBuilder();
        authUrlBuilder.append(backUrl)
                .append(recordId);

        logger.info("step2===>>重定向到scrm隐形授权url: {}", authUrlBuilder);
        return  "redirect:" + authService.authUrl(authUrlBuilder.toString());
    }

    /**
     * SCRM隐性授权回调地址
     * @return
     */
    @ApiIgnore
    @RequestMapping(method= RequestMethod.GET,value = "/index/{recordId}")
    public String index(HttpServletRequest request,@PathVariable String recordId){

        //1、获取参数openid以及记录id
        String openid = request.getParameter("openid");
        logger.info("step3===>>微信回掉的controller,拿到openid:{}", openid);
        if(StringUtils.isNull(openid)){
            return "/coupon";
        }
        //将openid放到session中
        request.getSession().setAttribute("openid",openid);
        request.getSession().setAttribute("recordId",recordId);

        logger.info("step4===>>openid:{},优惠券记录idrecordId{}保存到session", openid,recordId);
        return "/coupon";
    }



}
