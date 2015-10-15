package com.noisyle.crowbar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.constant.AdminConstant.Role;
import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.core.util.CookieUtils;
import com.noisyle.crowbar.core.util.CryptoUtils;
import com.noisyle.crowbar.model.Article;
import com.noisyle.crowbar.model.Category;
import com.noisyle.crowbar.model.User;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	protected GridFsTemplate gridFsTemplate;
	
	@RequestMapping(value="", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object hello(HttpServletRequest request, HttpServletResponse response) {
		String count = CookieUtils.getCookieValue(request, "count");
		if(count==null) count = "0";
		count = String.valueOf(Integer.valueOf(count)+1);
		CookieUtils.setCookieValue(request, response, "count", count, null, "/", CookieUtils.CURRENT_SESSION);
		logger.debug("=== 第{}次请求由主机 {}:{} 处理请求", count, request.getLocalName(), request.getLocalPort());
		return "hello world!";
	}
	
	@RequestMapping(value="/init", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String init() {
		mongoTemplate.dropCollection(User.class);
		String pass = CryptoUtils.md5("123456");
		User user = new User();
		user.setLoginname("admin1");
		user.setUsername("管理员1");
		user.setPassword(pass);
		user.setPhone("13"+RandomStringUtils.randomNumeric(11));
		user.setEmail(user.getLoginname()+"@crowbar.com");
		user.setRole(Role.ADMIN.toString());
		mongoTemplate.save(user);
		user = new User();
		user.setLoginname("admin2");
		user.setUsername("管理员2");
		user.setPassword(pass);
		user.setPhone("13"+RandomStringUtils.randomNumeric(11));
		user.setEmail(user.getLoginname()+"@crowbar.com");
		user.setRole(Role.ADMIN.toString());
		mongoTemplate.save(user);
		for(int i = 0;i<100;i++){
			user = new User();
			user.setLoginname("user"+i);
			user.setUsername("用户"+i);
			user.setPassword(pass);
			user.setPhone("13"+RandomStringUtils.randomNumeric(11));
			user.setEmail(user.getLoginname()+"@crowbar.com");
			user.setRole(Role.USER.toString());
			mongoTemplate.save(user);
		}
		
		mongoTemplate.dropCollection(Category.class);
		Category cat0 = new Category();
		cat0.setCategoryName("未分类");
		mongoTemplate.save(cat0);
		Category cat1 = new Category();
		cat1.setCategoryName("栏目1");
		mongoTemplate.save(cat1);
		Category cat11 = new Category();
		cat11.setParentId(cat1.getId());
		cat11.setCategoryName("栏目1-1");
		mongoTemplate.save(cat11);
		Category cat12 = new Category();
		cat12.setParentId(cat1.getId());
		cat12.setCategoryName("栏目1-2");
		mongoTemplate.save(cat12);
		Category cat2 = new Category();
		cat2.setCategoryName("栏目2");
		mongoTemplate.save(cat2);
		Category cat21 = new Category();
		cat21.setParentId(cat2.getId());
		cat21.setCategoryName("栏目2-1");
		mongoTemplate.save(cat21);
		Category cat22 = new Category();
		cat22.setParentId(cat2.getId());
		cat22.setCategoryName("栏目2-2");
		mongoTemplate.save(cat22);

		mongoTemplate.dropCollection(Article.class);
		gridFsTemplate.delete(null);
		
		return "初始化成功";
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public Object users() {
		return mongoTemplate.findAll(User.class);
	}
}
