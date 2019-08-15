package com.mix.controller.cement;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.dao.cement.CementConstructionPropDao;
import com.mix.model.Cement_ConsPropDetailed;
import com.mix.model.Cement_ConstructionProportion;
import com.mix.model.DataTablesResponseInfo;
import com.mix.model.ResponseInfo;
import com.mix.model.Search;
import com.mix.model.User_Info;
import com.mix.service.cement.CementConstructionPropService;
import com.mix.util.DateConvert;
import com.mix.util.MessageUtil;

import net.sf.json.JSONArray;

/**
 * 
 * @Title 水泥施工配比
 * @author ygt
 * @date 2018年10月8日
 */
@Controller
@RequestMapping("/cementConstructionProp")
public class CementConstructionPropController {
	
	private static final Map<String, Object> cement_ConstructionProportion = null;
	@Autowired
	private CementConstructionPropService cementConstructionPropService;
	
	@RequestMapping("/cementConstructionProp.action")
	public String  getCementConstruction() {
		return "/view/sn/pbgl_2";
	}
	/**
	 * 查询水泥施工配比列表
	 * @param cement_ConstructionProportion
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getCementConstructionProp.action")
	public @ResponseBody DataTablesResponseInfo getCementConstructionProp(HttpServletRequest request,@RequestParam Map<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			
			List<Cement_ConstructionProportion> list = cementConstructionPropService.getCementConstructionProp(map);
			for(int i = 0; i < list.size(); i++) {
				String operate = "<span class='globalLoginBtn'><a href='javascript:void(0)' onclick='show(\""+list.get(i).getI_id()+"\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" + 
						"<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+"');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
				list.get(i).setOperate(operate);
				list.get(i).setSerialNumber(i+1);
				list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
			}
			dtri.setData(list);
			return dtri;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@RequestMapping("/addSgpbXx.action")//添加施工配比信息
	public @ResponseBody ResponseInfo addAsphTargetPro( @RequestParam Map<String, Object> map,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		Cement_ConstructionProportion cc = new Cement_ConstructionProportion();
		
		
		List<Cement_ConsPropDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Cement_ConsPropDetailed.class);
		
		
	    String str_operator = (String)map.get("str_operator");
	    //处理制定人  tongn
		if(str_operator == null || "".equals(str_operator)) {
			
			map.put("str_operator", user.getStr_operator());
			
			cc.setOperate(user.getStr_name());
		}
       
		//组织机构id
		int org_id = Integer.parseInt(map.get("i_org_Id").toString());
		cc.setI_org_Id(org_id);
		
		
		//施工配比编号
		cc.setStr_prop_Code(map.get("sgpb").toString());
		//产品id
		cc.setI_product_Id(Integer.parseInt(map.get("cpmcId").toString()));
		//产品名称
		cc.setStr_material_Name(map.get("cpmc").toString());
		//规格型号
		cc.setStr_material_Model(map.get("ggxh").toString());
		//理论配比id
		cc.setI_theoProp_Id(Integer.parseInt(map.get("llpbbhId").toString()));
		//理论配比编码
		cc.setStr_llProp_Code(map.get("llpbbh").toString());
		//设计强度
		cc.setStr_design_Strength(map.get("i_org_Id").toString());
		//水灰比
		cc.setD_water_Cement_Ratio(Double.parseDouble(map.get("shb").toString()));
		//砂率
		cc.setD_sand_Ratio(Double.parseDouble(map.get("sl").toString()));
		//塌落度
		cc.setI_slump_Low(Integer.parseInt(map.get("tlda").toString()));
		cc.setI_slump_High(Integer.parseInt(map.get("tldb").toString()));
		//备注
		cc.setStr_remarks(map.get("bz").toString());
		//创建人
		cc.setStr_operator(map.get("cjr").toString());
		cc.setI_org_Id(Integer.parseInt(map.get("i_org_Id").toString()));
		cc.setCement_ConsPropDetailedList(list);
		try {
			cementConstructionPropService.addSgpbXx(cc);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return info;
	}
	
	
	/**
	 * 查询配比编码是否重复
	 * @param str_prop_Code
	 * @return
	 */
	@RequestMapping("/getPropCode.action")
	public @ResponseBody ResponseInfo getPropCode(@RequestParam("str_prop_Code") String str_prop_Code) {
		ResponseInfo info = new ResponseInfo();
		//获得sessionId
		try {
			List<Cement_ConstructionProportion> list = cementConstructionPropService.getPropCode(str_prop_Code);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
			} else {
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 查询产品名称和规格型号是否重复
	 * @param str_prop_Code
	 * @return
	 */
	@RequestMapping("/getMaterNameAndModel.action")
	public @ResponseBody ResponseInfo getMaterNameAndModel(Cement_ConstructionProportion cement_ConstructionProportion) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<Cement_ConstructionProportion> list = cementConstructionPropService.getMaterNameAndModel(cement_ConstructionProportion);
			if(list != null && list.size() > 0) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
			} else {
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 按配合比ID查询配合比明细
	 * @param id
	 * @return
	 */
//	@RequestMapping("/getC_ConstructionDeatlByid")
//	public @ResponseBody List<Cement_ConsPropDetailed> getC_ConstructionDeatlByid(@RequestBody Cement_ConsPropDetailed cement_ConsPropDetailed) {
//		try {
//			return cementConstructionPropService.getC_ConstructionDeatlByid(cement_ConsPropDetailed);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	@RequestMapping("/getC_ConstructionDeatlByid.action")
	public @ResponseBody DataTablesResponseInfo getC_ConstructionDeatlByid(HttpServletRequest request,@RequestParam Map<String, Object> mapId) {
		try {
			DataTablesResponseInfo dtri = new DataTablesResponseInfo();
			Cement_ConstructionProportion cp = new Cement_ConstructionProportion();
			
			Cement_ConsPropDetailed cement_ConsPropDetailed = new Cement_ConsPropDetailed();
			cement_ConsPropDetailed.setI_consProp_Id(Integer.parseInt(mapId.get("i_id").toString()));
			List<Cement_ConsPropDetailed> Cement_ConsPropDetailed_list = null;
			try {
				Cement_ConsPropDetailed_list = cementConstructionPropService.select_Asph_TargetPropDetailed(cement_ConsPropDetailed);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map ccs = new HashMap();
			String c1 = "success";
			if(Cement_ConsPropDetailed_list.size()>0){
				c1 = MessageUtil.SERVER_ERROR;
				ccs.put("code", c1);
				ccs.put("message", MessageUtil.ALREADY_UPADTE);
			}
			cp.setI_id(Integer.parseInt(mapId.get("i_id").toString()));
			List<Cement_ConstructionProportion> cc = cementConstructionPropService.getMainById(cp);
			List<Map<String, Object>> cd = cementConstructionPropService.getMainByIdGrid(cc.get(0));
			Map mp = new HashMap();
			mp.put("cc", cc.get(0));
			mp.put("cd", cd);
			mp.put("ca", ccs);
			dtri.setData(mp);
			return dtri;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 添加水泥施工配比列
	 * @param cement_ConstructionProportion
	 * @return
	 */
	@RequestMapping("/addCementConstructionPro.action")
	public @ResponseBody ResponseInfo addCementConstructionPro(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		int i_org_Id = user.getI_power_Org_Id();
		try {
			map.put("i_org_Id", i_org_Id);
			map.put("i_id", 0);
			List<Cement_ConsPropDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Cement_ConsPropDetailed.class);
			map.put("list", list);
		    
		    String str_operator = (String)map.get("str_operator");
		    //处理制定人  tongn
			if("".equals(str_operator)) {
				
				map.put("str_operator", user.getStr_operator());
			}
			cementConstructionPropService.addCementConstructionPro(map);

			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	/**
	 * 修改水泥施工配比列表
	 * @param cement_ConstructionProportion
	 * @return
	 * @throws DataAccessException 
	 */
	@RequestMapping("/updateCementConstructionPro.action")
	public @ResponseBody ResponseInfo updateCementConstructionPro(HttpServletRequest request,@RequestParam Map<String, Object> map) throws DataAccessException {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		int i_org_Id = user.getI_power_Org_Id();
		try {
			map.put("i_org_Id",i_org_Id);//获得sessionId
			List<Cement_ConsPropDetailed> list = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Cement_ConsPropDetailed.class);
			map.put("list", list);
			Cement_ConsPropDetailed cement_ConsPropDetailed = new Cement_ConsPropDetailed();
			
		cement_ConsPropDetailed.setI_consProp_Id(Integer.parseInt(map.get("i_id").toString()));
			
			List<Cement_ConsPropDetailed> Cement_ConsPropDetailed_list = cementConstructionPropService.select_Asph_TargetPropDetailed(cement_ConsPropDetailed);
			if(Cement_ConsPropDetailed_list.size()>0){
				info.setCode(MessageUtil.SERVER_ERROR);
				//已在料仓调用,无法修改
				info.setMessage(MessageUtil.Existence_Of_Oorresponding_Relation_UPADTE);
				
			}else {
			cementConstructionPropService.updateCementConstructionPro(map);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	@RequestMapping("/delCementConstructionPro.action")
	public @ResponseBody ResponseInfo delCementConstructionPro(Cement_ConstructionProportion cement_ConstructionProportion) {
		ResponseInfo info = new ResponseInfo();
		//获得sessionId
		try {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("i_id", cement_ConstructionProportion.getI_id());
			map.put("i_org_Id", cement_ConstructionProportion.getI_org_Id());
			
			//判断可以删除原材料条件 tongn
			List<Map<String,Object>> deletcementConstructionPropList = cementConstructionPropService.getCementConstructionProportionByID(map);
			map = deletcementConstructionPropList.get(0);
			
			if((Integer)map.get("PropidCount")>0) {
				//服务器遇到错误 500
				info.setCode(MessageUtil.SERVER_ERROR);
				//施工配合比操作失败，已在料仓对应关系中调用 
				info.setMessage(MessageUtil.Existence_Of_Oorresponding_Relation);
				
			} else {
			cementConstructionPropService.delCementConstructionPro(cement_ConstructionProportion);

			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	
	
	@RequestMapping("/getTheoryProportionCode.action")
	public @ResponseBody List<Map<String, Object>> getTheoryProportionCode(HttpServletRequest request,Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		int i_org_Id = user.getI_power_Org_Id();
		map.put("i_org_Id", i_org_Id);//获得sessionId
		try {
			return cementConstructionPropService.getgetTheoryProportionCode(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**通过产品iD查配比
	 * @param cement_ConstructionProportion
	 * @return
	 */
	@RequestMapping("/getCementConstructionPropbypid.action")
	public @ResponseBody DataTablesResponseInfo  getCementConstructionPropbypid(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		try {
			List list = new ArrayList();
			List  list1 = cementConstructionPropService.getCementConstructionPropbypid(map);
			List  list2 = cementConstructionPropService.getCementConstructionPropbypidList(map);
			list.add(list1);
			list.add(list2);
			dtr.setData(list);
			return dtr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	@RequestMapping("/getCementConstructionPropbypids.action")
	public @ResponseBody List<Cement_ConstructionProportion>  getCementConstructionPropbypids(HttpServletRequest request, @RequestParam String i_product_Id,String i_org_Id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("i_org_Id",i_org_Id);
		List<Cement_ConstructionProportion> list;
		try {
			int[] org_id =  new int[1];
			 org_id[0] =  	Integer.valueOf(map.get("i_org_Id").toString());
			 map.put("org_Ids",org_id);
			 map.put("i_product_Id",Integer.parseInt(i_product_Id));
		
			list = cementConstructionPropService.getCementConstructionPropbypids(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	@RequestMapping("/getTheory.action")
	public @ResponseBody List<Cement_ConstructionProportion>  getTheory(HttpServletRequest request, @RequestParam String i_product_Id,String i_org_Id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("i_org_Id",i_org_Id);
		try {
			int[] org_id =  new int[1];
			 org_id[0] =  	Integer.valueOf(map.get("i_org_Id").toString());
			 map.put("org_Ids",org_id);
			 map.put("i_product_Id",Integer.parseInt(i_product_Id));
		
			List li = cementConstructionPropService.getTheory(map);
			return li;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	@RequestMapping("/getValue.action")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
	
	@RequestMapping("/getSgpbNo.action")
	public @ResponseBody DataTablesResponseInfo  getSgpbNo(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		try {
			List  li = cementConstructionPropService.getSgpbNo(map);
			dtr.setData(li);
			return dtr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; 
	}
}
