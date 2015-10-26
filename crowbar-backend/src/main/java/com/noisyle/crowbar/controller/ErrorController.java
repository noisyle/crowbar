package com.noisyle.crowbar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noisyle.crowbar.core.base.BaseController;

@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {
	@RequestMapping("/404")
	public String error404() {
		return "error/404";
	}
	
	@RequestMapping("/500")
	public String error500() {
		return "error/500";
	}
	
	@RequestMapping("/unauth")
	public String unauth() {
		return "error/unauth";
	}
}
