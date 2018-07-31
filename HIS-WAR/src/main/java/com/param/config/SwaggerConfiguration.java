package com.param.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private ApiInfo apiInfo(String title, String description) {

		return new ApiInfoBuilder().title(title).description(description)
				.termsOfServiceUrl("http://springfox.io").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("1.0").build();
	}

	/*@Bean
	public Docket lisGlobalApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("LIS_Global").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.lis.global.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo("LIS", "Laboratory Information System(LIS) API'S FOR GLOBAL MASTERS."));

	}

	@Bean
	public Docket lisUnitApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("LIS_Unit").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.lis.unit.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo("LIS", "Laboratory Information System(LIS) API'S FOR UNIT MASTERS."));
	}

	@Bean
	public Docket lisTransactiontApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("LIS_Transaction").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.lis.transaction.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo("LIS", "Laboratory Information System(LIS) API'S FOR TRANSACTIONS."));
	}

	@Bean
	public Docket lisTransactiontApiMicro() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("LIS_Transaction_Microbiology").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.lis.microbiology.transaction.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo("LIS", "Laboratory Information System(LIS) API'S FOR Microbiology."));
	}
	
/
	
	@Bean
	public Docket PharmacyApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("Pharmacy", "API for Pharmacy Module of HIS Application")).groupName("Pharmacy").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.pharmacy")).paths(PathSelectors.any()).build();
	}
	
	*/
	
	@Bean
	public Docket GlobalApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("Global", "API for Global Module of HIS Application")).groupName("Global").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.global")).paths(PathSelectors.any()).build();

	}
	
	@Bean
	public Docket InventoryApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("Inventory", "API for Inventory Module of HIS Application")).groupName("Inventory").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.inventory")).paths(PathSelectors.any()).build();

	}
	
	@Bean
	public Docket ProcurementApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("Procurement", "API for Procurement Module of HIS Application")).groupName("Procurement").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.procurement")).paths(PathSelectors.any()).build();

	}
	
	@Bean
	public Docket RabbitmqApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo("RabbitMQ", "API for RabbitMQ Module of HIS Application")).groupName("RabbitMQ").select()
				.apis(RequestHandlerSelectors.basePackage("com.param.rabbitmq")).paths(PathSelectors.any()).build();

	}
}