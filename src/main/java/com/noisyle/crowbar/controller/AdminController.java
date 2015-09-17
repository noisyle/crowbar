package com.noisyle.crowbar.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noisyle.crowbar.constant.AdminConstant;
import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.core.datatables.IFormatter;
import com.noisyle.crowbar.core.datatables.PageParam;
import com.noisyle.crowbar.core.exception.GeneralException;
import com.noisyle.crowbar.core.util.CryptoUtils;
import com.noisyle.crowbar.core.vo.ResponseData;
import com.noisyle.crowbar.model.Article;
import com.noisyle.crowbar.model.User;
import com.noisyle.crowbar.repository.ArticleRepository;
import com.noisyle.crowbar.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, @ModelAttribute("user") User user) {
        try {
        	SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getLoginname(), user.getPassword()));
            return ResponseData.buildSuccessResponse(user);
        } catch (UnknownAccountException e) {
            throw new GeneralException("login.wrongUsername", user.getLoginname());
        } catch (IncorrectCredentialsException e) {
            throw new GeneralException("login.wrongPassword");
        }
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().getSession().removeAttribute("user");
		SecurityUtils.getSubject().logout();
		return "admin/login";
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String main(HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		request.setAttribute("user", user);
		request.setAttribute("today", LocalDate.now().toString());
		return "admin/main";
	}
	
	
	// 用户管理
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList() {
		return "admin/user/list";
	}
	
	@RequestMapping(value="/userlist", method=RequestMethod.POST)
	@ResponseBody
	public Object querUserList(@RequestBody PageParam pageParam) {
		pageParam.getColumns()[2].setFormatter(new IFormatter() {
			@Override
			public Object format(Object value) {
				return AdminConstant.Role.getText((String)value);
			}
		});
		return userRepository.getFormatedPage(pageParam);
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("json_role", AdminConstant.Role.getJSONString(0));
		return "admin/user/add";
	}
	
	@RequestMapping(value="/viewuser", method=RequestMethod.GET)
	public String viewUser(Model model, @RequestParam String id) {
		model.addAttribute("user", userRepository.findById(id));
		model.addAttribute("json_role", AdminConstant.Role.getJSONString(0));
		return "admin/user/view";
	}
	
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	@ResponseBody
	public Object saveUser(User user) {
		user.setPassword(CryptoUtils.md5("123456"));
		userRepository.save(user);
		return ResponseData.buildSuccessResponse(user, "保存成功");
	}
	
	@RequestMapping(value="/deluser", method=RequestMethod.POST)
	@ResponseBody
	public Object delUser(User user) {
		userRepository.delete(user.getId());
		return ResponseData.buildSuccessResponse(user.getId(), "删除成功");
	}
	
	
	//文章管理
	@RequestMapping(value="/articlelist", method=RequestMethod.GET)
	public String articleList() {
		return "admin/article/list";
	}
	
	@RequestMapping(value="/articlelist", method=RequestMethod.POST)
	@ResponseBody
	public Object querArticleList(@RequestBody PageParam pageParam) {
		return articleRepository.getPage(pageParam);
	}
	
	@RequestMapping(value="/addarticle", method=RequestMethod.GET)
	public String addArticle(Model model) {
		return "admin/article/add";
	}
	
	@RequestMapping(value="/viewarticle", method=RequestMethod.GET)
	public String viewArticle(Model model, @RequestParam String id) {
		model.addAttribute("article", articleRepository.findById(id));
		return "admin/article/view";
	}
	
	@RequestMapping(value="/savearticle", method=RequestMethod.POST)
	@ResponseBody
	public Object saveArticle(Article article) {
		if(article.getId()==null){
			article.setPublishtime(new Date());
			article.setAuthor((User) SecurityUtils.getSubject().getSession().getAttribute("user"));
		}
		articleRepository.save(article);
		return ResponseData.buildSuccessResponse(article, "保存成功");
	}
	
	@RequestMapping(value="/delarticle", method=RequestMethod.POST)
	@ResponseBody
	public Object delArticle(Article article) {
		articleRepository.delete(article.getId());
		return ResponseData.buildSuccessResponse(article.getId(), "删除成功");
	}
}
