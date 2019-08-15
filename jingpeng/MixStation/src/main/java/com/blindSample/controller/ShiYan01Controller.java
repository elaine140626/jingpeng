package com.blindSample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.ShiYan01DetailEntity;
import com.blindSample.model.ShiYan01Entity;
import com.blindSample.model.ShiYan02DetailEntity;
import com.blindSample.model.ShiYan02Entity;
import com.blindSample.model.ShiYan04DetailEntity;
import com.blindSample.model.ShiYan04Entity;
import com.blindSample.model.ShiYan0501DetailEntity;
import com.blindSample.model.ShiYan0502DetailEntity;
import com.blindSample.model.ShiYan05Entity;
import com.blindSample.model.ShiYan06DetailEntity;
import com.blindSample.model.ShiYan06Entity;
import com.blindSample.model.ShiYan07DetailEntity;
import com.blindSample.model.ShiYan07Entity;
import com.blindSample.model.ShiYan08DetailEntity;
import com.blindSample.model.ShiYan08Entity;
import com.blindSample.service.ShiYan01Service;
import com.jingpeng.dao.DataTablesResponseInfo;

@Controller
@RequestMapping("/ShiYan")
public class ShiYan01Controller{	
	@Autowired
	ShiYan01Service  shiYan01Service;

	
	/**
	 * 初始化方法 shiyan21
	 * 
	**/
	@RequestMapping("/shiyan21.html")
	public String init21() {
		return "/BlindnessTest/shiyan25";
	}
	
	/**
	 * 初始化方法 shiyan01
	 * 
	**/
	@RequestMapping("/shiyan01.html")
	public String init1() {
		return "/BlindnessTest/shiyan01";
	}
	
	
	/**
	 * 初始化方法 shiyan02
	 * 
	**/
	@RequestMapping("/shiyan02.html")
	public String init2() {
		return "/BlindnessTest/shiyan02";
	}
	
	/**
	 * 初始化方法 shiyan04
	 * 
	**/
	@RequestMapping("/shiyan04.html")
	public String init4() {
		return "/BlindnessTest/shiyan04";
	}
	
	/**
	 * 初始化方法 shiyan05
	 * 
	**/
	@RequestMapping("/shiyan05.html")
	public String init5() {
		return "/BlindnessTest/shiyan05";
	}
	
	/**
	 * 初始化方法 shiyan06
	 * 
	**/
	@RequestMapping("/shiyan06.html")
	public String init6() {
		return "/BlindnessTest/shiyan06";
	}
	
	/**
	 * 初始化方法 shiyan07
	 * 
	**/
	@RequestMapping("/shiyan07.html")
	public String init7() {
		return "/BlindnessTest/shiyan07";
	}
	
	/**
	 * 初始化方法 shiyan08
	 * 
	**/
	@RequestMapping("/shiyan08.html")
	public String init8() {
		return "/BlindnessTest/shiyan08";
	}
	
	
	/**
	 * 试验01 水泥胶砂强度试验（ISO法）试验 List
	 * 
	**/	
	@RequestMapping("/getshiyan01")
	@ResponseBody
	public DataTablesResponseInfo getshiyan01(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan01Entity> list=  shiYan01Service.getShiYan01(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan01DetailEntity> listDetail = shiYan01Service.getShiYanDetail01(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getBendFailureLoad1() == null) {
					listDetail.get(i).setBendFailureLoad1(""); 
				}
				if(listDetail.get(i).getBendFailureLoad2() == null) {
					listDetail.get(i).setBendFailureLoad2(""); 
				}
				if(listDetail.get(i).getBendFailureLoad3() == null) {
					listDetail.get(i).setBendFailureLoad3(""); 
				}
				if(listDetail.get(i).getRuptureStrength1() == null) {
					listDetail.get(i).setRuptureStrength1(""); 
				}
				if(listDetail.get(i).getRuptureStrength2() == null) {
					listDetail.get(i).setRuptureStrength2(""); 
				}
				if(listDetail.get(i).getRuptureStrength3() == null) {
					listDetail.get(i).setRuptureStrength3(""); 
				}
				if(listDetail.get(i).getAvgRuptureStrength() == null) {
					listDetail.get(i).setAvgRuptureStrength(""); 
				}
				if(listDetail.get(i).getCompFailureLoad1() == null) {
					listDetail.get(i).setCompFailureLoad1(""); 
				}
				if(listDetail.get(i).getCompFailureLoad2() == null) {
					listDetail.get(i).setCompFailureLoad2(""); 
				}
				if(listDetail.get(i).getCompFailureLoad3() == null) {
					listDetail.get(i).setCompFailureLoad3(""); 
				}
				if(listDetail.get(i).getCompFailureLoad4() == null) {
					listDetail.get(i).setCompFailureLoad4(""); 
				}
				if(listDetail.get(i).getCompFailureLoad5() == null) {
					listDetail.get(i).setCompFailureLoad5(""); 
				}
				if(listDetail.get(i).getCompFailureLoad6() == null) {
					listDetail.get(i).setCompFailureLoad6(""); 
				}
				if(listDetail.get(i).getCompTrength1() == null) {
					listDetail.get(i).setCompTrength1(""); 
				}
				if(listDetail.get(i).getCompTrength2() == null) {
					listDetail.get(i).setCompTrength2(""); 
				}
				if(listDetail.get(i).getCompTrength3() == null) {
					listDetail.get(i).setCompTrength3(""); 
				}
				if(listDetail.get(i).getCompTrength4() == null) {
					listDetail.get(i).setCompTrength4(""); 
				}
				if(listDetail.get(i).getCompTrength5() == null) {
					listDetail.get(i).setCompTrength5(""); 
				}
				if(listDetail.get(i).getCompTrength6() == null) {
					listDetail.get(i).setCompTrength6(""); 
				}
				if(listDetail.get(i).getAvgCompTrength() == null) {
					listDetail.get(i).setAvgCompTrength(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}

	/**
	 * 试验02 水泥混凝土抗压强度试验（立方体） List
	 * 
	**/	
	@RequestMapping("/getshiyan02")
	@ResponseBody
	public DataTablesResponseInfo getshiyan02(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan02Entity> list=  shiYan01Service.getShiYan02(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan02DetailEntity> listDetail = shiYan01Service.getShiYanDetail02(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getUltimateLoad1() == null) {
					listDetail.get(i).setUltimateLoad1(""); 
				}
				if(listDetail.get(i).getUltimateLoad2() == null) {
					listDetail.get(i).setUltimateLoad2(""); 
				}
				if(listDetail.get(i).getUltimateLoad3() == null) {
					listDetail.get(i).setUltimateLoad3(""); 
				}
				if(listDetail.get(i).getComprStrength1() == null) {
					listDetail.get(i).setComprStrength1(""); 
				}
				if(listDetail.get(i).getComprStrength2() == null) {
					listDetail.get(i).setComprStrength2(""); 
				}
				if(listDetail.get(i).getComprStrength3() == null) {
					listDetail.get(i).setComprStrength3(""); 
				}
				if(listDetail.get(i).getCompressiveStrength() == null) {
					listDetail.get(i).setCompressiveStrength(""); 
				}
				if(listDetail.get(i).getProp_DesignStrength() == null) {
					listDetail.get(i).setProp_DesignStrength(""); 
				}
				if(listDetail.get(i).getResult() == null) {
					listDetail.get(i).setResult(""); 
				}
				if(listDetail.get(i).getUltLoadImage1() == null) {
					listDetail.get(i).setUltLoadImage1(""); 
				}
				if(listDetail.get(i).getUltLoadImage2() == null) {
					listDetail.get(i).setUltLoadImage2(""); 
				}
				if(listDetail.get(i).getUltLoadImage3() == null) {
					listDetail.get(i).setUltLoadImage3(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验04 无机结合料稳定材料无侧限抗压强度试验） List
	 * 
	**/	
	@RequestMapping("/getshiyan04")
	@ResponseBody
	public DataTablesResponseInfo getshiyan04(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan04Entity> list=  shiYan01Service.getShiYan04(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan04DetailEntity> listDetail = shiYan01Service.getShiYanDetail04(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getMaxStress() == null) {
					listDetail.get(i).setMaxStress(""); 
				}
				if(listDetail.get(i).getRc() == null) {
					listDetail.get(i).setRc(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验05 沥青三大指标试验  List
	 * 
	**/	
	@RequestMapping("/getshiyan05")
	@ResponseBody
	public DataTablesResponseInfo getshiyan05(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan05Entity> list=  shiYan01Service.getShiYan05(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan0501DetailEntity> listDetail01 = shiYan01Service.getShiYanDetail0501(mapDetail);
		if (listDetail01!=null) {
			for(int i=0;i<listDetail01.size();i++) {
				if(listDetail01.get(i).getPenetration1() == null) {
					listDetail01.get(i).setPenetration1(""); 
				}
				if(listDetail01.get(i).getPenetration2() == null) {
					listDetail01.get(i).setPenetration2(""); 
				}
				if(listDetail01.get(i).getPenetration3() == null) {
					listDetail01.get(i).setPenetration3(""); 
				}
				if(listDetail01.get(i).getPenetration() == null) {
					listDetail01.get(i).setPenetration(""); 
				}
			}
		}
		List<ShiYan0502DetailEntity> listDetail02 = shiYan01Service.getShiYanDetail0502(mapDetail);
		if (listDetail02!=null) {
			for(int i=0;i<listDetail02.size();i++) {
				if(listDetail02.get(i).getSoftenPoint1() == null) {
					listDetail02.get(i).setSoftenPoint1(""); 
				}
				if(listDetail02.get(i).getSoftenPoint2() == null) {
					listDetail02.get(i).setSoftenPoint2(""); 
				}
				if(listDetail02.get(i).getSoftenPoint() == null) {
					listDetail02.get(i).setSoftenPoint(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail01", listDetail01);
		params.put("listDetail02", listDetail02);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验06 沥青混合料马歇尔稳定度试验 List
	 * 
	**/	
	@RequestMapping("/getshiyan06")
	@ResponseBody
	public DataTablesResponseInfo getshiyan06(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan06Entity> list=  shiYan01Service.getShiYan06(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan06DetailEntity> listDetail = shiYan01Service.getShiYanDetail06(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getStab() == null) {
					listDetail.get(i).setStab(""); 
				}
				if(listDetail.get(i).getFlow() == null) {
					listDetail.get(i).setFlow(""); 
				}
				if(listDetail.get(i).getMarshallModulus() == null) {
					listDetail.get(i).setMarshallModulus(""); 
				}
				if(listDetail.get(i).getUltLoadImage1() == null) {
					listDetail.get(i).setUltLoadImage1(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验07 钢筋抗拉强度、屈服强度、伸长率、冷弯试验 List
	 * 
	**/	
	@RequestMapping("/getshiyan07")
	@ResponseBody
	public DataTablesResponseInfo getshiyan07(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan07Entity> list=  shiYan01Service.getShiYan07(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan07DetailEntity> listDetail = shiYan01Service.getShiYanDetail07(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getYieldLoad1() == null) {
					listDetail.get(i).setYieldLoad1(""); 
				}
				if(listDetail.get(i).getYieldLoad2() == null) {
					listDetail.get(i).setYieldLoad2(""); 
				}
				if(listDetail.get(i).getYieldStrth1() == null) {
					listDetail.get(i).setYieldStrth1(""); 
				}
				if(listDetail.get(i).getYieldStrth2() == null) {
					listDetail.get(i).setYieldStrth2(""); 
				}
				if(listDetail.get(i).getMaxLoad1() == null) {
					listDetail.get(i).setMaxLoad1(""); 
				}
				if(listDetail.get(i).getMaxLoad2() == null) {
					listDetail.get(i).setMaxLoad2(""); 
				}
				if(listDetail.get(i).getTensileStrength1() == null) {
					listDetail.get(i).setTensileStrength1(""); 
				}
				if(listDetail.get(i).getTensileStrength2() == null) {
					listDetail.get(i).setTensileStrength2(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验08  钢筋接头抗拉强度、冷弯试验自动采集数据 List
	 * 
	**/	
	@RequestMapping("/getshiyan08")
	@ResponseBody
	public DataTablesResponseInfo getshiyan08(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan08Entity> list=  shiYan01Service.getShiYan08(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan08DetailEntity> listDetail = shiYan01Service.getShiYanDetail08(mapDetail);
		if (listDetail!=null) {
			for(int i=0;i<listDetail.size();i++) {
				if(listDetail.get(i).getMaxLoad1() == null) {
					listDetail.get(i).setMaxLoad1(""); 
				}
				if(listDetail.get(i).getMaxLoad2() == null) {
					listDetail.get(i).setMaxLoad2(""); 
				}
				if(listDetail.get(i).getMaxLoad3() == null) {
					listDetail.get(i).setMaxLoad3(""); 
				}
				if(listDetail.get(i).getTensileStrength1() == null) {
					listDetail.get(i).setTensileStrength1(""); 
				}
				if(listDetail.get(i).getTensileStrength2() == null) {
					listDetail.get(i).setTensileStrength2(""); 
				}
				if(listDetail.get(i).getTensileStrength3() == null) {
					listDetail.get(i).setTensileStrength3(""); 
				}
			}
		}
		Map params = new HashMap();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
}