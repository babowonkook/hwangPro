package com.won.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "filepath")
@Data
public class FilePathProperties {
	private String download;
	private String uploadUrl;
	private String upload;
	private String separator;
	private String rootpath;

}
