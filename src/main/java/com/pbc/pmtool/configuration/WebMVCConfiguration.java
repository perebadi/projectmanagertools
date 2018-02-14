package com.pbc.pmtool.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pbc.pmtool.component.DefaultViewAttributeInterceptor;

@Configuration
public class WebMVCConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	@Qualifier("defaultViewAttributeInterceptor")
	private DefaultViewAttributeInterceptor defaultViewAttributeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(defaultViewAttributeInterceptor);
	}
	
}