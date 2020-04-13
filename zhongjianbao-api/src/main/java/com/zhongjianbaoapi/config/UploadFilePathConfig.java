/*
package com.procurementapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class UploadFilePathConfig implements  WebMvcConfigurer {

    @Value("${upload.filePath}")
    private String filePath;

	@Value("${upload.uploadPath}")
	private String uploadPath;

	//设置虚拟路径
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(filePath).addResourceLocations("file:"+ uploadPath);
	}

}
*/
