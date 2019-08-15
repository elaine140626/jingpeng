package com.mixingStation.service.cement.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.cement.CementProductionPlanDao;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.cement.CementConstructionProportion;
import com.mixingStation.model.cement.CementProductionPlan;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.cement.CementProductionPlanService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;
@Service
public class CementProductionPlanServiceImpl extends BaseController implements CementProductionPlanService {

	@Autowired
	private CementProductionPlanDao cementProductionPlanDao;

	@Override
	public List<CementProductionPlan> getAllCementProductionPlan(Map<String, Object> map) {
		return cementProductionPlanDao.getAllCementProductionPlan(map);
	}

	@Override
	public List<EquipmentInfo> getEquipmentInfo(Map<String, Object> map) {
		return cementProductionPlanDao.getEquipmentInfo(map);
	}

	@Override
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return cementProductionPlanDao.getMaterialNameList(map);
	}

	@Override
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map) {
		return cementProductionPlanDao.getMaterialModelList(map);
	}

	@Override
	public List<CementConstructionProportion> getConstructionPropCode(Map<String, Object> map) {
		return cementProductionPlanDao.getConstructionPropCode(map);
	}

	@Override
	public List<Map<String, Object>> getProjPos(Map<String, Object> map) {
		return cementProductionPlanDao.getProjPos(map);
	}

	@Override
	public List<Map<String, Object>> getConstructionUnit(Map<String, Object> map) {
		return cementProductionPlanDao.getConstructionUnit(map);
	}

	@Override
	public List<Map<String, Object>> getWateringSite(Map<String, Object> map) {
		return cementProductionPlanDao.getWateringSite(map);
	}

	@Override
	public ResponseInfo addCementProductionPlan(HttpServletRequest request, Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//新增生产计划
		CementProductionPlan data = new CementProductionPlan();
		//生产计划编号
		data.setPlanNo(map.get("planNo").toString());
		//拌合设备名称
		data.setEquId(Integer.parseInt(map.get("equId").toString()));
		//产品名称/产品型号
		data.setProductId(Integer.parseInt(map.get("materialModel").toString()));
		//施工配比
		data.setConsPropId(Integer.parseInt(map.get("consPropId").toString()));
		//施工地点
		data.setProjPos(map.get("projPos").toString());
		//施工单位
		data.setConstructionUnit(map.get("constructionUnit").toString());
		//浇灌部位
		data.setWateringSite(map.get("wateringSite").toString());
		//计划生产量(t)
		data.setScheduledProduction(Double.valueOf(map.get("scheduledProduction").toString()));
		//计划生产时间
		data.setStartTime(map.get("startTime").toString());
		//组织机构ID
		data.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//创建者
		data.setOperator(loginUser.getUserName());
		int result = cementProductionPlanDao.addCementProductionPlan(data);
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
	public List<CementProductionPlan> getCementProductionPlanById(Map<String, Object> map) {
		return cementProductionPlanDao.getCementProductionPlanById(map);
	}

	@Override
	public ResponseInfo updateCementProductionPlan(HttpServletRequest request,Map<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//修改生产计划
		CementProductionPlan data = new CementProductionPlan();
		//生产计划编号
		data.setPlanNo(map.get("planNo").toString());
		//拌合设备名称
		data.setEquId(Integer.parseInt(map.get("equId").toString()));
		//产品名称/产品型号
		data.setProductId(Integer.parseInt(map.get("materialModel").toString()));
		//施工配比
		data.setConsPropId(Integer.parseInt(map.get("consPropId").toString()));
		//施工地点
		data.setProjPos(map.get("projPos").toString());
		//施工单位
		data.setConstructionUnit(map.get("constructionUnit").toString());
		//浇灌部位
		data.setWateringSite(map.get("wateringSite").toString());
		//计划生产量(t)
		data.setScheduledProduction(Double.valueOf(map.get("scheduledProduction").toString()));
		//计划生产时间
		data.setStartTime(map.get("startTime").toString());
		//id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//创建者
		data.setModifier(loginUser.getUserName());
		int result = cementProductionPlanDao.updateCementProductionPlan(data);
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
	public ResponseInfo deleteCementProductionPlan(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = cementProductionPlanDao.deleteCementProductionPlan(map);
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
	public ResponseInfo zfCementProductionPlan(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = cementProductionPlanDao.zfCementProductionPlan(map);
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
	
}
