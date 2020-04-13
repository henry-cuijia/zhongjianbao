package com.zhongjianbaoapi;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Value("${swagger.enable}")
	private boolean swagger_is_enable;

	/**
	 * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	 */
	@Bean
	public Docket createRestApi() {
		//添加head参数start
//		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = Lists.newArrayList();
//		tokenPar.name("token").description("令牌").modelRef(new ModelRef("string"))
//				.parameterType("header").required(false).build();
//		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2)
							.enable(swagger_is_enable)
							.apiInfo(apiInfo())
							.select()
							.apis(RequestHandlerSelectors.basePackage("com.zhongjianbaoapi.controller"))
							.paths(PathSelectors.any())
							.build()
							.globalOperationParameters(pars)
							.apiInfo(apiInfo());
	}

	/**
	 * @Description: 构建 api文档的信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置页面标题
				.title("中建宝app接口文档")
				// 设置联系人
				.contact(new Contact("宿州吴彦祖", "", "niujiabao@126.com"))
				// 描述
				.description("欢迎访问API接口文档，错误请好好检查请求方式。用户未登录情况下Id传空")
				// 定义版本号
				.version("1.0")
				.build();
	}

}
