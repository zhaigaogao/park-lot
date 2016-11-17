package cmcc.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.FeeScale;
import cmcc.oa.service.FeeScaleService;

@Controller
@RequestMapping("/feeScale")
public class FeeScaleController extends BaseController {
	@Autowired
	private FeeScaleService feeScaleService;

	/**
	 * 修改计费标准表接口
	 * 
	 * @param freeTime
	 *            免费停车时长
	 * @param chargePeriod
	 *            收费阶段（每隔几分钟收费）
	 * @param feePeriod
	 *            收费金额
	 * @param dayNight
	 *            白天或者晚上（0：白天 1：晚上）
	 * @param carType
	 *            车型（1：小车 2：中型车 3：大型车）
	 * @return
	 */
	@RequestMapping("/updateScale")
	@ResponseBody
	public JsonResult updateScale(@RequestParam(value = "freeTime", required = true) Integer freeTime,
			@RequestParam(value = "chargePeriod", required = true) Integer chargePeriod,
			@RequestParam(value = "feePeriod", required = true) Integer feePeriod,
			@RequestParam(value = "dayNight", required = true) Integer dayNight,
			@RequestParam(value = "carType", required = true) Integer carType) {
		JsonResult result = new JsonResult();
		FeeScale feeScale = new FeeScale();
		feeScale.setCarType(carType);
		feeScale.setDayNight(dayNight);
		feeScale.setFreeTime(freeTime);
		feeScale.setChargePeriod(chargePeriod);
		feeScale.setFeePeriod(feePeriod);
		feeScaleService.updateScale(feeScale);
		return result;
	}

	/**
	 * 查找所有收费准则
	 * @return
	 */
	@RequestMapping("/findAllScale")
	@ResponseBody
	public JsonResult findAllScale() {
		JsonResult result = new JsonResult();
		List<FeeScale> list = feeScaleService.findAllByCondition();
		result.setModel(list);
		return result;
	}

	@RequestMapping("/updateBenefit")
	@ResponseBody
	public JsonResult updateBenefit(@RequestParam(value = "carType", required = true) Integer carType,
			@RequestParam(value = "monthPay", required = true) Integer monthPay,
			@RequestParam(value = "yearPay", required = true) Integer yearPay){
		JsonResult result = new JsonResult();
		feeScaleService.updateBenefit(carType, monthPay, yearPay);
		return result;
	}
}
