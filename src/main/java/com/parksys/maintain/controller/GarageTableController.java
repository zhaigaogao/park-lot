package com.parksys.maintain.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;
import com.parksys.maintain.service.InventoryTtableService;

@Controller
@RequestMapping("GarageTable")
public class GarageTableController extends BaseController {
	
}
