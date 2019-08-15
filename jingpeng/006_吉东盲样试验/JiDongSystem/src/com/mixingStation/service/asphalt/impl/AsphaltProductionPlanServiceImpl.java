package com.mixingStation.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.asphalt.AsphaltProductionPlanDao;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.AsphaltGrading;
import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.asphalt.AsphaltProductionPlan;
import com.mixingStation.model.asphalt.EquipmentInfo;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.OrganizationInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.asphalt.AsphaltProductionPlanService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;
@Service
public class AsphaltProductionPlanServiceImpl extends BaseController implements AsphaltProductionPlanService {

	@Autowired
	private AsphaltProductionPlanDao asphaltProductionPlanDao;
	
	@Override
	public List<AsphaltProductionPlan> getAsphaltProductionPlanInfo(Map<String, Object> map) {
		return asphaltProductionPlanDao.getAsphaltProductionPlanInfo(map);
	}

	@Override
	public ResponseInfo addAsphaltProductionPlan(HttpServletRequest request,Map<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//新增生产计划
		AsphaltProductionPlan data = new AsphaltProductionPlan();
		//生产计划编号
		data.setPlanNo(map.get("planNo").toString());
		//拌合设备名称
		data.setEquId(Integer.parseInt(map.get("equId").toString()));
		//产品名称/产品型号
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		//生产配合比编号
		data.setProdId(Integer.parseInt(map.get("prodId").toString()));
		//级配比ID
		data.setGradId(Integer.parseInt(map.get("gradId").toString()));
		//工程部位/用途
		data.setProjPos(map.get("projPos").toString());
		//施工单位
		data.setConstructionUnit(map.get("constructionUnit").toString());
		//计划生产量(t)
		data.setScheduledProduction(Double.valueOf(map.get("scheduledProduction").toString()));
		//计划生产时间
		data.setStartTime(map.get("startTime").toString());
		//组织机构ID
		data.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//创建者
		data.setOperator(loginUser.getUserName());
		int result = asphaltProductionPlanDao.addAsphaltProductionPlan(data);
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
	public ResponseInfo updateAsphaltProductionPlan(HttpServletRequest request,Map<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//当前登录用户
		UserInfo loginUser = this.getLoginUser(request);
		//修改生产计划
		AsphaltProductionPlan data = new AsphaltProductionPlan();
		//id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//生产计划编号
		data.setPlanNo(map.get("planNo").toString());
		//拌合设备名称
		data.setEquId(Integer.parseInt(map.get("equId").toString()));
		//产品名称/产品型号
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		//生产配合比编号
		data.setProdId(Integer.parseInt(map.get("prodId").toString()));
		//级配编号
		data.setGradId(Integer.parseInt(map.get("gradId").toString()));
		//工程部位/用途
		data.setProjPos(map.get("projPos").toString());
		//施工单位
		data.setConstructionUnit(map.get("constructionUnit").toString());
		//计划生产量(t)
		data.setScheduledProduction(Double.valueOf(map.get("scheduledProduction").toString()));
		//计划生产时间
		data.setStartTime(map.get("startTime").toString());
		//修改者
		data.setModifier(loginUser.getUserName());
		int result = asphaltProductionPlanDao.updateAsphaltProductionPlan(data);
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
	public ResponseInfo deleteAsphaltProductionPlan(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = asphaltProductionPlanDao.deleteAsphaltProductionPlan(map);
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
	public ResponseInfo zfAsphaltProductionPlan(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = asphaltProductionPlanDao.zfAsphaltProductionPlan(map);
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
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return asphaltProductionPlanDao.getMaterialNameList(map);
	}

	@Override
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map) {
		return asphaltProductionPlanDao.getMaterialModelList(map);
	}

	@Override
	public List<EquipmentInfo> getEquipmentInfo(Map<String, Object> map) {
		return asphaltProductionPlanDao.getEquipmentInfo(map);
	}

	@Override
	public List<AsphaltProdProportion> getProportionCodeList(Map<String, Object> map) {
		return asphaltProductionPlanDao.getProportionCodeList(map);
	}

	@Override
	public List<AsphaltGrading> getGradeCode(Map<String, Object> map) {
		return asphaltProductionPlanDao.getGradeCode(map);
	}

	@Override
	public List<AsphaltProductionPlan> getProductionPlanById(Map<String, Object> map) {
		return asphaltProductionPlanDao.getProductionPlanById(map);
	}

	@Override
	public List<Map<String, Object>> getProjPos(Map<String, Object> map) {
		return asphaltProductionPlanDao.getProjPos(map);
	}

	@Override
	public List<Map<String, Object>> getConstructionUnit(Map<String, Object> map) {
		return asphaltProductionPlanDao.getConstructionUnit(map);
	}

}
