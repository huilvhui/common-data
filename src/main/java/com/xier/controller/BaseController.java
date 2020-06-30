package com.xier.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * 标准对外提供资源api
 * @see  https://open.hikvision.com/docs/374c2fb57cfb086d6935fe38f9784fd9
 * </p>
 * @author lvhui5 2018年4月13日 下午3:07:16
 * @version V1.0
 */

@Validated
@RestController
@RequestMapping("/api")
public class PubApiResourceController{

	
	@RequestMapping(value = "irds/v1/resource/resource", method = RequestMethod.POST)
	public ResultData resource(@RequestBody  @Validated ApiResQuery req, BindingResult result,
								   HttpServletRequest request, HttpServletResponse response) throws RcmException {
		log.debug(HikLog.toLog("start: /rcm/api/irds/v2/resource/resourcesByParams"));
		RcmValidatorUtil.validatorResult(result);
		BaseResp resp = pubApiResourceService.resource(req);
		return ErrorCode.SUCCESS.returnResultData(new ResultDTO(resp));
	}

	
}
