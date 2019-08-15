package com.oil.service.procurementApplication.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.procurementApplication.PurchaseRequisitionDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionaty;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.procurementApplication.PurchaseRequisition;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.service.procurementApplication.PurchaseRequisitionService;
import com.oil.util.PropertyUtil;
@Service
@Transactional
public class PurchaseRequisitionServiceImpl implements PurchaseRequisitionService {

	@Autowired
	private PurchaseRequisitionDao purchaseRequisitionDao;
	
	@Override
	public DataTablesResponseInfo getInfoList(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<PurchaseRequisition> dataList = purchaseRequisitionDao.getInfoList(map);

		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<PurchaseRequisition>();
		}
		info.setData(dataList);
		return info;
	}

	@Override
	public List<PurchaseRequisition> getTreeList(Map<String, Object> map) {
		return purchaseRequisitionDao.getTreeList(map);
	}

	@Override
	public ResponseInfo deletePurchaseRequisition(HttpServletRequest request, Map<String, Object> map)throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		if (userInfo != null) {
			map.put("userId", userInfo.getId());	
		}
		int result = purchaseRequisitionDao.deletePurchaseRequisition(map);
		if (result > 0) {
			info.setCode("success");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 用户或密码错误!
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	@Override
	public List<Datadictionaty> getAllDatadictionaty() {
		return purchaseRequisitionDao.getAllDatadictionaty();
	}

	@Override
	public List<MaterielinfoEntity> getMaterielinfoList(Map<String, Object> map) {
		List<MaterielinfoEntity> dataList = purchaseRequisitionDao.getMaterielinfoList(map);
		return dataList;
	}

	@Override
	public ResponseInfo addPurchaseRequisition(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		PurchaseRequisition data = new PurchaseRequisition();
		//单据编号
		data.setSerialID(map.get("serialID").toString());
		//采购物资
		data.setGoodsName(Integer.parseInt(map.get("goodsName").toString()));
		//规格型号
		data.setModel(map.get("model").toString());
		//申请数量
		data.setApplyNumber(Double.valueOf(map.get("applyNumber").toString()));
		//紧急状态
		data.setIsUrgentState(Integer.parseInt(map.get("isUrgentState").toString()));
		//预计到位时间
		data.setEstimatedTime(map.get("estimatedTime").toString());
		//物料采购级别
		data.setPurchaseLevel(map.get("purchaseLevel").toString());
		//申请部门
		data.setApplyDepartment(map.get("applyDepartment").toString());
		//申请人
		data.setApplicant(map.get("applicant").toString());
		//录入采购人
		data.setEntryClerkPurchaser(map.get("entryClerkPurchaser").toString());
		//申请日期
		data.setApplyDate(map.get("applyDate").toString());
		//录入申请人
		data.setEntryClerkApplicant(map.get("entryClerkApplicant").toString());
		//录入时间
		data.setEntryClerkDate(map.get("entryClerkDate").toString());
		//询价人
		//data.setInquirer(map.get("inquirer").toString());
		//询价方1
		data.setInquirer1(map.get("inquirer1").toString());
		//单价1
		data.setUnivalent1(map.get("univalent1") == "" ? 0 : Double.valueOf(map.get("univalent1").toString()));
		//金额1
		data.setMoney1(map.get("money1") == "" ? 0 : Double.valueOf(map.get("money1").toString()));
		//其他信息1
		data.setOtherInfo1(map.get("otherInfo1").toString());
		//评价1
		data.setAssess1(map.get("assess1").toString());
		//询价方2
		data.setInquirer2(map.get("inquirer2").toString());
		//单价2
		data.setUnivalent2(map.get("univalent2") == "" ? 0 : Double.valueOf(map.get("univalent2").toString()));
		//金额2
		data.setMoney2(map.get("money2") == "" ? 0 : Double.valueOf(map.get("money2").toString()));
		//其他信息2
		data.setOtherInfo2(map.get("otherInfo2").toString());
		//评价2
		data.setAssess2(map.get("assess2").toString());
		//询价方3
		data.setInquirer3(map.get("inquirer3").toString());
		//单价3
		data.setUnivalent3(map.get("univalent3") == "" ? 0 : Double.valueOf(map.get("univalent3").toString()));
		//金额3
		data.setMoney3(map.get("money3") == "" ? 0 : Double.valueOf(map.get("money3").toString()));
		//其他信息3
		data.setOtherInfo3(map.get("otherInfo3").toString());
		//评价3
		data.setAssess3(map.get("assess3").toString());
		//采购员备注
		data.setBuyerRemarks(map.get("buyerRemarks").toString());
		//创建者
		data.setCreater(String.valueOf(userInfo.getId()));
		int result = purchaseRequisitionDao.addPurchaseRequisition(data);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}

	@Override
	public ResponseInfo updatePurchaseRequisition(HttpServletRequest request, Map<String, Object> map)throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		PurchaseRequisition data = new PurchaseRequisition();
		//id
		data.setId(Integer.parseInt(map.get("id").toString()));
		//单据编号
		data.setSerialID(map.get("serialID").toString());
		//采购物资
		data.setGoodsName(Integer.parseInt(map.get("goodsName").toString()));
		//规格型号
		data.setModel(map.get("model").toString());
		//申请数量
		data.setApplyNumber(Double.valueOf(map.get("applyNumber").toString()));
		//紧急状态
		data.setIsUrgentState(Integer.parseInt(map.get("isUrgentState").toString()));
		//预计到位时间
		data.setEstimatedTime(map.get("estimatedTime").toString());
		//物料采购级别
		data.setPurchaseLevel(map.get("purchaseLevel").toString());
		//申请部门
		data.setApplyDepartment(map.get("applyDepartment").toString());
		//申请人
		data.setApplicant(map.get("applicant").toString());
		//录入采购人
		data.setEntryClerkPurchaser(map.get("entryClerkPurchaser").toString());
		//申请日期
		data.setApplyDate(map.get("applyDate").toString());
		//录入申请人
		data.setEntryClerkApplicant(map.get("entryClerkApplicant").toString());
		//录入时间
		data.setEntryClerkDate(map.get("entryClerkDate").toString());
		//询价人
		//data.setInquirer(map.get("inquirer").toString());
		//询价方1
		data.setInquirer1(map.get("inquirer1").toString());
		//单价1
		data.setUnivalent1(map.get("univalent1") == "" ? 0 : Double.valueOf(map.get("univalent1").toString()));
		//金额1
		data.setMoney1(map.get("money1") == "" ? 0 : Double.valueOf(map.get("money1").toString()));
		//其他信息1
		data.setOtherInfo1(map.get("otherInfo1").toString());
		//评价1
		data.setAssess1(map.get("assess1").toString());
		//询价方2
		data.setInquirer2(map.get("inquirer2").toString());
		//单价2
		data.setUnivalent2(map.get("univalent2") == "" ? 0 : Double.valueOf(map.get("univalent2").toString()));
		//金额2
		data.setMoney2(map.get("money2") == "" ? 0 : Double.valueOf(map.get("money2").toString()));
		//其他信息2
		data.setOtherInfo2(map.get("otherInfo2").toString());
		//评价2
		data.setAssess2(map.get("assess2").toString());
		//询价方3
		data.setInquirer3(map.get("inquirer3").toString());
		//单价3
		data.setUnivalent3(map.get("univalent3") == "" ? 0 : Double.valueOf(map.get("univalent3").toString()));
		//金额3
		data.setMoney3(map.get("money3") == "" ? 0 : Double.valueOf(map.get("money3").toString()));
		//其他信息3
		data.setOtherInfo3(map.get("otherInfo3").toString());
		//评价3
		data.setAssess3(map.get("assess3").toString());
		//采购员备注
		data.setBuyerRemarks(map.get("buyerRemarks").toString());
		//修改者
		data.setReviser(String.valueOf(userInfo.getId()));
		int result = purchaseRequisitionDao.updatePurchaseRequisition(data);
		if (result > 0) {
			info.setCode("success");
			// 保存成功
			info.setMessage(PropertyUtil.getProperties("M0007"));
		} else {
			info.setCode("error");
			// 保存失败
			info.setMessage(PropertyUtil.getProperties("M0008"));
		}
		return info;
	}

	@Override
	public PurchaseRequisition getInfoListById(Map<String, Object> map) {
		// 获取信息
		List<PurchaseRequisition> dataList = purchaseRequisitionDao.getInfoList(map);
		if (dataList != null && dataList.size() > 0) {
			PurchaseRequisition PurchaseRequisition = dataList.get(0);
			return PurchaseRequisition;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPurchaseorderinfo(Map<String, Object> map) {
		return purchaseRequisitionDao.getAllPurchaseorderinfo(map);
	}

	@Override
	public List<MaterielinfoEntity> getMaterielModel(Map<String, Object> map) {
		return purchaseRequisitionDao.getMaterielModel(map);
	}

}
