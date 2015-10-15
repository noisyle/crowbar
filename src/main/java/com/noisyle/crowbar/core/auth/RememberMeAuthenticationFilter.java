package com.noisyle.crowbar.core.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.noisyle.crowbar.constant.AdminConstant;
import com.noisyle.crowbar.core.base.IUser;
import com.noisyle.crowbar.service.LoginService;

public class RememberMeAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private LoginService loginService;

	/**
	 * 这个方法决定了是否能让用户登录
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);

		// 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true
		// 证明是没登陆直接通过记住我功能进来的
		if (!subject.isAuthenticated() && subject.isRemembered()) {

			// 获取session看看是不是空的
			Session session = subject.getSession(true);

			// 随便拿session的一个属性来看session当前是否是空的
			if (session.getAttribute(AdminConstant.SESSION_KEY_USER_CONTEXT) == null) {

				// 如果是空的才初始化，否则每次都要初始化，项目得慢死
				// 这边根据前面的前提假设，拿到的是username
				String loginname = subject.getPrincipal().toString();

				// 在这个方法里面做初始化用户上下文的事情，比如通过查询数据库来设置session值
		        IUser user = loginService.getUserByLoginName(loginname);
		        if(user != null) {
		        	loginService.initUserContext(user);
		        }else{
		        	return false;
		        }
			}
		}

		// 这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered()
		// 让它同时也兼容remember这种情况
		return subject.isAuthenticated() || subject.isRemembered();
	}
}
