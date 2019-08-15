package com.curing.projectSchedule.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curing.projectSchedule.dao.SubgradeKilometreEarthworkNumberDao;
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberEntity;
import com.curing.projectSchedule.model.SubgradeKilometreEarthworkNumberSum;
import com.curing.projectSchedule.service.SubgradeKilometreEarthworkNumberService;
import com.curing.util.CellUtil;
import com.curing.util.ReportMergeXls;

@Service
@Transactional
public class SubgradeKilometreEarthworkNumberServiceImpl implements SubgradeKilometreEarthworkNumberService{
	@Autowired
	private SubgradeKilometreEarthworkNumberDao subgradeKilometreEarthworkNumberDao;
	
	// 工程进度（路基每公里土石方数量表）List取得
	public List<SubgradeKilometreEarthworkNumberEntity> getSubgradeKilometreEarthworkNumberList(Map<String, Object> map){
		return subgradeKilometreEarthworkNumberDao.getSubgradeKilometreEarthworkNumberList(map);
	}
	
	// 工程进度（路基每公里土石方数量表）合计
	public List<SubgradeKilometreEarthworkNumberSum> getSubgradeKilometreEarthworkNumberSum(Map<String, Object> map){
		return subgradeKilometreEarthworkNumberDao.getSubgradeKilometreEarthworkNumberSum(map);
	}
	
	// 新增工程进度（路基每公里土石方数量表）
	public int insertSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity){
		return subgradeKilometreEarthworkNumberDao.insertSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
	}
	
	// 更新工程进度（路基每公里土石方数量表）
	public int updateSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity){
		return subgradeKilometreEarthworkNumberDao.updateSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
	}
	
	// 删除工程进度（路基每公里土石方数量表）
	public int deleteSubgradeKilometreEarthworkNumber(SubgradeKilometreEarthworkNumberEntity subgradeKilometreEarthworkNumberEntity){
		return subgradeKilometreEarthworkNumberDao.deleteSubgradeKilometreEarthworkNumber(subgradeKilometreEarthworkNumberEntity);
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sheetName = "路基防护工程数量";
		//表头
		String[] head0 = new String[] { "编号", "起止桩号", "挖方（立方米）", "挖方（立方米）", "挖方（立方米）", "挖方（立方米）", "挖方（立方米）", "挖方（立方米）", "挖方（立方米）",
				"填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "填方（立方米）", "弃方", "弃方", "弃方", "备注", };
		String[] head1 = new String[] { "总数量", "土", "土", "土","石", "石", "石", "合计","合计", "合计", "本桩利用", "本桩利用","纵向利用", "纵向利用","借方", "借方","（立方米）", "（立方米）", "（立方米）",};
		String[] head2 = new String[] { "普通土", "旧路结构层", "换填挖方","砖砌圬工", "软岩", "坚岩", "总数量","土", "石渣", "土", "石","土", "石","土", "石渣","土", "旧路结构层","砖砌圬工"};
		//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
		String[] headnum0 = new String[] { "2,4,0,0", "2,4,1,1", "2,2,2,8","2,2,9,17", "2,2,18,20", "2,4,21,21"};
		String[] headnum1 = new String[] { "3,4,2,2","3,3,3,5", "3,3,6,8", "3,3,9,11","3,3,12,13", "3,3,14,15", "3,3,16,17","3,3,18,20"};
		String[] headnum2 = new String[] { "4,4,3,3","4,4,4,4", "4,4,5,5", "4,4,6,6","4,4,7,7", "4,4,8,8", "4,4,9,9","4,4,10,10", "4,4,11,11", "4,4,12,12","4,4,13,13", "4,4,14,14", "4,4,15,15"
				,"4,4,16,16", "4,4,17,17", "4,4,18,18","4,4,19,19", "4,4,20,20"};
		 List<Map<String, Object>> dataList = subgradeKilometreEarthworkNumberDao.getSubgradeKilometreEarthworkNumberListEx(map);
		 for (int i = 0; i < dataList.size(); i++) {
			 dataList.get(i).put("row", i+1);
		}
		 String[] colName = new String[] { "row", "PileNumber", "AllCount", "ExcavationSoil", "ExcavationSoilOldRoad", "ExcavationSoilReplacement", "ExcavationStoneBrick", 
				 "ExcavationStoneSoft", "ExcavationStoneFirm","FillAllCount", "FillTotal", "FillStone", "FillLocalSoil", "FillLocalStone",
				 "FillPortraitSoil", "FillPortraitStone", "FillDebtorTotal", "FillDebtorStone", "AbandonSoil", "AbandonOldRoad", 
				 "AbandonBrick", "Remarks" };
		 Date now = new Date(); 
		 String date = "创建时间" + now.toString();
		 try {
			 //request, response, 数值,标题,第一行表头名字,第一行合并坐标,第二行表头名字,第二行合并坐标,数值对应列名称,创建时间,一共多少列,开始第二行表头列,结束第二行表头列,合计开始列
			this.reportMergeXls(request, response, dataList, sheetName, head0,headnum0, head1, headnum1, head2, headnum2,colName, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	public void reportMergeXls(HttpServletRequest request,
            HttpServletResponse response, List<Map<String, Object>> dataList,
            String sheetName, String[] head0, String[] headnum0,
            String[] head1, String[] headnum1,String[] head2, String[] headnum2, String[] detail , String date)
            throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);// 创建一个表
        // 表头标题样式
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);    
        // 表头时间样式
        HSSFFont datefont = workbook.createFont();
        datefont.setFontName("宋体");
        datefont.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle datestyle = workbook.createCellStyle();
        datestyle.setFont(datefont);
        datestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        datestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        datestyle.setLocked(true);
        // 列名样式
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setLocked(true);
        // 普通单元格样式（中文）
        HSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 12);
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style2.setFont(font2);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style2.setWrapText(true); // 换行
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        // 设置列宽  （第几列，宽度）
        sheet.setColumnWidth( 0, 1600);
        sheet.setColumnWidth( 1, 3600);  
        sheet.setColumnWidth( 2, 2800);  
        sheet.setColumnWidth( 3, 2800);  
        sheet.setColumnWidth( 4, 2800);  
        sheet.setColumnWidth( 5, 2800);
        sheet.setColumnWidth( 6, 4500);
        sheet.setColumnWidth( 7, 3600); 
        sheet.setDefaultRowHeight((short)360);//设置行高
        // 第一行表头标题
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, head0.length-1));
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 0x349);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headstyle);
        CellUtil.setCellValue(cell, sheetName);
        // 第二行时间
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, head0.length-1));
        HSSFRow row1 = sheet.createRow(1);
        row.setHeight((short) 0x349);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(datestyle);
        CellUtil.setCellValue(cell1, date);        
        // 第三行表头列名
        row = sheet.createRow(2);
        for (int i = 0; i < 22; i++) {  //自己修改
            cell = row.createCell(i);
            cell.setCellValue(head0[i]);
            cell.setCellStyle(style);
        }
        //动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        //设置合并单元格的参数并初始化带边框的表头（这样做可以避免因为合并单元格后有的单元格的边框显示不出来）
        row = sheet.createRow(3);//因为下标从0开始，所以这里表示的是excel中的第四行
        for (int i = 0; i < head0.length; i++) { 
            cell = row.createCell(i);
            cell.setCellStyle(style);//设置excel中第四行的的边框
            if(i > 2 && i< 21) { //自己修改
                for (int j = 0; j < head1.length; j++) {
                    cell = row.createCell(j+2); //自己修改
                    cell.setCellValue(head1[j]);//给excel中第四行列赋值
                    cell.setCellStyle(style);//设置excel中第四行的边框
                } 
            } 
        }
        //动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        
        row = sheet.createRow(4);//因为下标从0开始，所以这里表示的是excel中的第四行
        for (int i = 0; i < head0.length; i++) { 
        	 cell = row.createCell(i);
             cell.setCellStyle(style);//设置excel中第四行的的边框
             if(i > 3 && i< 21) { //自己修改
                 for (int j = 0; j < head2.length; j++) {
                     cell = row.createCell(j+3); //自己修改 //起始列
                     cell.setCellValue(head2[j]);//给excel中第四行列赋值
                     cell.setCellStyle(style);//设置excel中第四行的边框
                 } 
             } 
        }
        //动态合并单元格
        for (int i = 0; i < headnum2.length; i++) {
            String[] temp = headnum2[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }

        // 设置列值-内容
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 5);//标题、时间、表头字段共占了5行，所以在填充数据的时候要加5，也就是数据要从第6行开始填充
            for (int j = 0; j < detail.length; j++) {
                Map tempmap = (HashMap) dataList.get(i);
                Object data = tempmap.get(detail[j]);                
                cell = row.createCell(j);
                cell.setCellStyle(style2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                
                if(data != null) {
                //判断data是否为数值型
                Boolean isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
                //判断data是否为整数（小数部分是否为0）
                Boolean isInteger=data.toString().matches("^[-\\+]?[\\d]*$");
                //判断data是否为百分数（是否包含“%”）
                Boolean isPercent=data.toString().contains("%");
                HSSFCellStyle contextstyle =workbook.createCellStyle();
                if (isNum && !isPercent) {
                    HSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                    	contextstyle.setDataFormat(df.getBuiltinFormat("#,#0"));//数据格式只显示整数
                    }else{
                    	contextstyle.setDataFormat(df.getBuiltinFormat("#,##0.00"));//保留两位小数点
                    }                   
                    // 设置单元格格式
                    cell.setCellStyle(contextstyle);
                    // 设置单元格内容为double类型
                    CellUtil.setCellValue(cell, Double.parseDouble(data.toString()));
                } else {
                	cell.setCellStyle(contextstyle);
                    // 设置单元格内容为字符型
                	CellUtil.setCellValue(cell, data);
                }
              }
                //CellUtil.setCellValue(cell, data);
            }
        }
        
         row = sheet.createRow(5+dataList.size());
         cell = row.createCell(0);
         cell.setCellValue("合计：");
         cell.setCellStyle(style2);


        if (dataList.size() > 1) {
        	for (int i = 3; i < 21; i++) {
        		cell = row.createCell(i);//设置公式前，一定要先建立表格
        		String colString = CellReference.convertNumToColString(i); //长度转成ABC列
        		String sumstring = "SUM(" + colString + "6:" + colString + (5+dataList.size()) + ")";//求和公式
        		sheet.getRow(5+dataList.size()).getCell(i).setCellFormula(sumstring);
        		}
        	}
        String fileName = new String(sheetName.getBytes("gb2312"), "ISO8859-1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        response.setContentType("application/x-download;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + fileName + ".xls");
        OutputStream os = response.getOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] b = new byte[1024];
        while ((bais.read(b)) > 0) {
            os.write(b);
        }
        bais.close();
        os.flush();
        os.close();
    }

}
