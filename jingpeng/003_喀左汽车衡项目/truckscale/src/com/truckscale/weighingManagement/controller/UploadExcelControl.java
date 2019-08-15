package com.truckscale.weighingManagement.controller;


import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truckscale.common.util.ExcelUtil;
import com.truckscale.common.util.MessageUtil;
import com.truckscale.weighingManagement.model.WeighingQueryEntity;
import com.truckscale.weighingManagement.service.WeighingQueryService;
 
 
@Controller
@RequestMapping("/excell")  
public class UploadExcelControl {
	@Autowired
	private WeighingQueryService weighingQueryService ;
	
	 @RequestMapping("/dc.action")
	   @ResponseBody
	    public void export(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> map) throws Exception {
	           //获取数据
		List<WeighingQueryEntity> list = weighingQueryService.getWeighingQuery(map);
	           //excel标题
	           String[] title = MessageUtil.Excel_Title;
	           //excel文件名
	           String fileName = "出入库称重表"+System.currentTimeMillis()+".xls";

	           	//sheet名
	           String sheetName = "出入库称重表";
	           String [][] content = new String[list.size()][];
	           for (int i = 0; i < list.size(); i++) {
				content[i] = new String[title.length];
				WeighingQueryEntity obj = list.get(i);
				content[i][0] = obj.getSerialId();
				content[i][1] = obj.getCreaterDate();
				content[i][2] = obj.getCarNumber();
				content[i][3] = obj.getMaterielName();
				content[i][4] = obj.getMaterielModel();
				content[i][5] = String.valueOf(obj.getGrossWeight());
				content[i][6] = String.valueOf(obj.getTareWeight());
				content[i][7] = String.valueOf(obj.getNetWeight());
				content[i][8] = String.valueOf(obj.getNumericalValue());
				if(obj.getMeasurementMode() == 0) {
					content[i][9] = "自动";
				}else {
					content[i][9] = "手动";
				}
				content[i][10] = String.valueOf(obj.getGrossWeightCount());
				content[i][11] = String.valueOf(obj.getTareWeightCount());
				content[i][12] = obj.getCreater();
				content[i][13] = obj.getFeedCompanyName();
				content[i][14] = obj.getReceiveUnitName();
				content[i][15] = obj.getDriverName();
				content[i][16] = obj.getTransportCompanyName();
				content[i][17] = obj.getEndAddress();
				content[i][18] = obj.getStartAddress();
				content[i][19] = String.valueOf(obj.getTemperature());
				content[i][20] = obj.getRatioOfOil();
				content[i][21] = obj.getRoute();
				content[i][22] = obj.getRemarks();
	           	}

				//创建HSSFWorkbook 
				HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
				
				//响应到客户端
				try {
				this.setResponseHeader(response, fileName);
					OutputStream os = response.getOutputStream();
						wb.write(os);
						os.flush();
						os.close();
						} catch (Exception e) {
						e.printStackTrace();
					}
				}
	 
	 //发送响应流方法
	    public void setResponseHeader(HttpServletResponse response, String fileName) {
	        try {
	            try {
	                fileName = new String(fileName.getBytes(),"ISO8859-1");
	            } catch (UnsupportedEncodingException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            response.setContentType("application/octet-stream;charset=ISO8859-1");
	            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	            response.addHeader("Pargam", "no-cache");
	            response.addHeader("Cache-Control", "no-cache");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
}
