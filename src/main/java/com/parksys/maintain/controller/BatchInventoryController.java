package com.parksys.maintain.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.InventoryTtable;
import com.parksys.maintain.service.InventoryTtableService;

@Controller
@RequestMapping("BatchInventory")
public class BatchInventoryController {
	/**
	 * 批量盘点车库信息
	 */
	@Autowired
	private InventoryTtableService invenventabService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * @method 批量盘点插入盘点表
	 * @author zhaieryuan
	 * @param  List<InventoryTtable>
	 * @date   2016/10/14
	 */
	@RequestMapping("/BatchInventoryInsert")
	@ResponseBody
	public JsonResult  BatchInventoryInsert(List<InventoryTtable> inventoryTtables){
		JsonResult result=new JsonResult();
		if(inventoryTtables.size()>0){
			for(InventoryTtable inventoryTtable : inventoryTtables){
				try {
					invenventabService.insert(inventoryTtable);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("批量插入{inventoryTtable}失败！");
				}
			}
			result.setSuccess(true);
			result.setMessage("批量插入盘点信息成功！");
		}else{
			result.setSuccess(false);
			result.setMessage("批量插入盘点信息失败！");
		}
		return result;
	}
	
	/**
	 * @author zhaieryuan
	 * @method 单条记录盘点信息
	 * @param  InventoryTtable
	 * @date   2016/10/14
	 */
	@RequestMapping()
	@ResponseBody
	public JsonResult InventoryGarage(InventoryTtable inventoryTtable){
		JsonResult result = new JsonResult();
		return result;
	}
	
}
