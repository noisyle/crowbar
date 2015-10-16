package com.noisyle.crowbar.core.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.noisyle.crowbar.core.xss.XssFilter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		context.addFilter("shiroFilter", shiroFilterProxy()).addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST), true, "/*");
		context.addFilter("xssFilter", xssFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,
				"/*");
		context.addFilter("characterEncodingFilter", characterEncodingFilter()).addMappingForUrlPatterns(null, true,
				"/*");
		context.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter()).addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST), true, "/*");
		super.onStartup(context);
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, ShiroConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class };
	}

	private Filter shiroFilterProxy() {
		DelegatingFilterProxy filter = new DelegatingFilterProxy();
		filter.setTargetFilterLifecycle(true);
		return filter;
	}

	private XssFilter xssFilter() {
		XssFilter filter = new XssFilter();
		return filter;
	}

	private Filter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	private Filter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

}
