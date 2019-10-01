package com.sampleapp.paymentstarterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class PaymentStarterServiceApplication {

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Payment").select()
                .apis(RequestHandlerSelectors.basePackage("com.sampleapp.paymentstarterservice"))
                .paths(PathSelectors.ant("/payment/*")).build().apiInfo(new ApiInfo("Payment Starter Services",
                        "A set of services to post transaction details to another banking services", "1.0.0", null,
                        new Contact("Ashwani Kuntal", "https://www.linkedin.com/in/ashwani-kuntal-6239244b/", null),null, null));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentStarterServiceApplication.class, args);
	}
	
	@Bean 
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
