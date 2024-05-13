package org.ylzl.eden.demo.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @classname: SwaggerConfiguration
 * @description: swagger配置类
 * @date: 2024/5/12 16:13
 * @author: hollis
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("org.ylzl.eden.demo.adapter.user.web"))
			.build();
	}

	Contact contact = new Contact("zy","zydl.cn","123123@qq.com");

	private ApiInfo apiInfo()
	{
		return new ApiInfo("zy的Api文档",
			"测试文档",
			"1.0",
			"zydl.cn",
			contact,
			"Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<>());
	}
}
