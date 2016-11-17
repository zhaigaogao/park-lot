package cmcc.oa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import cmcc.oa.base.BaseController;

/**
 * 登录过滤器
 * 
 *
 */
public class LoginFilter extends OncePerRequestFilter {

	private Logger logger = Logger.getLogger(this.getClass());

	public String LOGIN_URL = "/login.do";// 登录路径

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		boolean authorized = validateAuthorization(request, response);
		if (authorized) {
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + LOGIN_URL);
		}
	}

	/**
	 * 校验是否已经登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean validateAuthorization(HttpServletRequest request, HttpServletResponse response) {
		String[] notFilter = new String[] { LOGIN_URL, "imageCheck", "loginCheck", "carPark/findLatestCarInfo", "carPark/executeCmd",
				"appointment/insert" };
		String uri = request.getRequestURI();
		logger.debug(uri);
		// .html,.do之外的请求直接放行
		if (!StringUtils.endsWithAny(uri, ".htm", ".do")) {
			return true;
		}

		// 白名单内的请求放行
		for (String s : notFilter) {
			if (uri.indexOf(s) != -1) {
				return true;
			}
		}

		Object object = request.getSession().getAttribute(BaseController.SESSION_USER);
		if (null != object) {
			return true;
		}

		return false;
	}
}
