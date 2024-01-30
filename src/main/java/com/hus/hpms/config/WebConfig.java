package com.hus.hpms.config;

import com.hus.hpms.interceptor.AdminCheckInterceptor;
import com.hus.hpms.interceptor.LoginCheckInterceptor;
import com.hus.hpms.interceptor.MasterCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login", "/logout", "/css/**", "/*.ico", "/error", "/register",
                        "/api/**"
                );

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(3)
                .addPathPatterns("/master", "/request/update/**", "/api/request/**", "/request/create");

        registry.addInterceptor(new MasterCheckInterceptor())
                .order(4)
                .addPathPatterns("/master");
    }
}
