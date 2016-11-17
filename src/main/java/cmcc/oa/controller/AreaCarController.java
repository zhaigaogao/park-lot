package cmcc.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.AreaCar;
import cmcc.oa.service.AreaCarService;
import cmcc.oa.service.ParkingInfotService;
import cmcc.oa.vo.ParkingStatusVo;

/**
 * 
 * @author weihang
 * 
 */
@Controller
@RequestMapping(value = "/area")
public class AreaCarController extends BaseController {
	@Autowired
	private AreaCarService areaCarService;
	@Autowired
	private ParkingInfotService psService;


	/**
	 * 查询所有区域
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectAllArea")
	@ResponseBody
	public JsonResult selectAllArea(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		JsonResult result = new JsonResult();
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<AreaCar> areaList = areaCarService.selectAllArea();
		PageInfo<AreaCar> pageInfo = new PageInfo<>(areaList);
		// 分页结束
		result.setModel(pageInfo);
		return result;
	}

	/**
	 * 根据id查询区域信息
	 * 
	 * @param id
	 * @param owenr
	 *            区域划分
	 * @param inner
	 *            内部车的数量
	 * @param leader
	 *            公车的数量
	 * @param outer
	 *            外部车的数量
	 * @param total
	 * 			       区域存放车的总量
	 * @return
	 */
	@RequestMapping("/selectCarInfoByPrimaryKey")
	@ResponseBody
	public JsonResult selectCarInfoByPrimaryKey(@RequestParam(value = "id", required = true) Integer id) 
	{
		JsonResult result = new JsonResult();
		AreaCar areaCar = new AreaCar();
		areaCar.setId(id);
		AreaCar total = areaCarService.selectCarInfoByPrimaryKey(areaCar);
		ParkingStatusVo ps = psService.getTotalStatus();
		total.setMessage("区域车辆总数");
		
		Integer inner = (int) ps.getEmployeeArea();
		Integer leader = (int) ps.getDepartmentArea();
		Integer outer = (int) ps.getGuestArea();
		AreaCar parked = new AreaCar();
		parked.setInnerArea(inner);
		parked.setOuterArea(outer);
		parked.setLeaderArea(leader);
		parked.setTotalCar(total.getTotalCar());
		parked.setMessage("已经停靠车的数量");
		
		AreaCar restCar = new AreaCar();
		restCar.setInnerArea(total.getInnerArea()-inner);
		restCar.setLeaderArea(total.getLeaderArea()-leader);
		restCar.setOuterArea(total.getOuterArea()-outer);
		restCar.setTotalCar(total.getTotalCar());
		restCar.setMessage("剩余车的数量");
		
		List<AreaCar> list = new ArrayList<>();
		list.add(restCar);
		list.add(total);
		list.add(parked);
		result.setModel(list);
		return result;
	}
	
	/**
	 * 新增区域
	 * @param id
	 * @param owenr
	 *            区域划分
	 * @param inner
	 *            内部车的数量
	 * @param leader
	 *            公车的数量
	 * @param outer
	 *            外部车的数量
	 * @param total
	 * 			  区域存放车的总量
	 * @return
	 */
	@RequestMapping("/insertArea")
	@ResponseBody
	public JsonResult insertArea(@RequestParam(value = "owner", required = true) String owenr,
			@RequestParam(value = "inner", required = true) Integer inner,
			@RequestParam(value = "leader", required = true) Integer leader,
			@RequestParam(value = "outer", required = true) Integer outer,
			@RequestParam(value = "total", required = true) Integer total) {
		JsonResult result = new JsonResult();
		AreaCar areaCar = new AreaCar();
		areaCar.setOwnerArea(owenr);
		areaCar.setOuterArea(outer);
		areaCar.setInnerArea(inner);
		areaCar.setLeaderArea(leader);
		areaCar.setTotalCar(total);;
		areaCarService.insertArea(areaCar);
		return result;
	}
	/**
	 * 修改区域 
	 * @param id
	 * @param owenr
	 *            区域划分
	 * @param inner
	 *            内部车的数量
	 * @param leader
	 *            公车的数量
	 * @param outer
	 *            外部车的数量
	 * @param total
	 * 			  区域存放车的总量
	 * @return
	 */
	@RequestMapping("/updateArea")
	@ResponseBody
	public JsonResult updateArea(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "owner", required = true) String owenr,
			@RequestParam(value = "inner", required = true) Integer inner,
			@RequestParam(value = "leader", required = true) Integer leader,
			@RequestParam(value = "outer", required = true) Integer outer){
		JsonResult result = new JsonResult();
		AreaCar areaCar = new AreaCar();
		ParkingStatusVo ps = psService.getTotalStatus();
		Integer psInner = (int) ps.getEmployeeArea();
		Integer psLeader = (int) ps.getDepartmentArea();
		Integer psOuter = (int) ps.getGuestArea();
		if(inner < psInner || outer < psOuter || leader < psLeader){
			result.setMessage("当前已停放数量超出修改数量，请检查后修改数据");
			return result;
		}else{
		areaCar.setId(id);
		areaCar.setOwnerArea(owenr);
		areaCar.setOuterArea(outer);
		areaCar.setInnerArea(inner);
		areaCar.setLeaderArea(leader);
		areaCarService.updateArea(areaCar);;
		result.setMessage("修改成功！");
		return result;
		}
	}
	
	@RequestMapping("/deleteByPrimaryKey")
	@ResponseBody
	public JsonResult deleteByPrimaryKey(@RequestParam(value = "id", required = true) Integer id){
		JsonResult result = new JsonResult();
		areaCarService.deleteByPrimaryKey(id);
		return result;
	}
}
