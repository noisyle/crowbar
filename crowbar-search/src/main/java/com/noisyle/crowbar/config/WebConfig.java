package com.noisyle.crowbar.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.noisyle.crowbar.core.interceptor.WebExceptionResolver;
import com.noisyle.crowbar.core.interceptor.WebHandlerInterceptor;
import com.noisyle.crowbar.core.util.CookieUtils;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.noisyle.crowbar.controller")
public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	public HandlerMapping resourceHandlerMapping() {
		return super.resourceHandlerMapping();
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return super.requestMappingHandlerMapping();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(webHandlerInterceptor());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/message");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieMaxAge(CookieUtils.CURRENT_SESSION);
		localeResolver.setCookieName("locale");
		localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public WebHandlerInterceptor webHandlerInterceptor() {
		return new WebHandlerInterceptor();
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public WebExceptionResolver webExceptionResolver() {
		return new WebExceptionResolver();
	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error/404").setViewName("error/404");
		registry.addViewController("/error/500").setViewName("error/500");
	}

}
