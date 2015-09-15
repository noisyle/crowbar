package com.noisyle.crowbar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noisyle.crowbar.core.base.BaseController;

@Controller
public class IndexController extends BaseController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
