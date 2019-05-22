package com.dha.dhawebdemo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class DruidConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        return datasource;
    }
    
    //配置Druid监控
    //1.配置管理后台的Servlet
    @Bean
    @SuppressWarnings("all")
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //com.alibaba.druid.support.http.ResourceServlet
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","root");//登录后台时的用户名
        initParams.put("loginPassword","123456");//登录后台时的密码
        initParams.put("allow","");//默认就是允许所有的访问
        initParams.put("deny","");//拒绝
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    //2.配置一个web监控的过滤器
    @Bean
    @SuppressWarnings("all")
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");//不拦截的请求
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

}
