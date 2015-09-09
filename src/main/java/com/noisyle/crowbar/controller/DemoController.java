package com.noisyle.crowbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.core.util.CryptoUtils;
import com.noisyle.crowbar.model.User;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value="/init", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String init() {
		mongoTemplate.dropCollection(User.class);
		User user = new User();
		user.setLoginname("admin1");
		user.setUsername("管理员1");
		user.setPassword(CryptoUtils.md5("123456"));
		mongoTemplate.save(user);
		return "初始化成功";
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public Object users() {
		return mongoTemplate.findAll(User.class);
	}
}
