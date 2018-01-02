package com.muyi.proxy;

import com.google.common.collect.ImmutableMap;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Map;

/**
 * @Author: muyi-corp
 * @Date: Created in 12:49 2018/1/2
 * @Description: http://idea.lanyus.com:80 进行代理
 */
@Configuration
public class LanyusProxyConfig {

    @Bean
    public Servlet lanyusProxy(){
        return new ProxyServlet();
    }

    @Bean
    public ServletRegistrationBean proxyServletRegistration(){

        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(lanyusProxy(),"/*");
        Map<String,String> params = ImmutableMap.of(
                "targetUri","http://idea.lanyus.com:80",
                "log",      "true"
        );
        registrationBean.setInitParameters(params);
        return registrationBean;
    }


}
