package com.yanjin.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**添加拦截器，设置拦截的路径（admin下所有访问），
         * 排除直接访问登录页（/admin）和登录post表单的访问（/admin/login）
         * 如果路径包含了static下的文件，记得排除前端需要的静态资源，否则登录页没有样式（此处没有）
         */
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login");
    }
}
