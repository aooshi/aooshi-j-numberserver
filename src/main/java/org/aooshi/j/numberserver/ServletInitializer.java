package org.aooshi.j.numberserver;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletInitializer extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NumberServerApplication.class);
	}
}