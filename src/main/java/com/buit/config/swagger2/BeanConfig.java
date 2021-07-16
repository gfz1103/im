package com.buit.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.sql.Timestamp;

/**
* @ClassName: BeanConfig
* @Description: 接口文档配置
* @author 神算子
* @date 2020年4月26日 下午4:09:06
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class BeanConfig {
	
	@Bean
	public  ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("云his系统").description("敢不敢做一个能上生产的系统，没有撤退可言。。兽人永不为奴。。")
				.termsOfServiceUrl("http://hisdev.51app.com/api").version("1.0").build();
	}
	@Bean(value = "defaultApi7")
	public Docket defaultApi7() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(Timestamp.class, String.class)
				.directModelSubstitute(Date.class, String.class)
				.apiInfo(apiInfo())
				.groupName("住院医生")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.buit.cis.dctwork.controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}
	@Bean(value = "defaultApi10")
	public Docket defaultApi10() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(Timestamp.class, String.class)
				.directModelSubstitute(Date.class, String.class)
				.apiInfo(apiInfo())
				.groupName("住院管理")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.buit.cis.ims.controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}
	@Bean(value = "defaultApi14")
	public Docket defaultApi14() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(Timestamp.class, String.class)
				.directModelSubstitute(Date.class, String.class)
				.apiInfo(apiInfo())
				.groupName("住院护士")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.buit.cis.nurse.controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}
	@Bean(value = "defaultApi15")
	public Docket defaultApi15() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(Timestamp.class, String.class)
				.directModelSubstitute(Date.class, String.class)
				.apiInfo(apiInfo())
				.groupName("床位预约")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.buit.cis.bedresev.controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}
}
