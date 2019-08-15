package com.mixingStation.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.system.EquipmentDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.system.EquipmentService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;
@Service
public class EquipmentServiceImpl extends BaseController implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	
	@Override
	public DataTablesResponseInfo getAllEquipmentInfo(Map<String, Object> map) {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		List<EquipmentInfo> dataList = equipmentDao.getAllEquipmentInfo(map);
		dtri.setData(dataList);
		return dtri;
	}

	@Override
	public ResponseInfo addEquipmentInfo(HttpServletRequest request,Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//新增拌和设备信息
		EquipmentInfo data = new EquipmentInfo();
		//设备编号
		data.setEquipmentNo(map.get("equipmentNo").toString());
		//拌和机名称
		data.setEquipmentName(map.get("equipmentName").toString());
		//拌和机型号
		data.setEquipmentModel(map.get("equipmentModel").toString());
		//设备状态
		data.setEquiState(Integer.parseInt(map.get("equiState").toString()));
		//设备状态时间
		data.setStateTime(map.get("stateTime").toString());
		//拌和机标识
		data.setEquipmentCode(map.get("equipmentCode").toString());
		//拌和机类型
		data.setEquipmentType(Integer.parseInt(map.get("equipmentType").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		//组织机构
		data.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//创建者
		data.setOperator(loginUser.getUserName());
		int result = equipmentDao.addEquipmentInfo(data);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	@Override
	public ResponseInfo updateEquipmentInfo(HttpServletRequest request,Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前的登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//新增拌和设备信息
		EquipmentInfo data = new EquipmentInfo();
		//id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//设备编号
		data.setEquipmentNo(map.get("equipmentNo").toString());
		//拌和机名称
		data.setEquipmentName(map.get("equipmentName").toString());
		//拌和机型号
		data.setEquipmentModel(map.get("equipmentModel").toString());
		//设备状态
		data.setEquiState(Integer.parseInt(map.get("equiState").toString()));
		//设备状态时间
		data.setStateTime(map.get("stateTime").toString());
		//拌和机标识
		data.setEquipmentCode(map.get("equipmentCode").toString());
		//拌和机类型
		data.setEquipmentType(Integer.parseInt(map.get("equipmentType").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		//修改者
		data.setModifier(loginUser.getUserName());
		int result = equipmentDao.updateEquipmentInfo(data);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	@Override
	public ResponseInfo deleteEquipmentInfo(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = equipmentDao.deleteEquipmentInfo(map);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	@Override
	public List<EquipmentInfo> getEquipmentInfoById(Map<String, Object> map) {
		return equipmentDao.getAllEquipmentInfo(map);
	}

}
