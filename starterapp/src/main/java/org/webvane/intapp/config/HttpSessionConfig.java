package org.webvane.intapp.config;

import org.webvane.common.http.filters.ServletSessionFilter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Configuration
public class HttpSessionConfig {

    static Logger log = LoggerFactory.getLogger(HttpSessionConfig.class);
//
//    @Bean
//    public HttpSessionIdResolver httpSessionIdResolver() {
//        return HeaderHttpSessionIdResolver.xAuthToken();
//    }

    @Autowired
    ServletSessionFilter servletSessionFilter;

    @Bean
    public FilterRegistrationBean servletSessionCacheFilterReg() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter( servletSessionFilter );
        registration.setDispatcherTypes( EnumSet.allOf(DispatcherType.class) );
        registration.addUrlPatterns("/fwk/*","/auth/*","/fls/*");
        return registration;
    }
}
