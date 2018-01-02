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
 * @Date: Created in 12:39 2018/1/2
 * @Description: 对 www.baidu.com 进行代理
 */
//@Configuration
public class BaiduProxyConfig {
    @Bean
    public Servlet baiduProxy(){
        return new ProxyServlet();
    }

    @Bean
    public ServletRegistrationBean proxyServletRegistration(){

        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(baiduProxy(),"/baidu/*");
        Map<String,String> params = ImmutableMap.of(
                "targetUri","http://baidu.com",
                "log",      "true"
        );
        registrationBean.setInitParameters(params);
        return registrationBean;
    }



}
