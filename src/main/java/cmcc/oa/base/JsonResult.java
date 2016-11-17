package cmcc.oa.base;

/**
 * 响应前端请求
 * 
 * @author zhangxs
 *
 */
public class JsonResult {
	private Boolean success;
	private String message;
	private String encoding = "UTF-8";
	private Object model;

	public JsonResult(Boolean success, String message, Object model) {
		this.success = success;
		this.message = message;
		this.model = model;
		// TODO Auto-generated constructor stub
	}

	public JsonResult() {
		this.success = true;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
}
