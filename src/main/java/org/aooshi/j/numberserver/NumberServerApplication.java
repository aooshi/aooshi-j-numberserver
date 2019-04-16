package org.aooshi.j.numberserver;

import java.util.ArrayList;
import java.util.List;

import org.aooshi.j.numberserver.filters.BasicAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//org.springframework.web.client.RestTemplate

@SpringBootApplication
public class NumberServerApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(NumberServerApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<BasicAuthorizationFilter> filterRegistrationBean() 
	{
        FilterRegistrationBean<BasicAuthorizationFilter> registrationBean = new FilterRegistrationBean<BasicAuthorizationFilter>();
        BasicAuthorizationFilter basicAuthFilter = new BasicAuthorizationFilter(); 
        registrationBean.setFilter(basicAuthFilter); 
        //
        List<String> urlPatterns = new ArrayList<String>(); 
        urlPatterns.add("/*"); 
        registrationBean.setUrlPatterns(urlPatterns); 
        return registrationBean;
	}
}
