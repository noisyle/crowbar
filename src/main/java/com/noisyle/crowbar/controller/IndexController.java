package com.noisyle.crowbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.repository.ArticleRepository;

@Controller
public class IndexController extends BaseController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping("/")
	public String index() {
		return "blog/index";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "blog/home";
	}
	
	@RequestMapping("/home/articles")
	@ResponseBody
	public Object homeArticles() {
		return articleRepository.findAll();
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
