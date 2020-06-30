package com.xier.rcm.controller

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;


@Configuration
public class HValidator {

	@Bean
	public Validator validator() throws ClassNotFoundException {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		Class<?> providerClass = Class.forName("org.hibernate.validator.HibernateValidator");
		localValidatorFactoryBean.setProviderClass(providerClass);
		return localValidatorFactoryBean;
	}

	@Bean("webBindingInitializer")
	public WebBindingInitializer initializer(Validator validator) {
		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setValidator(validator);
		return initializer;
	}

}
