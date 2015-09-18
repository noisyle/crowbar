package com.noisyle.crowbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.repository.ArticleRepository;

@Controller
public class IndexController extends BaseController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "blog/index";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "blog/home";
	}
	
	@RequestMapping(value="/home/articles", method=RequestMethod.GET)
	@ResponseBody
	public Object homeArticles() {
		return articleRepository.findAll();
	}
	
	@RequestMapping(value="/article", method=RequestMethod.GET)
	public String articlePage() {
		return "blog/article";
	}
	
	@RequestMapping(value="/article/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Object article(@PathVariable String id) {
		return articleRepository.findById(id);
	}
	
	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String about() {
		return "blog/about";
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String contact() {
		return "blog/contact";
	}
}
