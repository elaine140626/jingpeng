package com.oil.controller.testreports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.testreports.Exportmeasure;
import com.oil.model.testreports.Storagemeasure;
import com.oil.model.testreports.Testreport;
import com.oil.model.testreports.Testreportsaledetailed;
import com.oil.service.testreports.TestreportService;
import com.oil.util.PropertyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/testreportsaledetailed")
public class TestreportsaledetailedController {
	@Autowired
	private TestreportService testreportService;
		
	// 检测员用户名称
	@RequestMapping("/getTestreportName.action")
	@ResponseBody
	public List<Map<String,Object>> getTestreportName(){
		List<Map<String,Object>> list = testreportService.getTestreportName();
		return list;
	}
	
	// 查询出库单表数据
	@RequestMapping("/getExportmeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getExportmeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Exportmeasure> list = testreportService.getExportmeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	//查询空白检测报告数据
	@RequestMapping("/getTestreportList.action")
	@ResponseBody
	public DataTablesResponseInfo getTestreportList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Testreport> list = testreportService.getTestreportList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 获取复制的报表的list
	@RequestMapping("/getTestreports.action")
	@ResponseBody
	public List<Testreport> getTestreports(HttpServletRequest request){
		return testreportService.getTestreports();
	}
	
	//查询入库单表数据
	@RequestMapping("/getStoragemeasure.action")
	@ResponseBody
	public DataTablesResponseInfo getStoragemeasure(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<Storagemeasure> list = testreportService.getStoragemeasure(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 按电话号码模糊查询检测员
	@RequestMapping("/getTestreportNameInfo.action")
	@ResponseBody
	public List<Map<String,Object>> getTestreportNameInfo(@RequestParam String telephones) throws IOException{
		List<Map<String,Object>> list = testreportService.getTestreportNameInfo(telephones); 
		return list;
		
	}
	//出库单检测报告单销售订单明细
	@RequestMapping("/getTestreportInfoById.action")
	@ResponseBody
	public Map<String,Object> getTestreportInfoById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		// 出库单检测报告单明细
		List<Testreport> testreportListList = testreportService.getTestreport(map);
		if (testreportListList.size() > 0 && testreportListList != null) {
			map.put("testReportId", testreportListList.get(0).getId());	
		}
		// 检测报告单销售订单明细
		List<Testreportsaledetailed> testreportsaledetailedList = testreportService.getTestreportsaledetailed(map);
		
		resultMap.put("testreportListList",testreportListList);
		resultMap.put("testreportsaledetailedList", testreportsaledetailedList);
		return resultMap;
	}
	
	// 出库单检测报告单新增
	@RequestMapping("/addTestreportNameInfo.action")
	@ResponseBody
	public ResponseInfo addTestreportNameInfo(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();
		    Testreport testreport = new Testreport();
		    //testreportId如果是0就代表新增否则就是修改
		    if ("0".equals(param.get("testreportId"))) {
		    	testreport.setCreater(param.get("Creater").toString());
			}else {
				testreport.setId(Integer.parseInt(param.get("testreportId").toString()));
				testreport.setReviser(param.get("Creater").toString());
			}
		    
		    // 正常出库单检测
		    if(Integer.parseInt(param.get("flag").toString()) == 1) {
		    	if( param.get("id") != null && !param.get("id").toString().equals("null") && !param.get("id").toString().equals("")) {
			    	testreport.setExportStorageId(Integer.parseInt(param.get("id").toString()));
			    	// 是否兑换
			    	if(param.get("exchange") != null && !param.get("exchange").toString().equals("")){
			    		testreport.setExchange(Integer.parseInt(param.get("exchange").toString()));
			    	}
			    }
		    }
		    
		    testreport.setSaleOrOut(param.get("saleOrOut").toString());
		    testreport.setTestDate(param.get("testDate").toString());
		    testreport.setReportRenown(param.get("reportRenown").toString());
		    testreport.setProductModel(param.get("productModel").toString());
		    testreport.setUsePlace(param.get("usePlace").toString());
		    testreport.setTestDate(param.get("testDate").toString());
		    testreport.setTestReportNumber(param.get("testReportNumber").toString());
		    testreport.setIndexType(param.get("indexType").toString());
		    testreport.setConclusion(param.get("conclusion").toString());
		    testreport.setReportRemarks(param.get("reportRemarks").toString());
		    testreport.setInsideRemarks(param.get("insideRemarks").toString());
		    testreport.setTestPersonnelId(Integer.parseInt(param.get("testPersonnelId").toString()));
		    testreport.setReviewerId(Integer.parseInt(param.get("reviewerId").toString()));
		    testreport.setInspectionSupervisorId(Integer.parseInt(param.get("inspectionSupervisorId").toString()));
		    testreport.setDetectionUnitId(Integer.parseInt(param.get("detectionUnitId").toString()));
			// 检测报告单销售订单明细
			// 首先把字符串转成 JSONArray  对象
			JSONArray jsonArray=JSONArray.fromObject(param.get("testreportsaledetailedList"));
			List<Testreportsaledetailed> testreportsaledetailedList = new ArrayList<Testreportsaledetailed>();
			if(jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					Testreportsaledetailed testreportsaledetailed = new Testreportsaledetailed();
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i);
					int sum = 0;
					if (!"".equals(job.get("testingItems").toString())) {
						testreportsaledetailed.setDetectionIndexId(Integer.parseInt(job.get("testingItems").toString()));
						sum++;
					}
					if (!"".equals(job.get("skillRequire").toString())) {
						testreportsaledetailed.setSkillRequire(job.get("skillRequire").toString());
						sum++;
					}
					if (!"".equals(job.get("testResult").toString())) {
						testreportsaledetailed.setTestResult(job.get("testResult").toString());
						sum++;
					}
					testreportsaledetailed.setId(Integer.parseInt(job.get("testreportsaledetailedId").toString()));
					testreportsaledetailed.setCreater(param.get("Creater").toString());
 					if (sum!=0) {
 						testreportsaledetailedList.add(testreportsaledetailed);	
					}
				}
			}
			
			int res = 0;
			// 新增或赋值的场合
			res = testreportService.addTestreportNameInfo(testreport ,testreportsaledetailedList);

			if (res>0) {
				// 操作成功
				info.setCode("success");
				info.setMessage(PropertyUtil.getProperties("M0005"));
			} else {
				// 操作失败
				info.setCode("error");
				info.setMessage(PropertyUtil.getProperties("M0006"));
			}
		return info;
	}
	
	//入库单检测报告单销售订单明细
	@RequestMapping("/getTestreportInfoByIds.action")
	@ResponseBody
	public Map<String,Object> getContractInfoByIds(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//出库单明细
		List<Storagemeasure> storagemeasureList = testreportService.getStoragemeasure(map);
		if (storagemeasureList.size() > 0 && storagemeasureList != null) {
			map.put("testReportId", storagemeasureList.get(0).getTestReportId());
		}
		//出库单检测报告单明细
		List<Testreport> testreportListList = testreportService.getTestreport(map);
		if (testreportListList.size() > 0 && testreportListList != null) {
			map.put("testPersonnelId", testreportListList.get(0).getTestPersonnelId());	
		}
		// 用户表检测员获取
		List<Userinfo> userinfoList = testreportService.getUserinfo(map);
		// 检测报告单销售订单明细
		List<Testreportsaledetailed> testreportsaledetailedList = testreportService.getTestreportsaledetailed(map);
		
		resultMap.put("testreportListList",testreportListList);
		resultMap.put("userinfoList", userinfoList);
		resultMap.put("testreportsaledetailedList", testreportsaledetailedList);
		resultMap.put("storagemeasureList", storagemeasureList.get(0));
		return resultMap;
	}
	
	// 入库单检测报告单新增
	@RequestMapping("/addTestreportNameInfos.action")
	@ResponseBody
	public ResponseInfo addTestreportNameInfos(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();
			Storagemeasure storagemeasure = new Storagemeasure();
		    Testreport testreport = new Testreport();
		    storagemeasure.setId(Integer.parseInt(param.get("id").toString()));
		    storagemeasure.setReviser(param.get("Creater").toString());
		    testreport.setTestPersonnelId(Integer.parseInt(param.get("inspector").toString()));
		    //testreportId如果是0就代表新增否则就是修改
		    if ("0".equals(param.get("testreportId").toString())) {
		    	testreport.setCreater(param.get("Creater").toString());
			}else {
				testreport.setId(Integer.parseInt(param.get("testreportId").toString()));
				testreport.setReviser(param.get("Creater").toString());
			}
		    testreport.setTestDate(param.get("testDate").toString());
		    testreport.setRemarks(param.get("Remarks").toString());
			// 检测报告单销售订单明细
			// 首先把字符串转成 JSONArray  对象
			JSONArray jsonArray=JSONArray.fromObject(param.get("testreportsaledetailedList"));
			List<Testreportsaledetailed> testreportsaledetailedList = new ArrayList<Testreportsaledetailed>();
			if(jsonArray.size()>0){
				for(int i=1;i<jsonArray.size();i++){
					Testreportsaledetailed testreportsaledetailed = new Testreportsaledetailed();
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i);
					int sum = 0;
					if (!"".equals(job.get("materielName").toString())) {
						testreportsaledetailed.setMaterielId(Integer.parseInt(job.get("materielName").toString()));
						sum++;
					}
					if (!"".equals(job.get("detectionProject").toString())) {
						testreportsaledetailed.setTestProject(job.get("detectionProject").toString());
						sum++;
					}
					if (!"".equals(job.get("technicalIndicators").toString())) {
						testreportsaledetailed.setSkillRequire(job.get("technicalIndicators").toString());
						sum++;
					}
					if (!"".equals(job.get("detectionResult").toString())) {
						testreportsaledetailed.setTestResult(job.get("detectionResult").toString());
						sum++;
					}
					if (!"".equals(job.get("resultDetermination").toString())) {
						testreportsaledetailed.setResultDetermine(job.get("resultDetermination").toString());
						sum++;
					}
					testreportsaledetailed.setId(Integer.parseInt(job.get("testreportsaledetailedId").toString()));
					testreportsaledetailed.setCreater(param.get("Creater").toString());
 					if (sum!=0) {
 						testreportsaledetailedList.add(testreportsaledetailed);	
					}
				}
			}
			
			int res = 0;
			// 新增或赋值的场合
			res = testreportService.addTestreportNameInfos(storagemeasure, testreport ,testreportsaledetailedList);

			if (res>0) {
				// 操作成功
				info.setCode("success");
				info.setMessage(PropertyUtil.getProperties("M0005"));
			} else {
				// 操作失败
				info.setCode("error");
				info.setMessage(PropertyUtil.getProperties("M0006"));
			}
		return info;
	}

	// 入库单检测报告单新增
	@RequestMapping("/deleteTestreportsaledetailed.action")
	@ResponseBody
	public ResponseInfo updateTestreportsaledetailed(@RequestParam Map<String, Object> param) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = 0;
		// 新增或赋值的场合
		res = testreportService.deleteTestreportsaledetailed(param);

		if (res>0) {
			// 操作成功
			info.setCode("success");
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			// 操作失败
			info.setCode("error");
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}
}
