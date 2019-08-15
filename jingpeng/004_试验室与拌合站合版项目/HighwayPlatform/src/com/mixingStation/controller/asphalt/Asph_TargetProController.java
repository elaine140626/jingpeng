package com.mixingStation.controller.asphalt;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.Asph_TargetPropDetailed;
import com.mixingStation.model.asphalt.Asph_TargetProportion;
import com.mixingStation.service.asphalt.Asph_TargetProService;
import com.mixingStation.util.MessageUtil;

import net.sf.json.JSONArray;


/**
 * 目标配合比Controller
 */
@Controller
@RequestMapping("/AsphTargetPro")
public class Asph_TargetProController{
	@Autowired
	private Asph_TargetProService asph_TargetProService;
	/**
	 * 目标配合比列表
	 */
	@RequestMapping("/getAsphTargetPro.action")
	public @ResponseBody DataTablesResponseInfo getAsphTargetPro(HttpServletRequest request,@RequestParam Map<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<Asph_TargetProportion> list = asph_TargetProService.getAsphTargetPro(map);
		dtri.setData(list);
		return dtri;
	}
	/**
	 * 插入沥青目标配合比
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/addAsphTargetPro.action")
	public @ResponseBody ResponseInfo addAsphTargetPro( @RequestParam Map<String, Object> map,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		List<Asph_TargetPropDetailed> asph_TargetPropList = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asph_TargetPropDetailed.class);
		Asph_TargetProportion asphTargetPro = new Asph_TargetProportion();
		for(int i = asph_TargetPropList.size() - 1; i >= 0; i--){
			Asph_TargetPropDetailed item = asph_TargetPropList.get(i);
			if(asph_TargetPropList.get(i) == null) {
				asph_TargetPropList.remove(item);
			}
			if(asph_TargetPropList.get(i).getI_materials_Id() == 0) {
				asph_TargetPropList.remove(item);
			}
		}
		try {
			asphTargetPro.setProportion_Code(map.get("str_proportion_Code").toString());
				asphTargetPro.setValid_Flag(1);
				asphTargetPro.setI_upload(0);
				asphTargetPro.setOperator(map.get("operator").toString());
				asphTargetPro.setOrg_Id(Integer.parseInt(map.get("i_org_Id").toString()));
				asphTargetPro.setProduct_Id(Integer.parseInt(map.get("i_product_Id").toString()));
				asphTargetPro.setRemarks(map.get("str_remarks").toString());
				asphTargetPro.setAsph_TargetPropList(asph_TargetPropList);
				asph_TargetProService.addAsphTargetPro(asphTargetPro);
				//成功处理请求提示 200
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				//操作成功提示
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	/**
	 * 更新沥青目标配合比
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAsphTargetPro.action")
	public @ResponseBody ResponseInfo updateAsphTargetPro(HttpServletRequest request,@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		List<Asph_TargetPropDetailed> asph_TargetPropList = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), Asph_TargetPropDetailed.class);
		Asph_TargetProportion asph_TargetPro = new Asph_TargetProportion();
		try {
			for(int i = asph_TargetPropList.size() - 1; i >= 0; i--){
				Asph_TargetPropDetailed item = asph_TargetPropList.get(i);
				if(asph_TargetPropList.get(i) == null) {
					asph_TargetPropList.remove(item);
				}
				if(asph_TargetPropList.get(i).getI_materials_Id() == 0) {
					asph_TargetPropList.remove(item);
				}
			}
			asph_TargetPro.setId(Integer.parseInt(map.get("i_id").toString()));
			asph_TargetPro.setValid_Flag(1);
			asph_TargetPro.setI_upload(0);
			asph_TargetPro.setProduct_Id(Integer.parseInt(map.get("i_product_Id").toString()));
			asph_TargetPro.setRemarks(map.get("str_remarks").toString());
			asph_TargetPro.setModifier(map.get("operator").toString());;
			asph_TargetPro.setProportion_Code(map.get("str_proportion_Code").toString());
			asph_TargetPro.setAsph_TargetPropList(asph_TargetPropList);
			asph_TargetProService.updateAsphTargetPro(asph_TargetPro);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
		    info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	/**
	 * 删除沥青目标配合比
	 * @param asphTargetPro
	 * @return
	 */
	@RequestMapping("/delAsphTargetPro.action")
	public @ResponseBody ResponseInfo delAsphTargetPro(Asph_TargetProportion asphTargetPro) {
		ResponseInfo info = new ResponseInfo();
		int count = asph_TargetProService.getAsphTargetProRepeat(asphTargetPro);
		try {
			if(count>0) {
				info.setCode(MessageUtil.SERVER_ERROR);
				info.setMessage(MessageUtil.ALREADY_Proportion);
		}else {
			asph_TargetProService.delAsphTargetPro(asphTargetPro);
			asph_TargetProService.delAsph_TargetPropDetailed(asphTargetPro);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		}
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
	/**
	 * 按id查询目标配合比
	 * @param asphTargetPro
	 * @return
	 */
	@RequestMapping("/getAsphTargetProById.action")
	public @ResponseBody Map<String, Object> getAsphTargetProById(Asph_TargetProportion asphTargetPro) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = asph_TargetProService.getAsphTargetProRepeat(asphTargetPro);
		Map<String, Object> ccs = new HashMap<String, Object>();
		String c1 = "success";
		try {
			if(count>0) {
				c1 = MessageUtil.MULTIPLE_CHOICES;
				ccs.put("message", MessageUtil.ALREADY_UPADTE);
			}else {
				List<Asph_TargetProportion> Asph_TargetProlList = asph_TargetProService.getAsphTargetProById(asphTargetPro);
				asphTargetPro = Asph_TargetProlList.get(0);
				map.put("asphTargetPro", asphTargetPro);
				List<Map<String, Object>> asphTargetProDList = asph_TargetProService.getAsphTargetProD(asphTargetPro);
				map.put("asphTargetProDList", asphTargetProDList);
			}
			ccs.put("code", c1);
			map.put("c1", ccs);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
