package com.st.web.controller;

import com.st.service.AuthorizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Administrator on 2017/1/17.
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeController {
    private Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
    @Autowired
    private AuthorizeService authService;
    /**
     * 微信网页授权
     * @return
     */
    @ApiIgnore
    @RequestMapping(method= RequestMethod.GET)
    public String auth(@RequestParam String backUrl){

        logger.info("auth===>>>>>>>>>>>>>>>>>>...");
        return   "redirect:" + authService.authUrl(backUrl);

    }
}
