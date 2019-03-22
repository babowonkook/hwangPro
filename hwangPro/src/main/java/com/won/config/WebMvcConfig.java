package com.won.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.interceptor.LogInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	FilePathProperties filePathProperties;
	
	@Value("${server.port}")
	private String port;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
		        .addPathPatterns("/**")
		        .excludePathPatterns("/test/**/")
		        .excludePathPatterns("/users/login");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	log.debug("#########################{}",filePathProperties.getUploadUrl());
    	log.debug("#########################{}",port);
    	registry.addResourceHandler("/static/**").addResourceLocations(filePathProperties.getUploadUrl());//file:/home/webmaster/FILE/
    	registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
    	WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
    	ObjectMapper objectMapper = new ObjectMapper();
    	SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
    	objectMapper.setDateFormat(smt);
    	converter.setObjectMapper(objectMapper);
    	//设置中文编码格式
    	List<MediaType> list = new ArrayList<MediaType>();
    	list.add(MediaType.APPLICATION_JSON_UTF8);
    	converter.setSupportedMediaTypes(list);
        // Add settings to converter, builder
        converters.add(converter);
    }
}
