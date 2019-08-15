package com.mixingStation.service.asphalt.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixingStation.dao.asphalt.AsphaltProdProportionDao;
import com.mixingStation.model.DataTablesResponseInfo;
import com.mixingStation.model.ResponseInfo;
import com.mixingStation.model.asphalt.AsphaltProdProportion;
import com.mixingStation.model.materialInfo.MaterialInfo;
import com.mixingStation.model.userInfo.UserInfo;
import com.mixingStation.service.asphalt.AsphaltProdProportionService;
import com.mixingStation.util.BaseController;
import com.mixingStation.util.MessageUtil;
@Service
public class AsphaltProdProportionServiceImpl extends BaseController implements AsphaltProdProportionService {
	
	@Autowired
	private AsphaltProdProportionDao asphaltProdProportionDao;

	@Override
	public DataTablesResponseInfo getAllAsphaltProdProportion(Map<String, Object> map) {
		DataTablesResponseInfo dtr = new DataTablesResponseInfo();
		List<AsphaltProdProportion> data = asphaltProdProportionDao.getAllAsphaltProdProportion(map);
		dtr.setData(data);
		return dtr;
	}

	@Override
	public List<MaterialInfo> getMaterialNameList(Map<String, Object> map) {
		return asphaltProdProportionDao.getMaterialNameList(map);
	}

	@Override
	public List<MaterialInfo> getMaterialModelList(Map<String, Object> map) {
		return asphaltProdProportionDao.getMaterialModelList(map);
	}

	@Override
	public List<Map<String, Object>> getAsphTargetProCodeByProductId(Map<String, Object> map) {
		return asphaltProdProportionDao.getAsphTargetProCodeByProductId(map);
	}

	@Override
	public List<Map<String, Object>> getGradeCodeByIdByProductId(Map<String, Object> map) {
		return asphaltProdProportionDao.getGradeCodeByIdByProductId(map);
	}

	@Override
	public ResponseInfo addAsphaltProdProportion(HttpServletRequest request,Map<String,Object> map) {
		ResponseInfo info = new ResponseInfo();
		//新增沥青生产配合比
		AsphaltProdProportion data = new AsphaltProdProportion();
		//获取当前用户
		UserInfo loginUser = this.getLoginUser(request);
		//组织机构
		data.setOrgId(Integer.parseInt(map.get("orgId").toString()));
		//生产配合比编号
		data.setProportionCode(map.get("proportionCode").toString());
		//产品名称+产品型号
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		//目标配合比编号
		data.setTargPropId(Integer.parseInt(map.get("targPropId").toString()));
		//级配编号
		data.setGradId(Integer.parseInt(map.get("gradId").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		//1#仓
		data.setNo1SetValue(Double.valueOf(map.get("no1SetValue").toString()));
		//2#仓
		data.setNo2SetValue(Double.valueOf(map.get("no2SetValue").toString()));
		//3#仓
		data.setNo3SetValue(Double.valueOf(map.get("no3SetValue").toString()));
		//4#仓
		data.setNo4SetValue(Double.valueOf(map.get("no4SetValue").toString()));
		//5#仓
		data.setNo5SetValue(Double.valueOf(map.get("no5SetValue").toString()));
		//6#仓
		data.setNo6SetValue(Double.valueOf(map.get("no6SetValue").toString()));
		//冷粉仓
		data.setColdPowderSetValue(Double.valueOf(map.get("coldPowderSetValue").toString()));
		//冷粉仓2
		data.setColdPowder2SetValue(Double.valueOf(map.get("coldPowder2SetValue").toString()));
		//热粉仓
		data.setHotPowderSetValue(Double.parseDouble(map.get("hotPowderSetValue").toString()));
		//油石比
		data.setAsphaltSetValue(Double.valueOf(map.get("asphaltSetValue").toString()));
		//外掺剂1
		data.setAdmixture1SetValue(Double.valueOf(map.get("admixture1SetValue").toString()));
		//外掺剂2
		data.setAdmixture2SetValue(Double.valueOf(map.get("admixture2SetValue").toString()));
		//创建者
		if(loginUser != null) {
			data.setOperator(loginUser.getUserName());
		}
		int result = asphaltProdProportionDao.addAsphaltProdProportion(data);
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
	public ResponseInfo updateAsphaltProdProportion(HttpServletRequest request, Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		//修改沥青生产配合比
		AsphaltProdProportion data = new AsphaltProdProportion();
		//获取当前用户
		UserInfo loginUser = this.getLoginUser(request);
		//id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//生产配合比编号
		data.setProportionCode(map.get("proportionCode").toString());
		//产品名称+产品型号
		data.setProductId(Integer.parseInt(map.get("productId").toString()));
		//目标配合比编号
		data.setTargPropId(Integer.parseInt(map.get("targPropId").toString()));
		//级配编号
		data.setGradId(Integer.parseInt(map.get("gradId").toString()));
		//备注
		data.setRemarks(map.get("remarks").toString());
		//1#仓
		data.setNo1SetValue(Double.valueOf(map.get("no1SetValue").toString()));
		//2#仓
		data.setNo2SetValue(Double.valueOf(map.get("no2SetValue").toString()));
		//3#仓
		data.setNo3SetValue(Double.valueOf(map.get("no3SetValue").toString()));
		//4#仓
		data.setNo4SetValue(Double.valueOf(map.get("no4SetValue").toString()));
		//5#仓
		data.setNo5SetValue(Double.valueOf(map.get("no5SetValue").toString()));
		//6#仓
		data.setNo6SetValue(Double.valueOf(map.get("no6SetValue").toString()));
		//冷粉仓
		data.setColdPowderSetValue(Double.valueOf(map.get("coldPowderSetValue").toString()));
		//冷粉仓2
		data.setColdPowder2SetValue(Double.valueOf(map.get("coldPowder2SetValue").toString()));
		//热粉仓
		data.setHotPowderSetValue(Double.parseDouble(map.get("hotPowderSetValue").toString()));
		//油石比
		data.setAsphaltSetValue(Double.valueOf(map.get("asphaltSetValue").toString()));
		//外掺剂1
		data.setAdmixture1SetValue(Double.valueOf(map.get("admixture1SetValue").toString()));
		//外掺剂2
		data.setAdmixture2SetValue(Double.valueOf(map.get("admixture2SetValue").toString()));
		//创建者
		if(loginUser != null) {
			data.setModifier(loginUser.getUserName());
		}
		int result = asphaltProdProportionDao.updateAsphaltProdProportion(data);
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
	public List<Map<String, Object>> getAsphaltProdProportionById(Map<String, Object> map) {
		return asphaltProdProportionDao.getAsphaltProdProportionById(map);
	}

	@Override
	public ResponseInfo deleteAsphaltProdProportion(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = asphaltProdProportionDao.deleteAsphaltProdProportion(map);
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
	public List<Map<String, Object>> getPlanByProdId(Map<String, Object> map) {
		return asphaltProdProportionDao.getPlanByProdId(map);
	}
	
}
