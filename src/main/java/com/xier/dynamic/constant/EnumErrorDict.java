package com.xier.dynamic.constant;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.xier.dynamic.base.IGeneralErrorCode;



/**
 * 
 * <p>
 * 错误信息维护
 * </p>
 * @author lvhui5 2017年6月20日 下午4:15:41
 * @version V1.0
 */
public enum EnumErrorDict {
	
	
	UN_KNOWN_ERROR(IGeneralErrorCode.UN_KNOWN_ERROR,"internal error"),
	DATA_NOT_EXIST(IGeneralErrorCode.DATA_NOT_EXIST,"data not exsit"),
	PARAMS_ERROR(IGeneralErrorCode.PARAMS_ERROR,"params error: {0}"),
	DATA_EXIST(IGeneralErrorCode.DATA_EXIST,"data has exsit"),
	ERR_IPS_DEVICE_MODEL_NOT_EXIT(IGeneralErrorCode.ERR_IPS_DEVICE_MODEL_NOT_EXIT,"dataModel not exsit :{0}"),
	ERR_IPS_INDEX_SEARCH_PARAM_ERROR(IGeneralErrorCode.ERR_IPS_INDEX_SEARCH_PARAM_ERROR,"query params error"),
	ERR_IPS_HBASE_ERROR(IGeneralErrorCode.ERR_IPS_HBASE_ERROR,"hbase operation failed:[{0}]"),
	ERR_IPS_INDEX_INSERT_ERROR(IGeneralErrorCode.ERR_IPS_INDEX_INSERT_ERROR,"create index error:{0}"),
	ERR_IPS_DATABASE_INSERT_ERROR(IGeneralErrorCode.ERR_IPS_DATABASE_INSERT_ERROR,"database insert error:{0}"),
	ERR_IPS_INDEX_SEARCH_ERROR(IGeneralErrorCode.ERR_IPS_INDEX_SEARCH_ERROR,"index search error :{0}"),
	ERR_IPS_DATABASE_SEARCH_ERROR(IGeneralErrorCode.ERR_IPS_DATABASE_SEARCH_ERROR,"search database error:{0}"),
	ERR_IPS_DATABASE_CREATE_ERROR(IGeneralErrorCode.ERR_IPS_DATABASE_CREATE_ERROR,"create database table error:{0}"),
	
	
	
    ;
	private static final String PLACE_HOLDER_0 = "{0}";
    private String key;
    private String description;
    
    /**
     * 全局索引池
     */
    private static Map<String, EnumErrorDict> pool = new HashMap<String, EnumErrorDict>();
    static {
        for (EnumErrorDict et : EnumErrorDict.values()) {
            pool.put(et.key, et);
        }
    }
    
    private EnumErrorDict(String key,String description) {
    	this.key = key;
    	this.description = description;
    }

    public String getDescription() {
        return description;
    }

	public String getKey() {
		return key;
	}
	
	
    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumErrorDict indexByValue(String value) {
        return pool.get(value);
    }

	public static String getErrorInfo(String errorCode, Object[] params) {
		if ("0".equals(errorCode)) {
			return "操作成功！";
		}
		EnumErrorDict hsError = pool.get(errorCode);
		if (hsError != null) {// 有配置对应的错误信息
			String errorInfo = hsError.getDescription();
			if (errorInfo.contains(PLACE_HOLDER_0)) {
				return MessageFormat.format(errorInfo, params);
			} else {
				return MessageFormat.format(PLACE_HOLDER_0, errorInfo);
			}
		} else {
			// 未配置对应的错误信息，直接抛出异常
			// throw new RuntimeException("错误信息为空，错误号[" + errorNo
			// + "]，请确认已配置！");
			return "错误信息为空，错误号[" + errorCode + "]，请确认已配置";
		}
    }
}
