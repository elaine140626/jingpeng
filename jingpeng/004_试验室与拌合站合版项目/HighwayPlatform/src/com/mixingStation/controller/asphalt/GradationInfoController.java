package com.mixingStation.controller.asphalt;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.asphalt.GradationInfoService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;

/**
 * @since 级配信息Controller
 * @author Administrator
 */
@Controller
@RequestMapping("gradation")
public class GradationInfoController extends BaseController{

	@Autowired
	private GradationInfoService gradationInfoService;
	
	/**
	 * @since 新建级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertGradationInfo.action")
	public @ResponseBody ResponseInfo insertGradationInfo(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		ResponseInfo info = new ResponseInfo();
		UserInfo loginUser = getLoginUser(request);
		int result = gradationInfoService.insertGradationInfo(params, loginUser);
		if(result > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("操作成功");
		}else {
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("操作失败");
		}
		return info;
	} 
	
	/**
	 * @since 修改级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateGradationInfo.action")
	public @ResponseBody ResponseInfo updateGradationInfo(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		ResponseInfo info = new ResponseInfo();
		UserInfo loginUser = getLoginUser(request);
		int result = gradationInfoService.updateGradationInfo(params, loginUser);
		if(result > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("操作成功");
		}else {
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("操作失败");
		}
		return info;
	} 
	
	/**
	 * @since 删除级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteGradationInfo.action")
	public @ResponseBody ResponseInfo deleteGradationInfo(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		ResponseInfo info = new ResponseInfo();
		UserInfo loginUser = getLoginUser(request);
		int result = gradationInfoService.deleteGradationInfo(params, loginUser);
		if(result > 0) {
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION);
			info.setMessage("操作成功");
		}else {
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage("操作失败");
		}
		return info;
	} 
	
	/**
	 * @since 查询级配信息列表
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/queryList.action")
	public @ResponseBody DataTablesResponseInfo queryList(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		UserInfo loginUser = new UserInfo();
		loginUser.setPowerOrgID(Integer.valueOf(params.get("orgId").toString()));
		DataTablesResponseInfo list = gradationInfoService.queryList(params, loginUser);
		return list;
	} 
	
	/**
	 * @since 查询级配信息
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/queryDataById.action")
	public @ResponseBody AsphaltGrading queryDataById(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		AsphaltGrading asphaltGrading = gradationInfoService.queryDataById(params);
		return asphaltGrading;
	} 
	
	/**
	 * @since 加载该条级配数据的筛孔模板列表的列
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/getColumnModel.action")
	public @ResponseBody List<Map<String, Object>> getColumnModel(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		UserInfo loginUser = getLoginUser(request);
		List<Map<String, Object>> list = gradationInfoService.getColumnModel(params, loginUser);
		return list;
	} 
}
