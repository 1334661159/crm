package com.zql.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Author:zhangqinglei
 * Description:spring-boot中乱码问题解决方案
 * Created by qwert on 2018/2/5.
 * Modified By:
 */
@Configuration
public class CustomMVCConverterConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return messageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters ){
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configure){
        configure.favorPathExtension(false);
    }

}
