package com.noisyle.crowbar.core.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.noisyle.crowbar.constant.AdminConstant;
import com.noisyle.crowbar.core.base.IUser;
import com.noisyle.crowbar.core.vo.UserContext;
import com.noisyle.crowbar.service.LoginService;

public class MongoDBUserRealm extends AuthorizingRealm {
    final protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 UsernamePasswordToken 类型的 Token
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        IUser user = loginService.getUserByLoginName(token.getUsername());
        if(user != null) {
        	loginService.initUserContext(user);
            return new SimpleAuthenticationInfo(user.getLoginname(), user.getPassword(), getName());
        } else {
            return null;
        }
    }

    /**
     * 授权操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	IUser user = ((UserContext) SecurityUtils.getSubject().getSession().getAttribute(AdminConstant.SESSION_KEY_USER_CONTEXT)).getUser();
    	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    	if(user.getRole()!=null){
    		info.addRole(user.getRole());
    	}
        return info;
    }

}
