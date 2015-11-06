package com.noisyle.crowbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.service.ArticleSearchService;

@Controller
@RequestMapping(value="/search")
public class SearchController extends BaseController {
	@Autowired
	private ArticleSearchService articleSearchService;
	
	@RequestMapping(value="/articles")
	@ResponseBody
	public Object searchArticles(@RequestParam(required = false, defaultValue = "") String q) {
		return articleSearchService.searchArticles(q);
	}
	
}
