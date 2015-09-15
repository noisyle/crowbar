package com.noisyle.crowbar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noisyle.crowbar.core.base.BaseController;

@Controller
public class IndexController extends BaseController {
	@RequestMapping("/")
	public String index() {
		return "blog/index";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "blog/about";
	}
	
	@RequestMapping("/post")
	public String post() {
		return "blog/post";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "blog/contact";
	}
}
