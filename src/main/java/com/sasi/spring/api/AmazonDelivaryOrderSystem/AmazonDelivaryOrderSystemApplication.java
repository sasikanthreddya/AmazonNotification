package com.sasi.spring.api.AmazonDelivaryOrderSystem;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmazonDelivaryOrderSystemApplication {
	private static Log Logger=LogFactory.getLog(AmazonDelivaryOrderSystemApplication.class);
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				Logger.info("ServletContext initialized in main");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				Logger.info("ServletContext destroyed last");
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazonDelivaryOrderSystemApplication.class, args);
	}
}
