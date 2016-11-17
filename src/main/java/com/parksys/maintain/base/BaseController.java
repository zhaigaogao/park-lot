package com.parksys.maintain.base;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.parksys.maintain.entity.MtUser;
import com.parksys.maintain.server.db.MultipleDataSource;

/**
 * Controller 父类
 * 
 * @author zhangxs
 *
 */
@Controller
public class BaseController {

	@Autowired
	private HttpServletRequest request;

	public Logger log = Logger.getLogger(this.getClass());

	@ModelAttribute
	public void preRun() {
		// request.getSession().getAttribute("user");
		MultipleDataSource.setDataSourceKey(null);
	}

	public MtUser getUser() {
		MtUser user = (MtUser) request.getSession().getAttribute("user");
		return user;
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
		log.error(exception.getMessage(),exception);
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
			message = exception.getMessage();
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
