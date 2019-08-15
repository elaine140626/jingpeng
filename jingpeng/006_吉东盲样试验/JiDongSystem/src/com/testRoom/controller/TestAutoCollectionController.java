package com.testRoom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highwayPlatform.model.DataTablesResponseInfo;
import com.testRoom.model.TestSelectEntity;
import com.testRoom.service.TestAutoCollectionService;

@Controller
@RequestMapping("/TestAutoCollection")
public class TestAutoCollectionController {
	@Autowired
	TestAutoCollectionService testAutoCollectionService;
	
	/**
	 * 获取试验基本信息
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/getInfoList.action")
	@ResponseBody 
	public DataTablesResponseInfo getTestInfoList(@RequestParam Map<String, Object> param){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		
		// 检索条件设置
		Map<String, Object> map = new HashMap<String, Object>();
		if(!param.get("testNameId").toString().equals("")) {
			// 获取页面的试验名称id
			map.put("testNameId", Integer.parseInt(param.get("testNameId").toString()));
		}else {
			map.put("testNameId", 0);
		}

		// 开始时间
		map.put("startTime", param.get("startTime").toString());
		// 结束时间
		map.put("endTime", param.get("endTime").toString());
		// 获取选中的试验室唯一标识
		String uniqueIdentifiers = param.get("uniqueIdentifier").toString();
		if(("").equals(uniqueIdentifiers)) {
			map.put("uniqueIdentifier", uniqueIdentifiers);
		}else {
			String[] uniqueIdentifierList = uniqueIdentifiers.split(",");
			map.put("uniqueIdentifier", uniqueIdentifierList);
		}
		// 获取试验室基本信息
		List<TestSelectEntity> testInfoList = testAutoCollectionService.getInfoList(map);

		// 判空处理
		if (testInfoList != null && testInfoList.size() > 0) {
			// 循环设置序列号
			for(int i = 0; i < testInfoList.size(); i++) {
				testInfoList.get(i).setRownum(i+1);
			}
		}else {
			testInfoList = new ArrayList<TestSelectEntity>();
		}
		dtri.setData(testInfoList);
		return dtri;
	}

	//查询水泥混凝土抗压强度试验（立方体）
	@RequestMapping("/getSNHNList.action")
	@ResponseBody
	public DataTablesResponseInfo getSNHNList(@RequestParam Map<String, Object> param){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> snhnList = testAutoCollectionService.getSNHNList(param);
		if(snhnList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<snhnList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > snhnList.size()) {
					end = snhnList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(snhnList.get(i).getSerialNumber())) {
						snhnList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(snhnList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (snhnList.get(i).getSerialNumber().equals(snhnList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						snhnList.get(i).setMerge(merge);
					}
					snhnList.get(i).setRownum(count);
					inSerialNumber = (String) snhnList.get(i).getSerialNumber();
				}
			}
			dtri.setData(snhnList);
		}
		return dtri;
	}
	
	//查询砂浆抗压强度试验
	@RequestMapping("/getBJList.action")
	@ResponseBody
	public DataTablesResponseInfo getBJList(@RequestParam Map<String, Object> param){
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> bjList = testAutoCollectionService.getBJList(param);
		if(bjList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<bjList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > bjList.size()) {
					end = bjList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(bjList.get(i).getSerialNumber())) {
						bjList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(bjList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (bjList.get(i).getSerialNumber().equals(bjList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						bjList.get(i).setMerge(merge);
					}
					bjList.get(i).setRownum(count);
					inSerialNumber = (String) bjList.get(i).getSerialNumber();
				}
			}
			dtri.setData(bjList);
		}
		return dtri;
	}
	
	//钢筋拉伸强度、屈服强度、伸长率、冷弯试验
	@RequestMapping("/getKQSLList.action")
	@ResponseBody
	public DataTablesResponseInfo getKQSLList(@RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> kqslList = testAutoCollectionService.getKQSLList(param);
		if(kqslList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<kqslList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > kqslList.size()) {
					end = kqslList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(kqslList.get(i).getSerialNumber())) {
						kqslList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(kqslList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (kqslList.get(i).getSerialNumber().equals(kqslList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						kqslList.get(i).setMerge(merge);
					}
					kqslList.get(i).setRownum(count);
					inSerialNumber = (String) kqslList.get(i).getSerialNumber();
				}
			}
			dtri.setData(kqslList);
		}
		return dtri;
	}
	
	//钢筋接头拉伸强度、冷弯试验
	@RequestMapping("/getKLList.action")
	@ResponseBody
	public DataTablesResponseInfo getKLList(@RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> klList = testAutoCollectionService.getKLList(param);
		if(klList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<klList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > klList.size()) {
					end = klList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(klList.get(i).getSerialNumber())) {
						klList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(klList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (klList.get(i).getSerialNumber().equals(klList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						klList.get(i).setMerge(merge);
					}
					klList.get(i).setRownum(count);
					inSerialNumber = (String) klList.get(i).getSerialNumber();
				}
			}
			dtri.setData(klList);
		}
		return dtri;
	}
	
	//沥青针入度试验
	@RequestMapping("/getLQZRList.action")
	@ResponseBody
	public DataTablesResponseInfo getLQZRList(@RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> lqzrList = testAutoCollectionService.getLQZRList(param);
		if(lqzrList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<lqzrList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > lqzrList.size()) {
					end = lqzrList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(lqzrList.get(i).getSerialNumber())) {
						lqzrList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(lqzrList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (lqzrList.get(i).getSerialNumber().equals(lqzrList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						lqzrList.get(i).setMerge(merge);
					}
					lqzrList.get(i).setRownum(count);
					inSerialNumber = (String) lqzrList.get(i).getSerialNumber();
				}
			}
			dtri.setData(lqzrList);
		}
		return dtri;
	}
	
	//沥青软化点试验
	@RequestMapping("/getLQRHDList.action")
	@ResponseBody
	public DataTablesResponseInfo getLQRHDList(@RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> lqrhdList = testAutoCollectionService.getLQRHDList(param);
		if(lqrhdList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<lqrhdList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > lqrhdList.size()) {
					end = lqrhdList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(lqrhdList.get(i).getSerialNumber())) {
						lqrhdList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(lqrhdList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (lqrhdList.get(i).getSerialNumber().equals(lqrhdList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						lqrhdList.get(i).setMerge(merge);
					}
					lqrhdList.get(i).setRownum(count);
					inSerialNumber = (String) lqrhdList.get(i).getSerialNumber();
				}
			}
			dtri.setData(lqrhdList);
		}
		return dtri;
	}
	
	//沥青混合料马歇尔试验
	@RequestMapping("/getLQMXList.action")
	@ResponseBody
	public DataTablesResponseInfo getLQMXList(@RequestParam Map<String, Object> param) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<TestSelectEntity> lqmxList = testAutoCollectionService.getLQMXList(param);
		if(lqmxList != null) {
			//试验基本信息流水号
			String serialNumber = "";
			int count = 0;
			//外层循环 有多少页 循环几次
			for(int k = 0;k<lqmxList.size()/14+1;k++) {
				int end = 14*(k+1);
				if(end > lqmxList.size()) {
					end = lqmxList.size();
				}
				String inSerialNumber = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k;i < end;i++) {
					if(inSerialNumber.equals(lqmxList.get(i).getSerialNumber())) {
						lqmxList.get(i).setMerge(0);
					}else {
						if(!serialNumber.equals(lqmxList.get(i).getSerialNumber())) {
							count ++;
						}
						int merge = 0;
						for(int j=14*k;j < end;j++) {
							if (lqmxList.get(i).getSerialNumber().equals(lqmxList.get(j).getSerialNumber())) {
								merge++;
							}
						}
						lqmxList.get(i).setMerge(merge);
					}
					lqmxList.get(i).setRownum(count);
					inSerialNumber = (String) lqmxList.get(i).getSerialNumber();
				}
			}
			dtri.setData(lqmxList);
		}
		return dtri;
	}
}
