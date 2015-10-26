package com.noisyle.crowbar.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie操作工具类
 */
public class CookieUtils {

	/** cookie保存一年时的maxAge值 */
	public static final int ONE_YEAR = 31536000;
	/** cookie保存一月时的maxAge值 */
	public static final int ONE_MONTH = 2592000;
	/** cookie保存一天时的maxAge值 */
	public static final int ONE_DAY = 86400;
	/** cookie保存一小时时的maxAge值 */
	public static final int ONE_HOUR = 3600;
	/** cookie保存半小时时的maxAge值 */
	public static final int HALF_HOUR = 1800;
	/** cookie保存在浏览器进程的maxAge值 */
	public static final int CURRENT_SESSION = -1;

	/**
	 * 根据名称获取cookie对象
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param name
	 *            cookie的名字
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		Cookie cookie = null;

		if (cookies == null) {
			return cookie;
		}

		for (Cookie tmp : cookies) {
			if (tmp == null)
				continue;
			if (tmp.getName().equalsIgnoreCase(name)) {
				cookie = tmp;
				break;
			}
		}
		return cookie;
	}

	/**
	 * 根据名称获取cookie值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie的名字
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		String value = null;
		if (cookie != null) {
			value = cookie.getValue();
		}
		return value;
	}

	/**
	 * 设置浏览器cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param cookie
	 *            Cookie对象
	 */
	public static void setCookie(HttpServletResponse response, Cookie cookie) {
		if (response != null && cookie != null) {
			response.addCookie(cookie);
		}
	}

	/**
	 * 设置浏览器cookie值，超时时间为会话期间有效
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie的名字
	 * @param value
	 *            cookie的值
	 */
	public static void setCookieValue(HttpServletRequest request, HttpServletResponse response, String name,
			String value) {
		setCookieValue(request, response, name, value, null, null, CURRENT_SESSION);
	}

	/**
	 * 设置浏览器cookie值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie的名字
	 * @param value
	 *            cookie的值
	 * @param domain
	 *            cookie有效域，默认当前主机
	 * @param path
	 *            cookie有效路径，默认当前路径
	 * @param maxAge
	 *            cookie保存时间，以秒为单位
	 */
	public static void setCookieValue(HttpServletRequest request, HttpServletResponse response, String name,
			String value, String domain, String path, int maxAge) {
		Cookie cookie = getCookie(request, name);

		if (cookie != null) {
			cookie.setValue(value);
		} else {
			cookie = new Cookie(name, value);
		}
		if (domain != null)
			cookie.setDomain(domain);
		if (domain != path)
			cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
}
