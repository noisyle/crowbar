package com.noisyle.crowbar.core.interceptor;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.noisyle.crowbar.core.exception.GeneralException;
import com.noisyle.crowbar.core.vo.ResponseData;

public class WebExceptionResolver implements HandlerExceptionResolver {

    final private static Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);
    
    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;
    
    @Autowired
    @Qualifier("localeResolver")
    private LocaleResolver localeResolver;

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {
        ModelAndView model = new ModelAndView();
        String uri = request.getRequestURI();
        String message;
        if (ex instanceof GeneralException) {
        	GeneralException gex = (GeneralException) ex;
        	Locale locale = localeResolver.resolveLocale(request);
        	message = messageSource.getMessage(gex.getMessage(), gex.getArgs(), gex.getMessage(), locale);
        } else {
        	message = ex.getMessage();
        }
        logger.error("Controller中拦截到异常: {}\n--method:\"{}\"\n--url:\"{}\"", message, object, uri, ex);
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, Object> map = new HashMap<>();
            map.put("status", ResponseData.Status.ERROR.toString());
            map.put("message", message);
            model.setView(view);
            view.setAttributesMap(map);
        } else {
        	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            model.setViewName("error/500");
            model.addObject("e", ex);
            model.addObject("message", message);
        }
        return model;
    }

}
