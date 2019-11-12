package com.st.bo;


import java.util.HashMap;
import java.util.Map;

public class FacadeResult {
	private Integer status;
	private String errmsg;
	private Object data;// 借口返回的数据对象
	private long results;

	/**
	 * 成功时返回 默认返回0 表示成功 默认信息 success
	 */
	public FacadeResult(Object data) {
		this.status = 200;
		this.errmsg = "";
		this.data = data;
		this.results = 0;
	}


	public FacadeResult(Object data,long results) {
		this.status = 200;
		this.errmsg = "";
		this.data = data;
		this.results = results;
	}



	/**
	 * 错误时返回 指定信息
	 * @param status
	 * @param message
	 */
	public FacadeResult(Integer status, String message) {
		this.status = status;
		this.errmsg = message;
		this.data = null;
		this.results = 0;
	}

	/**
	 * 全量构造
	 * @param status
	 * @param message
	 * @param data
	 */
	public FacadeResult(Integer status, String message, Object data) {
		this.status = status;
		this.errmsg = message;
		this.data = data;
		this.results = 0;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}




	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public long getResults() {
		return results;
	}


	public void setResults(long results) {
		this.results = results;
	}


}