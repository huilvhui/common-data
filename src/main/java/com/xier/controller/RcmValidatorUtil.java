package com.xier.controller;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


public class RcmValidatorUtil {

	public static void validatorResult(BindingResult result) {
		StringBuffer validateError = new StringBuffer();
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for (ObjectError error : errorList) {
				String field = ((FieldError) error).getField();
				validateError.append(field + ":" + error.getDefaultMessage() + ";");
			}
			throw ErrorCode.PARAMS_ERROR.throwException(validateError.toString());
		}

	}

}
