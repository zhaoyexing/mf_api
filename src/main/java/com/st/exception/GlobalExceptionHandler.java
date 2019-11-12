package com.st.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.st.bo.FacadeResult;
import com.st.bo.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public FacadeResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        //打印报错信息
         logger.error(e.getMessage());
         e.printStackTrace();

        if(e.getMessage().indexOf("ufs_influence_monitor_logs_unique_1")>0){
            return new FacadeResult(ReturnCodeEnum.ERROR_10010.getCode());
        }
        //返回错误异常
        return new FacadeResult(ReturnCodeEnum.SERVER_EXCEPTION.getCode());
    }




}

