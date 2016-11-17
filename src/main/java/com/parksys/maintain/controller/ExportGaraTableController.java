package com.parksys.maintain.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageTableService;

@Controller
@RequestMapping("ExportGarageTable")
public class ExportGaraTableController extends BaseController{
	/**
	 * 导出车库表表信息生成excel表格
	 */
	@Autowired
	private GarageTableService garageTableService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	public static final short detail_size = 12;
	@RequestMapping("/exportGarageTableList")
	@ResponseBody
	public void exportExcel(HttpServletResponse response,HttpServletRequest request){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFCellStyle styleleft = wb.createCellStyle();styleleft.setWrapText(true);
		styleleft.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
		
		HSSFCellStyle style = wb.createCellStyle();style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
		HSSFFont dfont = wb.createFont();
		dfont.setFontHeightInPoints((short) 12);//设置字体大小
		
		style.setFont(dfont);
		
		HSSFCellStyle etitle = wb.createCellStyle();etitle.setWrapText(true);
		etitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个标题格式
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints(detail_size);//设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		etitle.setFont(font);

		HSSFCellStyle ptt = wb.createCellStyle();ptt.setWrapText(true);
		ptt.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个标题格式
		HSSFFont pfont = wb.createFont();
		pfont.setFontHeightInPoints(detail_size);//设置字体大小
		pfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		ptt.setFont(pfont);
		int l=-1;
		int i=-1;
		i=-1;
		//创建excle抬头
		HSSFRow row = sheet.createRow(++l);
		HSSFCell cell = row.createCell(++i);
		
			cell.setCellValue("车牌号");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("入库时间");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("出库时间");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("车位编号");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("停车状态");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("数据来源");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			cell.setCellValue("数据录入时间");
			cell.setCellStyle(style);
			cell = row.createCell(++i);
			
			
			//获取数据源
			GarageTable queryGarageTable=new GarageTable();
			List<GarageTable> garageTables=garageTableService.SelectGarageTableList(queryGarageTable);
			//将数据和抬头一一对应
	    Map<String,Object> vmap = new HashMap<String,Object>();
	    Map<String,Integer> imap = new HashMap<String,Integer>();
		for (GarageTable map : garageTables) {
			i=-1;
			row = sheet.createRow(++l);
				cell = row.createCell(++i);
				cell.setCellValue(map.getDataSources());
				cell.setCellStyle(style);
	       		
				cell = row.createCell(++i);
				cell.setCellValue(map.getDeliveryTime());
				cell.setCellStyle(style);
	       		
				cell = row.createCell(++i);
				cell.setCellValue(map.getParkNumber());
				cell.setCellStyle(style);
	       		
				cell = row.createCell(++i);
				cell.setCellValue(map.getParkStatus());
				cell.setCellStyle(style);
	       		
				cell = row.createCell(++i);
				cell.setCellValue(map.getParkNumber());
				cell.setCellStyle(style);
	       		
				cell = row.createCell(++i);
				cell.setCellValue(map.getRegisTime());
				cell.setCellStyle(style);
	       		
	       			
				cell = row.createCell(++i);
				cell.setCellValue(map.getStorageTime());
				cell.setCellStyle(style);
		
	       			    		
		}
		row = sheet.createRow(++l);
	    Set<String> ks = vmap.keySet();
	    for (String k : ks) {
			cell = row.createCell(imap.get(k));
			cell.setCellValue(vmap.get(k)+"");
			cell.setCellStyle(style);
		}
		//列数
		i=-1;
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
	    sheet.setColumnWidth(++i, 3500);
		try{
			//文件名称
			String filename = "车库表记录.xls";
			response.setContentType("application/vnd.ms-excel");
			//中文编码
	        response.setHeader("Content-disposition", "attachment;charset=UTF-8;filename=" + URLEncoder.encode(filename, "utf-8"));   
	        OutputStream ouputStream = response.getOutputStream();     
	        wb.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();     
		}catch (Exception e){
			e.printStackTrace();
			log.info("导出车库历史表记录.xls编码出错！");
		}
	}
}
