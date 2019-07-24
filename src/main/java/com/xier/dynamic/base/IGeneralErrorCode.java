package com.xier.dynamic.base;


public interface IGeneralErrorCode {
	
	String SUCCESS = "0";
	String SUCCESS_MSG = "success";
	

	
	/**
	 * 未知错误
	 */
	String UN_KNOWN_ERROR = "100000";
	/**
	 * 未知错误msg
	 */
	String UN_KNOWN_ERROR_MSG = "unknown error";
	
	/**
	 * 数据不存在
	 */
	String DATA_NOT_EXIST = "100001";
	
	/**
	 * 参数错误
	 */
	String PARAMS_ERROR = "100002";
	
	/**
	 * 数据已存在
	 */
	String DATA_EXIST = "100003";
	
	/**
	 * 无权限调用接口
	 */
	String REQUEST_FORBIDDEN = "100004"; 
	
	/**
	 * 秘钥协商失效
	 */
	String NEGOTIATION_OUTDATE = "100005";
	
	/**
	 * 数据加密失败
	 */
	String DATA_ENCRYPT_FAILED = "100006";
	
	/**
	 * 数据解密失败
	 */
	String DATA_DECRYPT_FAILED = "100007";

}
