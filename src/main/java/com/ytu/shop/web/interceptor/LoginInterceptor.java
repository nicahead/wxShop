package com.ytu.shop.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class LoginInterceptor extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login/**");
        addInterceptor.excludePathPatterns("/api/**");
        addInterceptor.excludePathPatterns("/admin/sys/**");
        addInterceptor.excludePathPatterns("/css/**");
        addInterceptor.excludePathPatterns("/js/**");
        addInterceptor.excludePathPatterns("/plugins/**");
        addInterceptor.excludePathPatterns("/upload/**");
        //拦截配置
        addInterceptor.addPathPatterns("/**/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();
            //判断是否已有该用户登录的session
            if ((session.getAttribute("admin") != null)||(session.getAttribute("shop") != null)) {
                return true;
            }
            //跳转到登录页
            response.sendRedirect("/login");
            return false;
        }
    }
}