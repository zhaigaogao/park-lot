package com.parksys.maintain.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.entity.InventoryRecord;
import com.parksys.maintain.entity.InventoryTtable;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;
import com.parksys.maintain.service.InventoryRecordService;
import com.parksys.maintain.service.InventoryTtableService;
import com.parksys.maintain.service.VehicleOutService;

@Controller
@RequestMapping("inventoryGarage")
public class InventoryGarageController extends BaseController {
	/**
	 * @author zhaieryuan
	 * @method 盘点接口
	 * @Date   2016/10/17
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private InventoryTtableService inventoryTtableService;
	@Autowired
	private GarageTableService garageTableService;
	@Autowired
	private GarageHistoryService garageHistoryService;
	@Autowired 
	private InventoryRecordService inventoryRecordService;
	@Autowired
	private VehicleOutService vehicleOutService;
	
	
	/**
	 * 
	 * @param   carNumber
	 * @return  JsonResult
	 * @author  zhaieryuan
	 * @method  车辆盘点接口
	 */
	
	@RequestMapping("/parkInventoryGarage")
	@ResponseBody
	public JsonResult InventoryGarage(String inventoryTime){
		JsonResult result = new JsonResult();
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//(盘点表到车库表的记录进行盘点)
		//获取盘点记录表的id最大的盘点记录信息   如果盘点结束时间为空则为上次盘点未关闭  ,否则获取盘点从上次盘点结束之后的所有盘点信息
		InventoryRecord inventoryRecord =inventoryRecordService.selectMaxId();
		if(inventoryRecord.getInventoryEndTime() != null){
			//从盘点表中获取上次盘点结束之后录入的盘点信息
			InventoryTtable inventoryTtable=new InventoryTtable();
			inventoryTtable.setInventoryTime(inventoryRecord.getInventoryEndTime());
			List<InventoryTtable> inventoryTtables=inventoryTtableService.selectListByInventoryTime(inventoryTtable);
			for(InventoryTtable ParamInventoryTtable : inventoryTtables){
				//判断车库表中是否有该记录的盘点信息
				GarageTable garageTable=new GarageTable();
				garageTable.setPlateNumber(ParamInventoryTtable.getCarNumber());
				if(garageTableService.GarageTableIscontain(garageTable).getSuccess()){
					//数据正常，判断车库表中车辆信息的入库时间和出库时间
					ParamInventoryTtable.setInventoryStatus("1:盘点正常");
					inventoryTtableService.updateByPrimaryKey(ParamInventoryTtable);
				}else {
					//车库历史表中查询车辆信息
					GarageHistory garageHistory=new GarageHistory();
					garageHistory.setPlateNumber(garageTable.getPlateNumber());
					//历史表中查询最近一条该车牌号的历史信息
					garageHistory=garageHistoryService.selectNearlyGarageHistory(garageHistory);
					if(garageHistory != null){
						//说明该车辆已经出库,在盘点记录表中标记盘点状态
						InventoryTtable inventoryTtable2=new InventoryTtable();
						inventoryTtable2.setCarNumber(garageHistory.getPlateNumber());
						inventoryTtable2=inventoryTtableService.selectSingle(inventoryTtable2);
						inventoryTtable2.setInventoryStatus("2:盘点期间车辆出库");
						inventoryTtableService.updateByPrimaryKey(inventoryTtable2);
					}else {
						//盘点人员盘点车库信息错误，车牌号码登记错误
						ParamInventoryTtable.setInventoryStatus("3:数据录入异常");
						inventoryTtableService.updateByPrimaryKey(ParamInventoryTtable);
						logger.warn("盘点信息错误，数据录入异常！");
					}
				}
			}
			//如果执行盘点表到车库表返回结果为true,再执行车库表到盘点表的信息盘点
			//(车库表到盘点的信息盘点)
			GarageTable paramGarageTable=new GarageTable();
			List<GarageTable> garageTables=garageTableService.SelectGarageTableList(paramGarageTable);
			for(GarageTable ParamGarageTable : garageTables){
				InventoryTtable inventoryTtable2=new InventoryTtable();
				inventoryTtable2.setCarNumber(ParamGarageTable.getPlateNumber());
				if(inventoryTtableService.inventoryTtableIsContain(inventoryTtable2).getSuccess()){
					ParamGarageTable.setDataSources("1:盘点正常");
					ParamGarageTable.setParkStatus("1:盘点正常");
					garageTableService.updateByPrimaryKey(ParamGarageTable);
				}else{
					GarageHistory nearlyGarageTableHistory=new GarageHistory();
					nearlyGarageTableHistory.setPlateNumber(ParamGarageTable.getPlateNumber());
					//查询历史表中车辆的入库时间和该历史表的出库时间一致的信息
					nearlyGarageTableHistory.setStorageTime(ParamGarageTable.getStorageTime());
					nearlyGarageTableHistory=garageHistoryService.selectNearlyGarageHistory(nearlyGarageTableHistory);
	                if(nearlyGarageTableHistory != null){
	                	//查询历史表中最近一条车牌号匹配且入库时间匹配的车辆信息，如果存在
	                	ParamGarageTable.setParkStatus("2:出库未登记");
						ParamGarageTable.setDataSources("2:系统盘点");
						ParamGarageTable.setDeliveryTime(sfmt.format(new Date()));       //设置出库时间为当前的系统时间
						garageTableService.updateByPrimaryKey(ParamGarageTable);
						//对该信息执行出库操作
						vehicleOutService.VehicleOut(ParamGarageTable);
					}else {
						//查询历史表中最近一条车牌号匹配且入库时间匹配的车辆信息，如果不存在
						ParamGarageTable.setParkStatus("3:盘点遗漏数据");
						ParamGarageTable.setDataSources("2:系统盘点");
						ParamGarageTable.setDeliveryTime(sfmt.format(new Date()));       //设置出库时间为当前的系统时间
						garageTableService.updateByPrimaryKey(ParamGarageTable);
						//对该信息执行出库操作
						vehicleOutService.VehicleOut(ParamGarageTable);
					}
				}
			}
			result.setSuccess(true);
			result.setMessage("盘点完成！");
		}else{
			result.setSuccess(false);
			result.setMessage("请关闭上次盘点记录，再进行下次盘点");
		}
		return result;
	}
	/**
	 * @param  inventoryTtable
	 * @author zhaieryuan
	 * @method 盘点表数据录入
	 */
	@RequestMapping("/InsertInventory")
	@ResponseBody
	public JsonResult InsertInventory(InventoryTtable inventoryTtable){
		JsonResult result = new JsonResult();
		if(inventoryTtable.getCarNumber() !=null && inventoryTtable.getCarNumber() !=""){
			inventoryTtableService.insert(inventoryTtable);
			result.setSuccess(true);
			result.setMessage("盘点信息录入成功！");
		}else{
			result.setSuccess(false);
			result.setMessage("盘点信息录入失败，车牌号不能为空!");
			logger.error("盘点信息录入失败，车牌号不能为空!");
		}
		return result;
	}
	
}
