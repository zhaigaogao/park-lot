package cmcc.oa.base;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import cmcc.oa.vo.UserInfoVo;

/**
 * Controller 父类
 * 
 * @author renlinggao
 *
 */
@Controller
public class BaseController {

	public static final String SESSION_USER = "userInfo";// 用户属性信息
	public static final String SESSION_LOGIN_IMAGE_CODE = "loginImage";// 登录的验证码

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	public Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 初始化信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@ModelAttribute
	public void preRun(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		this.request = request;
		this.session = session;
		this.response = response;
	}

	/**
	 * 设置session
	 * 
	 * @param user
	 */
	public void setUserInfo(UserInfoVo user) {
		session.setAttribute(SESSION_USER, user);
	}

	/**
	 * 返回session存的用户数据
	 * 
	 * @return
	 */
	public UserInfoVo getUserInfo() {
		return (UserInfoVo) session.getAttribute(SESSION_USER);
	}

	/**
	 * 存放验证码
	 * 
	 * @param code
	 */
	public void setLoginImageCode(String code) {
		session.setAttribute(SESSION_LOGIN_IMAGE_CODE, code);
	}

	/**
	 * 获取登录验证码
	 * 
	 * @return
	 */
	public String getLoginImageCode() {
		String code = (String) session.getAttribute(SESSION_LOGIN_IMAGE_CODE);
		return code != null ? code : "";
	}

	/**
	 * 基于异常处理机制
	 * 
	 * @param request
	 * @param ex
	 * @return
	 * @author renlinggao
	 */
	@ExceptionHandler
	@ResponseBody
	public JsonResult exp(HttpServletRequest request, Exception exception) {
		logger.error(exception.getMessage(), exception);
		String message = "";
		if (exception instanceof NumberFormatException) {
			message = "参数类型错误！";
		} else if (exception instanceof NoSuchRequestHandlingMethodException) {// 404
			message = "路径请求错误！";
		} else if (exception instanceof MissingServletRequestParameterException
				|| exception instanceof TypeMismatchException || exception instanceof HttpMessageNotReadableException) { // 400
			message = "接口请求错误(参数类型不匹配或参数缺失)！";
		} else if (exception instanceof NoSuchAlgorithmException) {
			message = "短信网关异常！";
		} else if (exception instanceof BindException) {
			message = "参数绑定错误！";
		} else if (exception instanceof NullPointerException) {
			message = "参数不可为空！";
		} else if (exception instanceof FileNotFoundException) {
			message = "所选文件不存在！";
		} else if (exception instanceof RuntimeException) {
			message = exception.getMessage().length() <= 20 ? exception.getMessage() : "操作失败";
		} else if (exception instanceof ConnectException) {
			message = "请求连接错误！";
		} else if (exception instanceof MaxUploadSizeExceededException) {
			Long size = (((MaxUploadSizeExceededException) exception).getMaxUploadSize()) / 1024;
			message = "上传文件大小应小于" + size + "KB（" + size / 1024 + "MB）";
		} else {
			message = "系统错误！";
		}
		return new JsonResult(false, "错误原因：" + message, null);
	}
}
