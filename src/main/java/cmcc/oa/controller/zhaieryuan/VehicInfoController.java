package cmcc.oa.controller.zhaieryuan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.service.VehicleInfoService;
import cmcc.oa.vo.VehicinfoVo;

@Controller
@RequestMapping("vehicInfo")
public class VehicInfoController {
	/**
	 * @author zhaieryuan
	 * @Date   2016/10/28
	 * @method 车辆信息控制层
	 */
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	
	/**
	 * 
	 * @method 添加车辆信息
	 */
	@RequestMapping("addVehicInfo")
	@ResponseBody
	public JsonResult addVehicInfo(@RequestParam(value="userId") String userId,
			@RequestParam(value="carNumber") String carNumber,
			@RequestParam(value="carModel") Integer carModel,
			@RequestParam(value="carMark") Integer carMark){
		JsonResult result=new JsonResult(true,"","");
		VehicleInfo vehicleInfo=new VehicleInfo();
		vehicleInfo.setCarMark(carMark);
		vehicleInfo.setCarModel(carModel);
		vehicleInfo.setCarNumber(carNumber);
		vehicleInfo.setCreateTime(new Date());
		vehicleInfo.setFlag(1);
		if(vehicleInfoService.isExistCarNumber(carNumber)){
			vehicleInfoService.addVehInfo(userId, vehicleInfo);
			result.setSuccess(true);
			result.setMessage("添加成功");
		}else {
			result.setSuccess(false);
			result.setMessage("添加失败");
		}
		return result;
	}
	/**
	 * @method 根据车牌号信息查询车辆信息
	 */
	@RequestMapping("queryVehicInfo")
	@ResponseBody
	public JsonResult queryVehicInfo(@RequestParam(value="carNumber") String carNumber){
		JsonResult result=new JsonResult(true,"","");
		VehicleInfo vehicleInfo=vehicleInfoService.queryByCarNumber(carNumber);
		if(vehicleInfo!=null){
			result.setModel(vehicleInfo);
		}else {
			result.setSuccess(false);
			result.setMessage("车牌号不存在");
		}
		return result;
	}
	
	/**
	 * @method 根据车辆信息id删除车辆信息
	 */
	@RequestMapping("deleteVehicInfo")
	@ResponseBody
	public JsonResult deleteVehicInfo(@RequestParam(value="id",required=true) String id){
		JsonResult result=new JsonResult(true,"","");
		if(!id.equals("")){
			vehicleInfoService.deleteVehInfo(id);
			result.setMessage("删除成功");
		}else {
			result.setSuccess(false);
			result.setMessage("删除失败");
		}
		return result;
	}
	/**
	 * @method 根据车牌号查询车辆和人员信息
	 */
	@RequestMapping("queryVehicVoByUserId")
	@ResponseBody
	public JsonResult queryVehicVoByUserId(@RequestParam(value="userId") String userId){
		JsonResult result=new JsonResult(true,"","");
		VehicinfoVo vehicinfoVo=new VehicinfoVo();
		vehicinfoVo.setUserId(userId);
		List<VehicinfoVo> vehicinfoVos=vehicleInfoService.queryByUserId(vehicinfoVo);
		if(vehicinfoVos.size()>0){
			result.setSuccess(true);
			result.setModel(vehicinfoVos);
		}else {
			result.setSuccess(false);
			result.setMessage("查询结果不存在");
		}
		return result;
	}
	/**
	 * @method 根据车牌号查询车辆和人员信息
	 */
	@RequestMapping("queryVehicVoByCarNumber")
	@ResponseBody
	public JsonResult queryVehicVoByCarNumber(@RequestParam(value="carNumber") String carNumber){
		JsonResult result=new JsonResult(true,"","");
		VehicinfoVo vehicinfoVo=new VehicinfoVo();
		vehicinfoVo.setCarNumber(carNumber);
		List<VehicinfoVo> vehicinfoVos=vehicleInfoService.queryByUserId(vehicinfoVo);
		if(vehicinfoVos.size()>0){
			result.setSuccess(true);
			result.setModel(vehicinfoVos);
		}else {
			result.setSuccess(false);
			result.setMessage("查询结果不存在");
		}
		return result;
	}
	/**
	 * @method 查询公家车信息
	 * @param pageNum    分頁頁數
	 * @param pageSize   分頁每頁記錄數
	 * @param carModel   車型
	 * @param carMark    車子類別
	 * @param carNumber  車牌號
	 * @param userName   用戶名
	 */
	@RequestMapping("queryCommonVehicVos")
	@ResponseBody
	public JsonResult queryCommonVehicVos(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		JsonResult result=new JsonResult(true,"","");
		PageHelper.startPage(pageNum, pageSize);
		VehicleInfo vehicleInfo=new VehicleInfo();
		vehicleInfo.setCarMark(1);
		vehicleInfo.setFlag(1);
		List<VehicleInfo> vehicinfos=vehicleInfoService.queryVehicInfoList(vehicleInfo);
		PageInfo<VehicleInfo> pageInfo=new PageInfo<>(vehicinfos);
		if(vehicinfos.size()>0){
			result.setSuccess(true);
			result.setModel(pageInfo);
		}else {
			result.setSuccess(false);
			result.setMessage("没有数据");
		}
		return result;
	}
	/**
	 * @method 查詢所有私家車信息
	 */
	@RequestMapping("queryPrivateVehicVos")
	@ResponseBody
	public JsonResult queryPrivateVehicVos(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		JsonResult result=new JsonResult(true,"","");
		PageHelper.startPage(pageNum, pageSize);
		VehicinfoVo vehicinfoVo=new VehicinfoVo();
		vehicinfoVo.setCarMark(2);
		List<VehicinfoVo> vehicinfoVos=vehicleInfoService.queryByUserId(vehicinfoVo);
		if(vehicinfoVos.size()>0){
			result.setSuccess(true);
			PageInfo<VehicinfoVo> pageInfo=new PageInfo<>(vehicinfoVos);
			result.setModel(pageInfo);
		}else {
			result.setSuccess(false);
			result.setMessage("查询结果不存在");
		}
		return result;
	}
	
	/**
	 * @method 查詢公家車和私車的人員車輛信息并集
	 * @param pageNum    分頁頁數
	 * @param pageSize   分頁每頁記錄數
	 * @param carModel   車型
	 * @param carMark    車子類別
	 * @param carNumber  車牌號
	 * @param userName   用戶名
	 */
	@RequestMapping("queryVehicInfoVos")
	@ResponseBody
	public JsonResult queryVehicInfoVo(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			String carNumber,String userName){
		JsonResult result=new JsonResult(true,"","");
		PageHelper.startPage(pageNum, pageSize);
		VehicinfoVo vehicleInfovo=new VehicinfoVo();
		vehicleInfovo.setCarNumber(carNumber); 
		vehicleInfovo.setUserName(userName);
		List<VehicinfoVo> vehicinfos=vehicleInfoService.queryVoOnParam(vehicleInfovo);
		if(vehicinfos.size()>0){
			PageInfo<VehicinfoVo> pageInfo=new PageInfo<>(vehicinfos);
			result.setSuccess(true);
			result.setModel(pageInfo);
		}else {
			result.setSuccess(false);
			result.setMessage("没有数据");
		}
		return result;
	}
	
}
