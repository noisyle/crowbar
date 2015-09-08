package com.noisyle.crowbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.core.util.CryptoUtils;
import com.noisyle.crowbar.model.User;
import com.noisyle.crowbar.repository.UserRepository;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/init", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String init() {
		userRepository.removeAll();
		User user = new User();
		user.setLoginname("admin1");
		user.setUsername("管理员1");
		user.setPassword(CryptoUtils.md5("123456"));
		userRepository.save(user);
		return "初始化成功";
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public Object users() {
		return userRepository.findAll();
	}
}
