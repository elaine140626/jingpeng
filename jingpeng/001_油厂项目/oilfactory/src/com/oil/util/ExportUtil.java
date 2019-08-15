package com.oil.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;

public class ExportUtil {
	/**
	 * @since easypoi报表导出
	 * @param request
	 * @param response
	 * @param excelName 导出excel文件名称
	 * @param title 表头名称
	 * @param sheetName sheet页名称（可填为""或者为null）
	 * @param pojoClass 导出实体.Class
	 * @param dataSet 结果集
	 */
	public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String excelName,
			String title, String sheetName, Class<?> pojoClass, Collection<?> dataSet) {
		// sheetName不填时默认为sheet1
		if ("".equals(sheetName) || sheetName == null) {
			sheetName = "sheet1";
		}
		try {
			Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), pojoClass, dataSet);
			response.setContentType("application/xls;charset=UTF-8");
			excelName = URLEncoder.encode(excelName,"UTF-8"); 
			response.setHeader("Content-Disposition", "attachment;filename="+excelName+".xls");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush(); 
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
