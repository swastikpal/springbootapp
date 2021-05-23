package com.gcdc.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gcdc.sample.interceptor.PetServiceInterceptor;

@Component
public class PetStoreAppConfig implements WebMvcConfigurer {

	@Autowired
	PetServiceInterceptor petServiceInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(petServiceInterceptor);
		//WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
