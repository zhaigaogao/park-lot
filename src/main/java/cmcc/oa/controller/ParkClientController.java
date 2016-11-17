package cmcc.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.ParkClient;
import cmcc.oa.service.ParkClientService;

/**
 * 出入口管理
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Controller
@RequestMapping("client")
public class ParkClientController {
	@Autowired
	private ParkClientService parkClientService;

	/**
	 * 新增一个入口
	 * 
	 * @param name
	 * @param key
	 * @param type
	 * @param ip
	 * @param remark
	 * @return
	 */
	@RequestMapping("addClient")
	@ResponseBody
	public JsonResult addClient(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "type", required = true, defaultValue = "0") Integer type, String ip, String remark) {
		JsonResult result = new JsonResult();
		ParkClient client = new ParkClient();
		client.setName(name);
		client.setKey(key);
		client.setType(type);
		client.setRemark(remark);
		client.setIp(ip);
		parkClientService.addClient(client);
		return result;
	}

	/**
	 * 编辑入口
	 * @param id
	 * @param name
	 * @param key
	 * @param type
	 * @param ip
	 * @param remark
	 * @return
	 */
	@RequestMapping("editClient")
	@ResponseBody
	public JsonResult editClient(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "type", required = true, defaultValue = "0") Integer type,
			@RequestParam(value = "ip", defaultValue = "") String ip,
			@RequestParam(value = "remark", defaultValue = "") String remark) {
		JsonResult result = new JsonResult();
		ParkClient client = new ParkClient();
		client.setId(id);
		client.setName(name);
		client.setKey(key);
		client.setType(type);
		client.setRemark(remark);
		client.setIp(ip);
		parkClientService.editClient(client);
		return result;
	}

	/**
	 * 删除入口
	 * @param id
	 * @return
	 */
	@RequestMapping("delClient")
	@ResponseBody
	public JsonResult delClient(@RequestParam(value = "id", required = true) Integer id) {
		JsonResult result = new JsonResult();
		ParkClient client = new ParkClient();
		client.setId(id);
		client.setStatus(0);
		parkClientService.editClient(client);
		return result;
	}

	/**
	 * 模糊查询
	 * @param pageNum
	 * @param pageSize
	 * @param search
	 * @return
	 */
	@RequestMapping("findBySearch")
	@ResponseBody
	public JsonResult findBySearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String search) {
		JsonResult result = new JsonResult();
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<ParkClient> clients = parkClientService.findByLike(search);
		PageInfo<ParkClient> pageInfo = new PageInfo<>(clients);
		// 分页结束
		result.setModel(pageInfo);
		return result;
	}

	/**
	 * 检查唯一标识是否存在
	 * @param key
	 * @return
	 */
	@RequestMapping("checkKey")
	@ResponseBody
	public JsonResult checkKey(@RequestParam(value = "key", required = true) String key) {
		JsonResult result = new JsonResult();
		ParkClient client = parkClientService.getByKey(key);
		if(client != null){
			result.setSuccess(false);
			result.setMessage("唯一标识已经存在");
		}
		return result;
	}
}
