package com.xier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = { "com.xier"})
@ImportResource(locations={"classpath*:applicationContext.xml"})
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableScheduling
public class WebApplication  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{

	public static void main(String [] args){
		SpringApplication.run(WebApplication.class,args);
	}
	
	@Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);  
    }  
	@Override
    public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(9090);   
    } 
}
