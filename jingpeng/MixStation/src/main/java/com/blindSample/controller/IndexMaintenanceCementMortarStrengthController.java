package com.blindSample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blindSample.model.JudgingBasis04006;
import com.blindSample.model.JudgingBasis08001;
import com.blindSample.model.JudgingBasis0900101;
import com.blindSample.model.JudgingBasisDeleteEntity;
import com.blindSample.model.JudgingBasisTest10;
import com.blindSample.model.TestUser_Info;
import com.blindSample.service.AppJpushService;
import com.blindSample.service.IndexMaintenanceCementMortarStrengthService;
import com.blindSample.util.MessageUtilBlindSample;
import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.ResponseInfo;

@Controller
@RequestMapping("/BlindSample")
public class IndexMaintenanceCementMortarStrengthController{	
	@Autowired
	IndexMaintenanceCementMortarStrengthService  indexMaintenanceCementMortarStrengthService;
	@Autowired
	AppJpushService appJpushService;

	/**
	 * 初始化方法 tab1
	 * 
	**/
	@RequestMapping("/shiyan_6.html")
	public String getIndexMaintenance1() {
		return "/BlindnessTest/shiyan_6";
	}
	
	/**
	 * 初始化方法 tab2
	 * 
	**/
	@RequestMapping("/shiyan_6_2.html")
	public String getIndexMaintenance2() {
		return "/BlindnessTest/shiyan_6_2";
	}
	
	/**
	 * 初始化方法 tab3
	 * 
	**/
	@RequestMapping("/shiyan_6_3.html")
	public String getIndexMaintenance3() {
		return "/BlindnessTest/shiyan_6_3";
	}
	
	/**
	 * 初始化方法 tab4
	 * 
	**/
	@RequestMapping("/shiyan_6_4.html")
	public String getIndexMaintenance4() {
		return "/BlindnessTest/shiyan_6_4";
	}
	
	/**
	 * 初始化方法 tab5
	 * 
	**/
	@RequestMapping("/shiyan_6_5.html")
	public String getIndexMaintenance5() {
		return "/BlindnessTest/shiyan_6_5";
	}
	
	/**
	 * tab1 水泥品种列表获取
	 * 
	**/
	@RequestMapping("/getCementTypeList.html")
	@ResponseBody
	public List<Map<String, Object>> getTestRoomList(HttpServletRequest request){		
		Map<String, Object> map = null;	
		List<Map<String, Object>> list = indexMaintenanceCementMortarStrengthService.getCementTypeList(map);
		return list;
	}
	
	/**
	 * tab1 水泥胶砂强度 List 
	 * 
	**/	
	@RequestMapping("/getCementMortarStrength.html")
	public @ResponseBody DataTablesResponseInfo getCementMortarStrength(){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = null;	
		List<Map<String, Object>> list =  indexMaintenanceCementMortarStrengthService.getCementMortarStrength(map);
		if(list != null) {	
			String cementType = "";
			int count = 0;
			//外层循环 有多少分页 循环几次
			for(int k = 0;k<list.size()/14+1;k++) {
				int end = 14*(k+1);
				if (end>list.size()) {
					end = list.size();
				}
				String inCementType = "";
				//内层循环，按照每个页的个数循环
				for(int i = 14*k; i < end; i++) {				
					if(inCementType.equals(list.get(i).get("CementType"))) {
						list.get(i).put("merge", 0);
					} else {	
						if (!cementType.equals(list.get(i).get("CementType"))) {
							count++;
						}									
						int merge = 0;
						for(int j= 14*k; j < end; j++) {
							if (list.get(i).get("CementType").equals(list.get(j).get("CementType"))) {
								merge++;
							}
						}
						list.get(i).put("merge", merge);
					}
					list.get(i).put("serialNumber", count);
					
					inCementType = (String) list.get(i).get("CementType");
					String delete = "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).get("id")+"\");'>删除</a></span>";
					list.get(i).put("delete", delete);
				}
				cementType = (String) list.get(end-1).get("CementType");
			}
			dtri.setData(list);
		}
		return dtri;
	}
	
	/**
	 * tab2 沥青三大指标   List
	 * 
	**/	
	@RequestMapping("/getAsphalt.html")
	public @ResponseBody DataTablesResponseInfo getAsphalt(){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = null;	
		List<Map<String, Object>> list =  indexMaintenanceCementMortarStrengthService.getAsphalt(map);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {				
				list.get(i).put("serialNumber", i+1);					
				String delete = "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).get("id")+"\");'>删除</a></span>";
				list.get(i).put("delete", delete);
			}
			dtri.setData(list);
		}		
		return dtri;
	}
	
	/**
	 * tab3 马歇尔   List
	 * 
	**/	
	@RequestMapping("/getMarshall.html")
	public @ResponseBody DataTablesResponseInfo getMarshall(){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = null;	
		List<Map<String, Object>> list =  indexMaintenanceCementMortarStrengthService.getMarshall(map);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {				
				list.get(i).put("serialNumber", i+1);					
				String delete = "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).get("id")+"\");'>删除</a></span>";
				list.get(i).put("delete", delete);
			}
			dtri.setData(list);				
		}		
		return dtri;
	}
	
	/**
	 * tab4  钢筋抗拉强度、屈服强度、伸长率、冷弯试验 List
	 * 
	**/	
	@RequestMapping("/getRebar.html")
	public @ResponseBody DataTablesResponseInfo getRebar(){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = null;	
		List<Map<String, Object>> list =  indexMaintenanceCementMortarStrengthService.getRebar(map);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {				
				list.get(i).put("serialNumber", i+1);					
				String delete = "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).get("id")+"\");'>删除</a></span>";
				list.get(i).put("delete", delete);
			}
			dtri.setData(list);
		}		
		return dtri;
	}
	
	/**
	 * tab5  钢筋接头抗拉强度、冷弯试验 List
	 * 
	**/	
	@RequestMapping("/getRebarJoint.html")
	public @ResponseBody DataTablesResponseInfo getRebarJoint(){	
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		Map<String, Object> map = null;	
		List<Map<String, Object>> list =  indexMaintenanceCementMortarStrengthService.getRebarJoint(map);
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {				
				list.get(i).put("serialNumber", i+1);					
				String delete = "<span><a href='javascript:void(0)' onclick='del(\""+list.get(i).get("id")+"\");'>删除</a></span>";
				list.get(i).put("delete", delete);
			}
			dtri.setData(list);
		}		
		return dtri;
	}
	
	/**
	 * tab1 水泥胶砂强度 插入数据
	 * 
	**/
	@RequestMapping("/addCementMortarStrength.html")
	public @ResponseBody ResponseInfo addCementMortarStrength(HttpServletRequest request, JudgingBasis04006 judgingBasis04006) {
		ResponseInfo info = new ResponseInfo();
	
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		judgingBasis04006.setOperator(user.getUsercode());
		judgingBasis04006.setModifier(user.getUsercode());

		List<Map<String, Object>> list = indexMaintenanceCementMortarStrengthService.getCementMortarStrengthCount(judgingBasis04006);
		if (0 == (Integer)list.get(0).get("count")) {
			indexMaintenanceCementMortarStrengthService.addCementMortarStrength(judgingBasis04006);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//水泥品种和强度等级不能重复
			info.setMessage(MessageUtilBlindSample.CEMENT_NOREPEAT);
		}
		
		return info;
	}
	
	/**
	 * tab2 沥青三大指标 插入数据
	 * 
	**/
	@RequestMapping("/addAsphalt.html")
	public @ResponseBody ResponseInfo addAsphalt(HttpServletRequest request, JudgingBasis08001 judgingBasis08001) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		judgingBasis08001.setOperator(user.getUsercode());
		judgingBasis08001.setModifier(user.getUsercode());
		List<Map<String, Object>> list = indexMaintenanceCementMortarStrengthService.getAsphaltCount(judgingBasis08001);
		if (0 == (Integer)list.get(0).get("count")) {
			indexMaintenanceCementMortarStrengthService.addAsphalt(judgingBasis08001);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//沥青种类和针入度不能重复
			info.setMessage(MessageUtilBlindSample.ASPHALT_NOREPEAT);
		}
		
		return info;
	}
	
	/**
	 * tab3   马歇尔 插入数据 
	 * 
	**/
	@RequestMapping("/addMarshall.html")
	public @ResponseBody ResponseInfo addMarshall(HttpServletRequest request, JudgingBasis0900101 judgingBasis0900101) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		judgingBasis0900101.setOperator(user.getUsercode());
		judgingBasis0900101.setModifier(user.getUsercode());
		List<Map<String, Object>> list = indexMaintenanceCementMortarStrengthService.getMarshallCount(judgingBasis0900101);
		if (0 == (Integer)list.get(0).get("count")) {
			indexMaintenanceCementMortarStrengthService.addMarshall(judgingBasis0900101);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//稳定度不能重复
			info.setMessage(MessageUtilBlindSample.MARSHALL_NOREPEAT);
		}
		
		return info;
	}
	
	/**
	 * tab4  钢筋抗拉强度、屈服强度、伸长率、冷弯试验   && tab5 钢筋接头抗拉强度、冷弯试验  插入数据
	 * 
	**/
	@RequestMapping("/addRebar.html")
	public @ResponseBody ResponseInfo addRebar(HttpServletRequest request, JudgingBasisTest10 judgingBasisTest10) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		judgingBasisTest10.setOperator(user.getUsercode());
		judgingBasisTest10.setModifier(user.getUsercode());
		List<Map<String, Object>> list = indexMaintenanceCementMortarStrengthService.getRebarCount(judgingBasisTest10);
		if (0 == (Integer)list.get(0).get("count")) {
			indexMaintenanceCementMortarStrengthService.addRebar(judgingBasisTest10);
			//成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			//多种选择 300
			info.setCode(MessageUtilBlindSample.MULTIPLE_CHOICES);
			//钢筋牌号和直径
			info.setMessage(MessageUtilBlindSample.REBAR_NOREPEAT);
		}
		
		return info;
	}
	
	/**
	 * tab1 水泥胶砂强度 删除数据
	 * tab2 沥青三大指标 删除数据
	 * tab3 马歇尔 删除数据
	 * tab4 钢筋抗拉强度、屈服强度、伸长率、冷弯试验 删除数据 
	 * tab5 钢筋接头抗拉强度、冷弯试验 
	**/
	@RequestMapping("/delCementMortarStrength.html")
	public @ResponseBody ResponseInfo delCementMortarStrength(@RequestBody JudgingBasisDeleteEntity judgingBasisDeleteEntity,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		
		HttpSession session = request.getSession();
		TestUser_Info user = (TestUser_Info) session.getAttribute("user");		
		judgingBasisDeleteEntity.setModifier(user.getUsercode());

		if ("1".equals(judgingBasisDeleteEntity.getFlg())) {
			//tab1 水泥胶砂强度 删除数据
			indexMaintenanceCementMortarStrengthService.updateCementMortarStrength(judgingBasisDeleteEntity);
		}
		if ("2".equals(judgingBasisDeleteEntity.getFlg())) {
			//tab2 沥青三大指标 删除数据
			indexMaintenanceCementMortarStrengthService.updateAsphalt(judgingBasisDeleteEntity);
		}
		if ("3".equals(judgingBasisDeleteEntity.getFlg())) {
			//tab3 马歇尔 删除数据
			indexMaintenanceCementMortarStrengthService.updateMarshall(judgingBasisDeleteEntity);
		} else {
			//tab4 钢筋抗拉强度、屈服强度、伸长率、冷弯试验 删除数据 
			//tab5 钢筋接头抗拉强度、冷弯试验 
			indexMaintenanceCementMortarStrengthService.updateRebar(judgingBasisDeleteEntity);
		}
				
		//成功处理请求提示 200
		info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
		//操作成功提示
		info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		
		return info;
	}		
	
}