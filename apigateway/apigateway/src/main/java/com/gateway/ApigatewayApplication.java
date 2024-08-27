package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	       return builder.routes()
	      .route("jobmodule", r->r.path("/app1/**").uri("lb://jobmodule")) //dynamic routing
	      .route("jobseekermodule", r->r.path("/app2/**").uri("lb://jobseekermodule")) //dynamic routing
	      .build();
	    }
	
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
