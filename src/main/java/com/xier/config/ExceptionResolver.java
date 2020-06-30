package com.xier.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;


@Configuration
public class HikExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(HikExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
		log.error(HikLog.toLog(ErrorCode.REST_TEMP_ERROR.getErrorCode(), "unknown error") , ex);
		ResultData resultData = new ResultData();
		if (ex instanceof RcmException) {
			RcmException resultException = (RcmException) ex;
			resultData.setCode(resultException.getCode());
			resultData.setMsg(resultException.getMsg());
			response.setStatus(resultException.getStatus());
		} else if (ex instanceof ConstraintViolationException) {
			ConstraintViolationException resException = (ConstraintViolationException) ex;
			resultData.setCode(ErrorCode.PARAMS_ERROR.getErrorCode());
			String msg = "";
			Set<ConstraintViolation<?>> sets = resException.getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : sets) {
				msg += constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage() + ";";
			}
			resultData.setMsg(msg);
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (ex instanceof MissingServletRequestParameterException) {
			MissingServletRequestParameterException msgex = (MissingServletRequestParameterException) ex;
			resultData.setCode(ErrorCode.PARAMS_ERROR.getErrorCode());
			resultData.setMsg("url error " + msgex.getMessage());
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		} else if (ex instanceof BeansException) {
			BeansException msgex = (BeansException) ex;
			resultData.setCode(ErrorCode.PARAMS_ERROR.getErrorCode());
			resultData.setMsg("param error " + msgex.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (ex instanceof JSONException) {
			JSONException resultException = (JSONException) ex;
			resultData.setCode(ErrorCode.PARAMS_ERROR.getErrorCode());
			resultData.setMsg(ErrorCode.PARAMS_ERROR.getMessage() + ":" + resultException.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (ex instanceof SQLException) {
			log.error(HikLog.toLog(ErrorCode.SQL_OPERATE_ERROR.getErrorCode(), "SQLException"), ex);
			resultData.setCode(ErrorCode.SQL_OPERATE_ERROR.getErrorCode());
			resultData.setMsg(ErrorCode.SQL_OPERATE_ERROR.getMessage());
			response.setStatus(ErrorCode.SQL_OPERATE_ERROR.getState());
		} else if(ex instanceof HttpRequestMethodNotSupportedException){
			HttpRequestMethodNotSupportedException resException = (HttpRequestMethodNotSupportedException)ex;
			resultData.setCode(ErrorCode.METHOD_NOT_ALLOWED.getErrorCode());
			resultData.setMsg(resException.getMessage());
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		} else if(ex instanceof ClientAbortException) {
			ClientAbortException clientAbortException = (ClientAbortException)ex;
			resultData.setCode(ErrorCode.REST_TEMP_ERROR.getErrorCode());
			resultData.setMsg(clientAbortException.getMessage());
			response.setStatus(ErrorCode.REST_TEMP_ERROR.getState());
		}
		else if(ex instanceof IOException) {
			IOException ioException = (IOException)ex;
			resultData.setCode(ErrorCode.REST_TEMP_ERROR.getErrorCode());
			resultData.setMsg(ioException.getMessage());
			response.setStatus(ErrorCode.REST_TEMP_ERROR.getState());
		}
		else {
			//log.error(HikLog.toLog(ErrorCode.REST_TEMP_ERROR .getErrorCode(),"[unknown error]")); 
			resultData.setCode(ErrorCode.UN_KNOWN_ERROR.getErrorCode());
			resultData.setMsg(ErrorCode.UN_KNOWN_ERROR.getMessage());
			response.setStatus(ErrorCode.UN_KNOWN_ERROR.getState());
		}

		response.addHeader("x-frame-options","SAMEORIGIN"); 
		
		writeAjaxResponse(response, resultData);

		return new ModelAndView();
	}

	public void writeAjaxResponse(HttpServletResponse response, ResultData resultData) {
		response.setContentType("text/json;charset=utf-8");
		/*PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(resultData));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		ByteArrayInputStream bais;
        try {
	        bais = new ByteArrayInputStream(JSON.toJSONString(resultData).getBytes("UTF-8"));
	        OutputStream output=response.getOutputStream();
			int len = -1;
			byte[] buf = new byte[1024];
			while((len=bais.read(buf,0,1024))!=-1){
				output.write(buf,0,len);
			}
			output.flush();
        } catch (Exception e) {
	        e.printStackTrace();
        }	
	}

	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {

		return new HikExceptionResolver();
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
