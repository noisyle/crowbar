package com.noisyle.crowbar.core.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.noisyle.crowbar.core.auth.MongoDBUserRealm;
import com.noisyle.crowbar.core.auth.RememberMeAuthenticationFilter;

@Configuration
public class ShiroConfig {

	@Bean
	public MongoDBUserRealm userRealm() {
		return new MongoDBUserRealm();
	}
	
	@Bean
	@DependsOn({"userRealm"})
	public DefaultWebSecurityManager securityManager() {
		return new DefaultWebSecurityManager(userRealm());
	}
	
	@Bean
	public RememberMeAuthenticationFilter rememberMeFilter() {
		return new RememberMeAuthenticationFilter();
	}
	
	@Bean
	@DependsOn({"securityManager"})
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/admin/login");
		shiroFilter.setSuccessUrl("/admin");
		shiroFilter.setUnauthorizedUrl("/error/unauth");
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/admin/login", "anon");
		filterChainDefinitionMap.put("/admin/**", "rememberMeFilter, roles[\"ADMIN\"]");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
