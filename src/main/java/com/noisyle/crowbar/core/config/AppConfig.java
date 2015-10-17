package com.noisyle.crowbar.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;
import com.noisyle.crowbar.core.util.SpringContextHolder;

@Configuration
@PropertySource("classpath:/spring-context.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.noisyle.crowbar.repository", "com.noisyle.crowbar.service" })
public class AppConfig extends AbstractMongoConfiguration {

	@Value("${mongo.host}")
	String mongo_host;
	@Value("${mongo.port}")
	String mongo_port;
	@Value("${mongo.databaseName}")
	String mongo_dbname;

	@Override
	protected String getDatabaseName() {
		return mongo_dbname;
	}

	@Override
	public MongoClient mongo() throws Exception {
		return new MongoClient(mongo_host, Integer.valueOf(mongo_port));
	}

	@Bean
	@DependsOn({"mongoDbFactory", "mappingMongoConverter"})
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}
}
