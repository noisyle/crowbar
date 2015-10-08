package com.noisyle.crowbar.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.noisyle.crowbar.constant.AdminConstant;
import com.noisyle.crowbar.core.base.BaseController;
import com.noisyle.crowbar.core.datatables.IFormatter;
import com.noisyle.crowbar.core.datatables.PageParam;
import com.noisyle.crowbar.core.exception.GeneralException;
import com.noisyle.crowbar.core.util.CryptoUtils;
import com.noisyle.crowbar.core.vo.ResponseData;
import com.noisyle.crowbar.core.vo.UserContext;
import com.noisyle.crowbar.model.Article;
import com.noisyle.crowbar.model.Category;
import com.noisyle.crowbar.model.User;
import com.noisyle.crowbar.repository.ArticleRepository;
import com.noisyle.crowbar.repository.CategoryRepository;
import com.noisyle.crowbar.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
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
		SecurityUtils.getSubject().getSession().removeAttribute("uctx");
		SecurityUtils.getSubject().logout();
		return "admin/login";
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String main(HttpServletRequest request) {
		return "admin/main";
	}
	
	
	// 用户管理
	@RequestMapping(value="/userList", method=RequestMethod.GET)
	public String userList() {
		return "admin/user/list";
	}
	
	@RequestMapping(value="/userList", method=RequestMethod.POST)
	@ResponseBody
	public Object querUserList(@RequestBody PageParam pageParam) {
		pageParam.getColumns()[2].setFormatter(new IFormatter() {
			@Override
			public Object format(Object value) {
				return AdminConstant.Role.get((String)value).getText();
			}
		});
		return userRepository.getFormatedPage(pageParam);
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("json_role", AdminConstant.Role.getJSONString(0));
		return "admin/user/add";
	}
	
	@RequestMapping(value="/viewUser", method=RequestMethod.GET)
	public String viewUser(Model model, @RequestParam String id) {
		model.addAttribute("user", userRepository.findById(id));
		model.addAttribute("json_role", AdminConstant.Role.getJSONString(0));
		return "admin/user/view";
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	@ResponseBody
	public Object saveUser(User user) {
		user.setPassword(CryptoUtils.md5("123456"));
		userRepository.save(user);
		return ResponseData.buildSuccessResponse(user, "保存成功");
	}
	
	@RequestMapping(value="/uploadAvatar", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadAvatar(MultipartFile file) {
		GridFSFile gridFSFile = userRepository.uploadAvatar(file);
		return ResponseData.buildSuccessResponse(gridFSFile.getId().toString(), "上传成功");
	}
	
	@RequestMapping(value="/avatar/{id}", method=RequestMethod.GET)
	public void getAvatar(@PathVariable String id, HttpServletResponse response) {
		GridFSDBFile file = userRepository.getAvatar(id);
		if(file!=null){
			response.setContentType(file.getContentType());
			response.setContentLength((new Long(file.getLength()).intValue()));
			try {
				file.writeTo(response.getOutputStream());
			} catch (IOException e) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		}else{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
	
	@RequestMapping(value="/delUser", method=RequestMethod.POST)
	@ResponseBody
	public Object delUser(User user) {
		userRepository.delete(user.getId());
		return ResponseData.buildSuccessResponse(user.getId(), "删除成功");
	}
	
	
	//栏目管理
	@RequestMapping(value="/categoryList", method=RequestMethod.GET)
	public String categoryList() {
		return "admin/category/list";
	}
	
	@RequestMapping(value="/categorys", method=RequestMethod.GET)
	@ResponseBody
	public Object querCategoryList(@RequestParam(required=false) String q) {
		return categoryRepository.querCategoryList(q);
	}
	
	@RequestMapping(value="/saveCategory", method=RequestMethod.POST)
	@ResponseBody
	public Object saveCategory(Category category) {
		if("".equals(category.getParentId())){
			category.setParentId(null);
		}
		categoryRepository.save(category);
		return ResponseData.buildSuccessResponse(category, "保存成功");
	}
	
	@RequestMapping(value="/delCategory", method=RequestMethod.POST)
	@ResponseBody
	public Object delCategory(Category category) {
		categoryRepository.delete(category.getId());
		return ResponseData.buildSuccessResponse(category.getId(), "删除成功");
	}
	
	
	//文章管理
	@RequestMapping(value="/articleList", method=RequestMethod.GET)
	public String articleList() {
		return "admin/article/list";
	}
	
	@RequestMapping(value="/articleList", method=RequestMethod.POST)
	@ResponseBody
	public Object querArticleList(@RequestBody PageParam pageParam) {
		return articleRepository.getPage(pageParam);
	}
	
	@RequestMapping(value="/addArticle", method=RequestMethod.GET)
	public String addArticle(Model model) {
		return "admin/article/add";
	}
	
	@RequestMapping(value="/viewArticle", method=RequestMethod.GET)
	public String viewArticle(Model model, @RequestParam String id) {
		model.addAttribute("article", articleRepository.findById(id));
		return "admin/article/view";
	}
	
	@RequestMapping(value="/saveArticle", method=RequestMethod.POST)
	@ResponseBody
	public Object saveArticle(Article article, String categoryId) {
		Category category = categoryRepository.findById(categoryId);
		article.setCategory(category);
		if(article.getId()==null){
			article.setPublishtime(new Date());
			article.setAuthor((User) ((UserContext) SecurityUtils.getSubject().getSession().getAttribute("uctx")).getUser());
		}else{
			Article article_db = articleRepository.findById(article.getId());
			article.setAuthor(article_db.getAuthor());
			article.setPublishtime(article_db.getPublishtime());
		}
		articleRepository.save(article);
		return ResponseData.buildSuccessResponse(article, "保存成功");
	}
	
	@RequestMapping(value="/delArticle", method=RequestMethod.POST)
	@ResponseBody
	public Object delArticle(Article article) {
		articleRepository.delete(article.getId());
		return ResponseData.buildSuccessResponse(article.getId(), "删除成功");
	}
}
