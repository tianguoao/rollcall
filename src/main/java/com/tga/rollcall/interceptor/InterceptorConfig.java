package com.tga.rollcall.interceptor;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月19日 下午5:16:37
 * Class: InterceptorConfig.java
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer  {
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {}

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    // 注册拦截器，拦截器需要添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**")
         .excludePathPatterns("/rollCall/login", "/rollCall/register","/rollCall/serverState");
    }
}
