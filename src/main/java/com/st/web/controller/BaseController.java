package com.st.web.controller;


import com.st.bo.FacadeResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public abstract class BaseController<E> {

	private static Logger logger = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody
	FacadeResult runtimeExceptionHandler(RuntimeException runtimeException) {

		//捕获异常，对不同异常进行处理

		logger.error("controller runtimeException:" + runtimeException.getLocalizedMessage());
		runtimeException.printStackTrace();
		return generateException(1002,"SERVER ERROR");
	}
	/**
	 * 正常返回数据
	 *
	 * @return
	 */
	public FacadeResult generateData(Integer result) {
		return new FacadeResult( result);
	}
	/**
	 * 正常返回数据
	 *
	 * @return
	 */
	public FacadeResult generateData(E data) {
		return new FacadeResult(data);
	}


	/**
	 * 返回异常信息
	 *
	 * @return
	 */
	public FacadeResult generateException(Integer result) {
		return new FacadeResult(result);
	}
	/**
	 * 返回异常信息 自定义
	 *
	 * @return
	 */
	public FacadeResult generateException(Integer result, String message) {
		return new FacadeResult(result, message);
	}
}
