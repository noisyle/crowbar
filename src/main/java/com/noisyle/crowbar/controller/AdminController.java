package com.noisyle.crowbar.controller;

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
import com.noisyle.crowbar.core.exception.GeneralException;
import com.noisyle.crowbar.core.pagination.PageParam;
import com.noisyle.crowbar.core.vo.ResponseData;
import com.noisyle.crowbar.model.User;
import com.noisyle.crowbar.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	private UserRepository userRepository;
	
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
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList() {
		return "admin/user/list";
	}
	
	@RequestMapping(value="/userlist", method=RequestMethod.POST)
	@ResponseBody
	public Object querUserList(@RequestBody PageParam pageParam) {
		return userRepository.getPage(pageParam);
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
	public Object save(User user) {
		userRepository.save(user);
		return ResponseData.buildSuccessResponse(user, "保存成功");
	}
	
	@RequestMapping(value="/deluser", method=RequestMethod.POST)
	@ResponseBody
	public Object del(User user) {
		userRepository.delete(user.getId());
		return ResponseData.buildSuccessResponse(user.getId(), "删除成功");
	}
}
