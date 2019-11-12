package com.st.web.controller;

import com.st.bo.FacadeResult;
import com.st.service.ProductRecommendService;
import com.st.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/18.
 */
@Controller
@RequestMapping("/data")
@Api(tags="1.获取优惠券和推荐产品",description = " ")
public class MFDataController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(MFDataController.class);
    @Autowired
    private ProductRecommendService productRecommendService;


    @ApiOperation(value="获取优惠券和推荐产品", notes="datas:" +
            "{" +"<br />"+
            "   \"couponMap\": " +"<br />"+

            "   {" +"<br />"+
            "      \"coupon_code\": \"0123456\",--优惠券码" +"<br />"+
            "      \"coupon_dead_time\": \"2017-01-30\",--到期时间" +"<br />"+
            "      \"coupon_name\": \"满300减30\",--到期时间" +"<br />"+
            "    }" +"<br />"+
            "   \"recommendProductMap\": "+"<br />"+
            "   {"+"<br />"+
            "      \"product_type\": \"N\",--产品类型N为非明星产品，S 为明星产品" +"<br />"+
            "      \"product_id\": 3,--产品id" +"<br />"+
            "      \"product_copywriter\": \"带来清透无感底妆，美裸妆必备利器，打造无瑕底妆\",--产品文案" +"<br />"+
            "      \"product_image_url\": \"http://115.28.164.153:9999/static/prodImages/normal_images/miracle_touch_liquid.png\",--产品图片url" +"<br />"+
            "      \"product_name\": \"蜜丝佛陀水漾触感粉底霜45号玉瓷色\",--推荐产品名称" +"<br />"+
            "      \"page_copywriter\": \"她是一款玩转物理形态，会“变身”的粉底霜\",--产品H5文案" +"<br />"+
            "      \"product_image_name\": \"miracle_touch_liquid.png\",--图片名" +"<br />"+

            "    }" +"<br />"+
            "    }",
            position = 1

    )
    @RequestMapping(value = "/getCouponInfo",method= RequestMethod.GET)
    public @ResponseBody
    FacadeResult getCouponInfo(HttpServletRequest request){

        logger.info("step5===>>获取优惠券记录");
        //将openid放到session中
        //1.判断session中获取openid
       /* request.getSession().setAttribute("openid","o98Aet4jH0HXtVk5KcGBgbnBzsag");
        request.getSession().setAttribute("recordId","1");*/
        if(StringUtils.isNull( request.getSession().getAttribute("openid"))||StringUtils.isNull(request.getSession().getAttribute("recordId"))){
            //session失效
            return  generateException(1001);
        }
        //2、获取session中openid以及recordId
        String openid = (String) request.getSession().getAttribute("openid");
        String recordId = (String) request.getSession().getAttribute("recordId");
        logger.info("step5===>>session中保存的openid:{},优惠券记录idrecordId{}", openid,recordId);
        //3、查询会员获取的优惠券信息
        Map<String,Object> couponMap = productRecommendService.getCouponInfoById(openid,recordId);

        //4、查询推荐产品信息
        Map<String,Object> recommendProductMap = productRecommendService.getRecommendProductMap(couponMap);

        //5、封装返回map
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("couponMap",couponMap);
        retMap.put("recommendProductMap",recommendProductMap);

        return generateData(retMap);
    }
}
