package com.test.confxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ConfxxApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ConfxxApplication.class, args);
	}

	@Bean //SpringBoot中使用和SpringMVC中同样的filter功能
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		//1.指定对应的Filter类
		registrationBean.setFilter(new HttpFilter());

		//2.定义当前要拦截的url
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
