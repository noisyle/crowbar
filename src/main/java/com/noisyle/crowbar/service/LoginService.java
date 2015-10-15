package com.noisyle.crowbar.service;

import org.apache.shiro.SecurityUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noisyle.crowbar.constant.AdminConstant;
import com.noisyle.crowbar.core.base.IUser;
import com.noisyle.crowbar.core.vo.UserContext;
import com.noisyle.crowbar.model.User;
import com.noisyle.crowbar.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    
	public User getUserByLoginName(String loginname) {
		return userRepository.getUserByLoginName(loginname);
	}
	
	public void initUserContext(IUser user) {
    	UserContext uctx = new UserContext(user);
    	uctx.setLoginTime(LocalDate.now().toDate());
    	SecurityUtils.getSubject().getSession().setAttribute(AdminConstant.SESSION_KEY_USER_CONTEXT, uctx);
	}
	
}
