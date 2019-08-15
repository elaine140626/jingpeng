package com.blindSample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.JudgingBasisTest02;
import com.blindSample.model.ShiYan21DetailEntity;
import com.blindSample.model.ShiYan21Entity;
import com.blindSample.model.ShiYan22DetailEntity;
import com.blindSample.model.ShiYan2301DetailEntity;
import com.blindSample.model.ShiYan2302DetailEntity;
import com.blindSample.model.ShiYan24DetailEntity;
import com.blindSample.model.ShiYan28DetailEntity;
import com.blindSample.model.TestUser_Info;
import com.blindSample.service.ShiYan02Service;
import com.blindSample.util.MessageUtilBlindSample;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.ResponseInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ShiYan02")
public class ShiYan02Controller{	
	@Autowired
	ShiYan02Service  shiYan02Service;

	/**
	 * 初始化方法 shiyan21
	 * 
	**/
	@RequestMapping("/shiyan21.html")
	public String init1() {
		return "/BlindnessTest/shiyan21";
	}
	
	/**
	 * 初始化方法 shiyan25
	 * 
	**/
	@RequestMapping("/shiyan25.html")
	public String init5() {
		return "/BlindnessTest/shiyan25";
	}
	
	/**
	 * 初始化方法 shiyan22
	 * 
	**/
	@RequestMapping("/shiyan22.html")
	public String init2() {
		return "/BlindnessTest/shiyan22";
	}
	
	/**
	 * 初始化方法 shiyan26
	 * 
	**/
	@RequestMapping("/shiyan26.html")
	public String init6() {
		return "/BlindnessTest/shiyan26";
	}
	
	/**
	 * 初始化方法 shiyan23
	 * 
	**/
	@RequestMapping("/shiyan23.html")
	public String init3() {
		return "/BlindnessTest/shiyan23";
	}
	
	/**
	 * 初始化方法 shiyan24
	 * 
	**/
	@RequestMapping("/shiyan24.html")
	public String init4() {
		return "/BlindnessTest/shiyan24";
	}
	
	/**
	 * 初始化方法 shiyan27
	 * 
	**/
	@RequestMapping("/shiyan27.html")
	public String init7() {
		return "/BlindnessTest/shiyan27";
	}
	
	/**
	 * 初始化方法 shiyan28
	 * 
	**/
	@RequestMapping("/shiyan28.html")
	public String init8() {
		return "/BlindnessTest/shiyan28";
	}
	
	/**
	 * 初始化方法 shiyan29
	 * 
	**/
	@RequestMapping("/shiyan29.html")
	public String init9() {
		return "/BlindnessTest/shiyan29";
	}
	
	/**
	 * 试验21  粗集料筛分试验
	 * 
	**/	
	@RequestMapping("/getshiyan21")
	@ResponseBody
	public DataTablesResponseInfo getshiyan21(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan21(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan21DetailEntity> listDetail = shiYan02Service.getShiYanDetail21(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验25  粗集料筛分试验
	 * 
	**/	
	@RequestMapping("/getshiyan25")
	@ResponseBody
	public DataTablesResponseInfo getshiyan25(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan25(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan21DetailEntity> listDetail = shiYan02Service.getShiYanDetail25(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验22  粗集料含泥量试验
	 * 
	**/	
	@RequestMapping("/getshiyan22")
	@ResponseBody
	public DataTablesResponseInfo getshiyan22(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan22(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan22DetailEntity> listDetail = shiYan02Service.getShiYanDetail22(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验26  细集料含泥量试验
	 * 
	**/	
	@RequestMapping("/getshiyan26")
	@ResponseBody
	public DataTablesResponseInfo getshiyan26(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan26(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan22DetailEntity> listDetail = shiYan02Service.getShiYanDetail26(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验23  粗集料针、片状颗粒含量试验
	 * 
	**/	
	@RequestMapping("/getshiyan23")
	@ResponseBody
	public DataTablesResponseInfo getshiyan23(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan23(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan2302DetailEntity> listDetail2 = null;
		List<ShiYan2301DetailEntity> listDetail1 = null;
		if ("a".equals(list.get(0).getTest_Method())) {
			// (规准仪法)
			listDetail2 = shiYan02Service.getShiYanDetail2302(mapDetail);
		} else {
			// (游标卡尺法)
			listDetail1 = shiYan02Service.getShiYanDetail2301(mapDetail);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail2", listDetail2);
		params.put("listDetail1", listDetail1);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验24  粗集料压碎值试验
	 * 
	**/	
	@RequestMapping("/getshiyan24")
	@ResponseBody
	public DataTablesResponseInfo getshiyan24(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan24(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan24DetailEntity> listDetail = shiYan02Service.getShiYanDetail24(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验27  水泥凝结时间 
	 * 
	**/	
	@RequestMapping("/getshiyan27")
	@ResponseBody
	public DataTablesResponseInfo getshiyan27(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan27(map);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验28  粗集料试验
	 * 
	**/	
	@RequestMapping("/getshiyan28")
	@ResponseBody
	public DataTablesResponseInfo getshiyan28(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan28(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan28DetailEntity> listDetail = shiYan02Service.getShiYanDetail28(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 试验29  细集料试验
	 * 
	**/	
	@RequestMapping("/getshiyan29")
	@ResponseBody
	public DataTablesResponseInfo getshiyan29(@RequestParam HashMap<String, Object> mapParam){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mapParam.get("id"));
		List<ShiYan21Entity> list=  shiYan02Service.getShiYan29(map);
		Map<String, Object> mapDetail = new HashMap<String, Object>();
		mapDetail.put("serialnumber", list.get(0).getSerialNumber());
		List<ShiYan28DetailEntity> listDetail = shiYan02Service.getShiYanDetail29(mapDetail);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("listDetail", listDetail);
		dtri.setData(params);	
		return dtri;
	}
	
	/**
	 * 判定依据Test02集料取得  试验项目
	**/
	@RequestMapping("/getQualification.html")
	@ResponseBody
	public List<JudgingBasisTest02> getQualification(HttpServletRequest request,@RequestParam Map<String, Object> param){
		List<JudgingBasisTest02> list = new ArrayList<JudgingBasisTest02>();		
		list = shiYan02Service.getQualification(param);
		return list;
	}
	
	/**
	 * 判定依据Test02集料取得  技术指标
	**/
	@RequestMapping("/getPosition.html")
	@ResponseBody
	public List<Map<String,Object>> getPosition(HttpServletRequest request,@RequestParam Map<String, Object> param){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		list = shiYan02Service.getPosition(param);
		return list;
	}
	
	/**
	 * 试验21  粗集料筛分试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan21.html")
	public @ResponseBody ResponseInfo saveShiYan21(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		if (!"".equals(param.get("pass_Rate1").toString())) {
			shiYan21Entity.setPass_Rate1(Double.parseDouble(param.get("pass_Rate1").toString()));
		}
		if (!"".equals(param.get("pass_Rate2").toString())) {
			shiYan21Entity.setPass_Rate2(Double.parseDouble(param.get("pass_Rate2").toString()));
		}	
		
		String SerialNumber = param.get("serialNumber").toString();
		
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("data"));
		List<ShiYan21DetailEntity> shiYan21DetailList = new ArrayList<ShiYan21DetailEntity>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				ShiYan21DetailEntity shiYan21DetailEntity = new ShiYan21DetailEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				shiYan21DetailEntity.setSerialNumber(SerialNumber);
				int count = 0;
				if (!"".equals(job.get("sieve_Size").toString())) {
					shiYan21DetailEntity.setSieve_Size(Float.parseFloat(job.get("sieve_Size").toString()));
					count++;
				}		
				if (!"".equals(job.get("sub_Sieve_Resi").toString())) {
					shiYan21DetailEntity.setSub_Sieve_Resi1(Double.parseDouble(job.get("sub_Sieve_Resi").toString()));
					count++;
				}	
				if (!"".equals(job.get("total_Sieve_Resi").toString())) {
					shiYan21DetailEntity.setTotal_Sieve_Resi1(Double.parseDouble(job.get("total_Sieve_Resi").toString()));
					count++;
				}	
				if (!"".equals(job.get("pass_Rate").toString())) {
					shiYan21DetailEntity.setPass_Rate1(Double.parseDouble(job.get("pass_Rate").toString()));
					count++;
				}		
				if (!"".equals(job.get("avg_Pass_Rate").toString())) {
					shiYan21DetailEntity.setAvg_Pass_Rate(Double.parseDouble(job.get("avg_Pass_Rate").toString()));
					count++;
				}		
				if (!"".equals(job.get("set_Max_Pass_Rate").toString())) {
					shiYan21DetailEntity.setSet_Max_Pass_Rate(Float.parseFloat(job.get("set_Max_Pass_Rate").toString()));
					count++;
				}	
				if (!"".equals(job.get("set_Main_Pass_Rate").toString())) {
					shiYan21DetailEntity.setSet_Main_Pass_Rate(Float.parseFloat(job.get("set_Main_Pass_Rate").toString()));
					count++;
				}
				if (count!=0) {
					shiYan21DetailList.add(shiYan21DetailEntity);
				}			
			}
		}			
		try {
			shiYan02Service.saveShiYan21(shiYan21Entity,SerialNumber,shiYan21DetailList);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		
		return info;
	}
	
	/**
	 * 试验25  细集料筛分试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan25.html")
	public @ResponseBody ResponseInfo saveShiYan25(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}		
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}		
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		if (!"".equals(param.get("pass_Rate1").toString())) {
			shiYan21Entity.setPass_Rate1(Double.parseDouble(param.get("pass_Rate1").toString()));
		}
		if (!"".equals(param.get("pass_Rate2").toString())) {
			shiYan21Entity.setPass_Rate2(Double.parseDouble(param.get("pass_Rate2").toString()));
		}
		if (!"".equals(param.get("avg_Fineness_Modulus").toString())) {
			shiYan21Entity.setAvg_Fineness_Modulus(Double.parseDouble(param.get("avg_Fineness_Modulus").toString()));
		}
				
		String SerialNumber = param.get("serialNumber").toString();
		
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("data"));
		List<ShiYan21DetailEntity> shiYan21DetailList = new ArrayList<ShiYan21DetailEntity>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				ShiYan21DetailEntity shiYan21DetailEntity = new ShiYan21DetailEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				shiYan21DetailEntity.setSerialNumber(SerialNumber);
				int count = 0;
				if (!"".equals(job.get("sieve_Size").toString())) {
					shiYan21DetailEntity.setSieve_Size(Float.parseFloat(job.get("sieve_Size").toString()));
					count++;
				}		
				if (!"".equals(job.get("sub_Sieve_Resi").toString())) {
					shiYan21DetailEntity.setSub_Sieve_Resi1(Double.parseDouble(job.get("sub_Sieve_Resi").toString()));
					count++;
				}	
				if (!"".equals(job.get("total_Sieve_Resi").toString())) {
					shiYan21DetailEntity.setTotal_Sieve_Resi1(Double.parseDouble(job.get("total_Sieve_Resi").toString()));
					count++;
				}	
				if (!"".equals(job.get("pass_Rate").toString())) {
					shiYan21DetailEntity.setPass_Rate1(Double.parseDouble(job.get("pass_Rate").toString()));
					count++;
				}		
				if (!"".equals(job.get("avg_Pass_Rate").toString())) {
					shiYan21DetailEntity.setAvg_Pass_Rate(Double.parseDouble(job.get("avg_Pass_Rate").toString()));
					count++;
				}		
				if (!"".equals(job.get("set_Max_Pass_Rate").toString())) {
					shiYan21DetailEntity.setSet_Max_Pass_Rate(Float.parseFloat(job.get("set_Max_Pass_Rate").toString()));
					count++;
				}	
				if (!"".equals(job.get("set_Main_Pass_Rate").toString())) {
					shiYan21DetailEntity.setSet_Main_Pass_Rate(Float.parseFloat(job.get("set_Main_Pass_Rate").toString()));	
					count++;
				}
				if (count!=0) {
					shiYan21DetailList.add(shiYan21DetailEntity);
				}				
			}
		}
		try {
			shiYan02Service.saveShiYan25(shiYan21Entity,SerialNumber,shiYan21DetailList);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
			
		return info;
	}
	
	/**
	 * 试验22 粗集料含泥量试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan22.html")
	public @ResponseBody ResponseInfo saveShiYan22(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		shiYan21Entity.setJudgementIndex(param.get("judgementIndex").toString());
		
		String SerialNumber = param.get("serialNumber").toString();
		
		ShiYan22DetailEntity shiYan22DetailEntity = new ShiYan22DetailEntity();
		shiYan22DetailEntity.setSerialNumber(SerialNumber);
		if (!"".equals(param.get("bSmpl_Mass1").toString())) {
			shiYan22DetailEntity.setBSmpl_Mass1(Float.parseFloat(param.get("bSmpl_Mass1").toString()));
		}
		if (!"".equals(param.get("aSmpl_Mass1").toString())) {
			shiYan22DetailEntity.setASmpl_Mass1(Float.parseFloat(param.get("aSmpl_Mass1").toString()));
		}
		if (!"".equals(param.get("sediment_Percentage1").toString())) {
			shiYan22DetailEntity.setSediment_Percentage1(Double.parseDouble(param.get("sediment_Percentage1").toString()));		
		}
		if (!"".equals(param.get("bSmpl_Mass2").toString())) {
			shiYan22DetailEntity.setBSmpl_Mass2(Float.parseFloat(param.get("bSmpl_Mass2").toString()));
		}
		if (!"".equals(param.get("aSmpl_Mass2").toString())) {
			shiYan22DetailEntity.setASmpl_Mass2(Float.parseFloat(param.get("aSmpl_Mass2").toString()));
		}
		if (!"".equals(param.get("sediment_Percentage2").toString())) {
			shiYan22DetailEntity.setSediment_Percentage2(Double.parseDouble(param.get("sediment_Percentage2").toString()));
		}
		if (!"".equals(param.get("avg_Sedi_Perc").toString())) {
			shiYan22DetailEntity.setAvg_Sedi_Perc(Double.parseDouble(param.get("avg_Sedi_Perc").toString()));
		}	
		try {
			shiYan02Service.saveShiYan22(shiYan21Entity, SerialNumber, shiYan22DetailEntity);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		
		return info;
	}
	
	/**
	 * 试验26 细集料含泥量试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan26.html")
	public @ResponseBody ResponseInfo saveShiYan26(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		shiYan21Entity.setJudgementIndex(param.get("judgementIndex").toString());
		
		String SerialNumber = param.get("serialNumber").toString();
		
		ShiYan22DetailEntity shiYan22DetailEntity = new ShiYan22DetailEntity();
		shiYan22DetailEntity.setSerialNumber(SerialNumber);
		if (!"".equals(param.get("bSmpl_Mass1").toString())) {
			shiYan22DetailEntity.setBSmpl_Mass1(Float.parseFloat(param.get("bSmpl_Mass1").toString()));
		}
		if (!"".equals(param.get("aSmpl_Mass1").toString())) {
			shiYan22DetailEntity.setASmpl_Mass1(Float.parseFloat(param.get("aSmpl_Mass1").toString()));
		}
		if (!"".equals(param.get("sediment_Percentage1").toString())) {
			shiYan22DetailEntity.setSediment_Percentage1(Double.parseDouble(param.get("sediment_Percentage1").toString()));		
		}
		if (!"".equals(param.get("bSmpl_Mass2").toString())) {
			shiYan22DetailEntity.setBSmpl_Mass2(Float.parseFloat(param.get("bSmpl_Mass2").toString()));
		}
		if (!"".equals(param.get("aSmpl_Mass2").toString())) {
			shiYan22DetailEntity.setASmpl_Mass2(Float.parseFloat(param.get("aSmpl_Mass2").toString()));
		}
		if (!"".equals(param.get("sediment_Percentage2").toString())) {
			shiYan22DetailEntity.setSediment_Percentage2(Double.parseDouble(param.get("sediment_Percentage2").toString()));
		}
		if (!"".equals(param.get("avg_Sedi_Perc").toString())) {
			shiYan22DetailEntity.setAvg_Sedi_Perc(Double.parseDouble(param.get("avg_Sedi_Perc").toString()));
		}	
		
		try {
			shiYan02Service.saveShiYan26(shiYan21Entity, SerialNumber, shiYan22DetailEntity);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		
		return info;
	}
	
	/**
	 * 试验23 粗集料针、片状颗粒含量试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan23.html")
	public @ResponseBody ResponseInfo saveShiYan23(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		System.out.println(param);
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		if (!"".equals(param.get("samp_Mass").toString())) {
			shiYan21Entity.setSamp_Mass(Float.parseFloat(param.get("samp_Mass").toString()));
		}
		if (!"".equals(param.get("elon_Part_Mass").toString())) {
			shiYan21Entity.setElon_Part_Mass(Float.parseFloat(param.get("elon_Part_Mass").toString()));
		}
		if (!"".equals(param.get("elongated_Particles").toString())) {
			shiYan21Entity.setElongated_Particles(Double.parseDouble(param.get("elongated_Particles").toString()));
		}	
		shiYan21Entity.setJudgementIndex(param.get("judgementIndex").toString());
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());

		String SerialNumber = param.get("serialNumber").toString();	
		
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("data"));
		// 规准仪法
		List<ShiYan2302DetailEntity> shiYan2302DetailList = new ArrayList<ShiYan2302DetailEntity>();
		// 游标卡尺法
		List<ShiYan2301DetailEntity> shiYan2301DetailList = new ArrayList<ShiYan2301DetailEntity>();
		// 规准仪法
		if ("a".equals(param.get("test_Method").toString())) {
			if(jsonArray.size()>0){
				for(int i=0;i<jsonArray.size();i++){
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i);				
					ShiYan2302DetailEntity shiYan2302DetailEntity = new ShiYan2302DetailEntity();
					shiYan2302DetailEntity.setSerialNumber(SerialNumber);
					shiYan2302DetailEntity.setSingle_Stage(job.get("single_Stage").toString());
					if (!"".equals(job.get("acic_Parti_Mass").toString())) {
						shiYan2302DetailEntity.setAcic_Parti_Mass(Float.parseFloat(job.get("acic_Parti_Mass").toString()));
					}
					if (!"".equals(job.get("flaky_Parti_Mass").toString())) {
						shiYan2302DetailEntity.setFlaky_Parti_Mass(Float.parseFloat(job.get("flaky_Parti_Mass").toString()));
					}				
					shiYan2302DetailList.add(shiYan2302DetailEntity);
				}
			}
		} else {
			if(jsonArray.size()>0){				
				for(int i=0;i<jsonArray.size();i++){
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = jsonArray.getJSONObject(i); 
					ShiYan2301DetailEntity shiYan2301DetailEntity = new ShiYan2301DetailEntity();
					// 得到 每个对象中的属性值
					shiYan2301DetailEntity.setSerialNumber(SerialNumber);
					if (!"".equals(job.get("samp_Mass").toString())) {
						shiYan2301DetailEntity.setSamp_Mass1(Float.parseFloat(job.get("samp_Mass").toString()));
					}
					if (!"".equals(job.get("in_Samp_Mass").toString())) {
						shiYan2301DetailEntity.setIn_Samp_Mass1(Float.parseFloat(job.get("in_Samp_Mass").toString()));
					}
					if (!"".equals(job.get("elongated_Particles").toString())) {
						shiYan2301DetailEntity.setElongated_Particles1(Double.parseDouble(job.get("elongated_Particles").toString()));
					}
					if (!"".equals(job.get("avg_Elon_Part").toString())) {
						shiYan2301DetailEntity.setAvg_Elon_Part(Double.parseDouble(job.get("avg_Elon_Part").toString()));
					}
					if (!"".equals(job.get("samp_Mass2").toString())) {
						shiYan2301DetailEntity.setSamp_Mass2(Float.parseFloat(job.get("samp_Mass2").toString()));
					}
					if (!"".equals(job.get("in_Samp_Mass2").toString())) {
						shiYan2301DetailEntity.setIn_Samp_Mass2(Float.parseFloat(job.get("in_Samp_Mass2").toString()));
					}
					if (!"".equals(job.get("elongated_Particles2").toString())) {
						shiYan2301DetailEntity.setElongated_Particles2(Double.parseDouble(job.get("elongated_Particles2").toString()));
					}
					if (!"".equals(job.get("samp_Mass3").toString())) {
						shiYan2301DetailEntity.setSamp_Mass3(Float.parseFloat(job.get("samp_Mass3").toString()));
					}
					if (!"".equals(job.get("in_Samp_Mass3").toString())) {
						shiYan2301DetailEntity.setIn_Samp_Mass3(Float.parseFloat(job.get("in_Samp_Mass3").toString()));
					}
					if (!"".equals(job.get("elongated_Particles3").toString())) {
						shiYan2301DetailEntity.setElongated_Particles3(Double.parseDouble(job.get("elongated_Particles3").toString()));
					}
					
					shiYan2301DetailList.add(shiYan2301DetailEntity);
				}
			}
		}		
		
		try {
			shiYan02Service.saveShiYan23(shiYan21Entity,SerialNumber,shiYan2302DetailList,shiYan2301DetailList);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		return info;
	}
	
	/**
	 * 试验24 粗集料压碎值试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan24.html")
	public @ResponseBody ResponseInfo saveShiYan24(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		shiYan21Entity.setJudgementIndex(param.get("judgementIndex").toString());
		
		String SerialNumber = param.get("serialNumber").toString();	
		
		ShiYan24DetailEntity shiYan24DetailEntity = new ShiYan24DetailEntity();
		shiYan24DetailEntity.setSerialNumber(SerialNumber);
		if (!"".equals(param.get("tBefore_Mass1").toString())) {
			shiYan24DetailEntity.setTBefore_Mass1(Float.parseFloat(param.get("tBefore_Mass1").toString()));
		}
		if (!"".equals(param.get("tAfter_Mass1").toString())) {
			shiYan24DetailEntity.setTAfter_Mass1(Float.parseFloat(param.get("tAfter_Mass1").toString()));
		}
		if (!"".equals(param.get("crush_Value1").toString())) {
			shiYan24DetailEntity.setCrush_Value1(Double.parseDouble(param.get("crush_Value1").toString()));
		}
		if (!"".equals(param.get("tBefore_Mass2").toString())) {
			shiYan24DetailEntity.setTBefore_Mass2(Float.parseFloat(param.get("tBefore_Mass2").toString()));
		}
		if (!"".equals(param.get("tAfter_Mass2").toString())) {
			shiYan24DetailEntity.setTAfter_Mass2(Float.parseFloat(param.get("tAfter_Mass2").toString()));
		}
		if (!"".equals(param.get("crush_Value2").toString())) {
			shiYan24DetailEntity.setCrush_Value2(Double.parseDouble(param.get("crush_Value2").toString()));
		}
		if (!"".equals(param.get("tBefore_Mass3").toString())) {
			shiYan24DetailEntity.setTBefore_Mass3(Float.parseFloat(param.get("tBefore_Mass3").toString()));
		}
		if (!"".equals(param.get("tAfter_Mass3").toString())) {
			shiYan24DetailEntity.setTAfter_Mass3(Float.parseFloat(param.get("tAfter_Mass1").toString()));
		}
		if (!"".equals(param.get("crush_Value3").toString())) {
			shiYan24DetailEntity.setCrush_Value3(Double.parseDouble(param.get("crush_Value3").toString()));
		}
		if (!"".equals(param.get("avg_Crush_Value").toString())) {
			shiYan24DetailEntity.setAvg_Crush_Value(Double.parseDouble(param.get("avg_Crush_Value").toString()));
		}
		try {
			shiYan02Service.saveShiYan24(shiYan21Entity,SerialNumber,shiYan24DetailEntity);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		return info;
	}
	
	/**
	 * 试验27 水泥凝结时间 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan27.html")
	public @ResponseBody ResponseInfo saveShiYan27(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());
		shiYan21Entity.setStart_Hour(param.get("start_Hour").toString());
		shiYan21Entity.setStart_Minute(param.get("start_Minute").toString());
		shiYan21Entity.setInitial_Set_Hour(param.get("initial_Set_Hour").toString());
		shiYan21Entity.setInitial_Set_Minute(param.get("initial_Set_Minute").toString());
		shiYan21Entity.setFinal_Set_Hour(param.get("final_Set_Hour").toString());
		shiYan21Entity.setFinal_Set_Minute(param.get("final_Set_Minute").toString());
		shiYan21Entity.setInit_Set_Time(param.get("init_Set_Time").toString());
		shiYan21Entity.setFinal_Set_Time(param.get("final_Set_Time").toString());
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		
		try {
			shiYan02Service.saveShiYan27(shiYan21Entity);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}
		
		return info;
	}
	
	/**
	 * 试验28  粗集料筛分试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan28.html")
	public @ResponseBody ResponseInfo saveShiYan28(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());	
		
		String SerialNumber = param.get("serialNumber").toString();
		
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("data"));
		List<ShiYan28DetailEntity> shiYan28DetailList = new ArrayList<ShiYan28DetailEntity>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				ShiYan28DetailEntity shiYan28DetailEntity = new ShiYan28DetailEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				shiYan28DetailEntity.setSerialNumber(SerialNumber);
				int count = 0;
				if (!"".equals(job.get("test_Project").toString())) {
					shiYan28DetailEntity.setTest_Project(job.get("test_Project").toString());
					count++;
				}		
				if (!"".equals(job.get("technical_Index").toString())) {
					shiYan28DetailEntity.setTechnical_Index(job.get("technical_Index").toString());
					count++;
				}	
				if (!"".equals(job.get("test_Item").toString())) {
					shiYan28DetailEntity.setTest_Item(job.get("test_Item").toString());
					count++;
				}	
				if (!"".equals(job.get("detection_Result").toString())) {
					shiYan28DetailEntity.setDetection_Result(job.get("detection_Result").toString());
					count++;
				}		
				if (count!=0) {
					shiYan28DetailList.add(shiYan28DetailEntity);
				}			
			}
		}			
		try {
			shiYan02Service.saveShiYan28(shiYan21Entity,SerialNumber,shiYan28DetailList);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}		
		return info;
	}
	
	/**
	 * 试验29  细集料筛分试验 保存
	 * 
	**/	
	@RequestMapping("/saveShiYan29.html")
	public @ResponseBody ResponseInfo saveShiYan29(HttpServletRequest request,@RequestParam Map<String, Object> param) {
		ResponseInfo info = new ResponseInfo();
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");	
		
		ShiYan21Entity shiYan21Entity = new ShiYan21Entity();
		shiYan21Entity.setModifier(user.getUsercode());
		if (!"".equals(param.get("testDate").toString())) {
			shiYan21Entity.setTestDate(param.get("testDate").toString());
		}
		if (!"".equals(param.get("isQualifiedTest").toString())) {
			shiYan21Entity.setIsQualifiedTest(param.get("isQualifiedTest").toString());
		}
		shiYan21Entity.setSerialNumber(param.get("serialNumber").toString());	
		
		String SerialNumber = param.get("serialNumber").toString();
		
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(param.get("data"));
		List<ShiYan28DetailEntity> shiYan28DetailList = new ArrayList<ShiYan28DetailEntity>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				ShiYan28DetailEntity shiYan28DetailEntity = new ShiYan28DetailEntity();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				shiYan28DetailEntity.setSerialNumber(SerialNumber);
				int count = 0;
				if (!"".equals(job.get("test_Project").toString())) {
					shiYan28DetailEntity.setTest_Project(job.get("test_Project").toString());
					count++;
				}		
				if (!"".equals(job.get("technical_Index").toString())) {
					shiYan28DetailEntity.setTechnical_Index(job.get("technical_Index").toString());
					count++;
				}	
				if (!"".equals(job.get("test_Item").toString())) {
					shiYan28DetailEntity.setTest_Item(job.get("test_Item").toString());
					count++;
				}	
				if (!"".equals(job.get("detection_Result").toString())) {
					shiYan28DetailEntity.setDetection_Result(job.get("detection_Result").toString());
					count++;
				}		
				if (count!=0) {
					shiYan28DetailList.add(shiYan28DetailEntity);
				}			
			}
		}			
		try {
			shiYan02Service.saveShiYan29(shiYan21Entity,SerialNumber,shiYan28DetailList);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//修改成功提示
			info.setMessage(MessageUtilBlindSample.UPDATE_SUCCESS);
		} catch (Exception e) {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//修改失败
			info.setMessage(MessageUtilBlindSample.UPDATE_ERROR);
		}		
		return info;
	}
}
