package com.mixingStation.controller.meshInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.meshInfo.MeshSizeDataAnalysis;
import com.mixingStation.model.meshInfo.MeshSizeInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.meshInfo.MeshSizeService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;

/**
 * @since 筛孔信息Controller
 * @author Administrator
 */
@Controller
@RequestMapping("meshSize")
public class MeshSizeController extends BaseController {
	
	@Autowired
	private MeshSizeService meshSizeService;
	
	/**
	 * @since 获取筛孔规格信息列表
	 * @param request
	 * @param meshSize
	 * @return
	 */
	@RequestMapping("/queryMeshSizeList.action")
	public @ResponseBody List<MeshSizeInfo> queryMeshSizeList(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		// 获取当前登录用户
		UserInfo loginUser = new UserInfo();
		loginUser.setPowerOrgID(Integer.valueOf(params.get("orgId").toString()));
		
		MeshSizeInfo meshSize = new MeshSizeInfo();
		meshSize.setOrgId(loginUser.getPowerOrgID());
		List<MeshSizeInfo> list = meshSizeService.queryMeshSizeList(meshSize);
		return list;
	} 
	
	/**
	 * @since 新增筛孔规格信息
	 * @param request
	 * @param meshSize
	 * @return
	 */
	@RequestMapping("/insertMeshSizeList.action")
	public @ResponseBody ResponseInfo insertMeshSizeList(HttpServletRequest request, MeshSizeInfo meshSize) {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		// 创建人
		meshSize.setOperator(loginUser.getUserName());
		// 创建时间
		meshSize.setCreateDate(new Date());
		try {
			int result = meshSizeService.insertMeshSizeList(meshSize);
			if (result > 0) {
				// 操作成功
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			} else {
				// 操作失败
				info.setCode(MessageUtil.SERVER_ERROR);
				info.setMessage(MessageUtil.OPERATION_FAILED);
			}
		} catch (Exception e) {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	} 
	
	/**
	 * @since 修改筛孔规格信息
	 * @param request
	 * @param meshSize
	 * @return
	 */
	@RequestMapping("/updateMeshSizeList.action")
	public @ResponseBody ResponseInfo updateMeshSizeList(HttpServletRequest request, MeshSizeInfo meshSize) {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		// 修改人
		meshSize.setModifier(loginUser.getUserName());
		// 修改时间
		meshSize.setModifyDate(new Date());
		try {
			int result = meshSizeService.updateMeshSizeList(meshSize);
			if (result > 0) {
				// 操作成功
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			} else {
				// 操作失败
				info.setCode(MessageUtil.SERVER_ERROR);
				info.setMessage(MessageUtil.OPERATION_FAILED);
			}
		} catch (Exception e) {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	} 
	
	/**
	 * @since 删除筛孔规格信息
	 * @param request
	 * @param meshSize
	 * @return
	 */
	@RequestMapping("/deleteMeshSizeList.action")
	public @ResponseBody ResponseInfo deleteMeshSizeList(HttpServletRequest request, MeshSizeInfo meshSize) {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		// 修改人
		meshSize.setModifier(loginUser.getUserName());
		// 修改时间
		meshSize.setModifyDate(new Date());
		try {
			int result = meshSizeService.deleteMeshSizeList(meshSize);
			if (result > 0) {
				// 操作成功
				info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			} else {
				// 操作失败
				info.setCode(MessageUtil.SERVER_ERROR);
				info.setMessage(MessageUtil.OPERATION_FAILED);
			}
		} catch (Exception e) {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	} 
	
	/**
	 * @since 获取模板信息
	 * @param request
	 * @param meshSizeDataAnalysis
	 * @return
	 */
	@RequestMapping("/queryMeshSizeDataList.action")
	public @ResponseBody List<Map<String, Object>> queryMeshSizeDataList(HttpServletRequest request, MeshSizeDataAnalysis meshSizeDataAnalysis) {
		return meshSizeService.queryMeshSizeDataList(meshSizeDataAnalysis);
	} 
}
