package com.noisyle.crowbar.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.noisyle.crowbar.core.util.SpringContextHolder;

@Configuration
@PropertySource("classpath:/spring-context.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@ComponentScan(basePackages = { "com.noisyle.crowbar.repository", "com.noisyle.crowbar.service" })
public class AppConfig extends AbstractMongoConfiguration {

	@Autowired
	Environment env;

	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongo.databaseName");
	}

	@Override
	public MongoClient mongo() throws Exception {
		String host = env.getProperty("mongo.host");
		int port = env.getProperty("mongo.port", Integer.class);
		String username = env.getProperty("mongo.username");
		String password = env.getProperty("mongo.password");
		String database = env.getProperty("mongo.databaseName");
		ServerAddress addr = new ServerAddress(host, port);
		if (username != null && password != null) {
			List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
			MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
			credentialsList.add(credential);
			return new MongoClient(addr, credentialsList);
		} else {
			return new MongoClient(addr);
		}
	}

	@Bean
	@DependsOn({ "mongoDbFactory", "mappingMongoConverter" })
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
