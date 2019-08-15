package com.oil.service.system.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.SalesCompanyDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.model.repertory.NoWeighOutWarehouse;
import com.oil.model.system.SalesCompany;
import com.oil.service.system.SalesCompanyService;
import com.oil.util.PropertyUtil;
@Service
@Transactional
public class SalesCompanyServiceImpl implements SalesCompanyService {
	
	@Autowired
	private SalesCompanyDao salesCompanyDao;
	
	@Override
	public DataTablesResponseInfo getSalesCompanyList(Map<String, Object> map) {
		DataTablesResponseInfo data = new DataTablesResponseInfo();
		List<SalesCompany> dataList = salesCompanyDao.getSalesCompanyList(map);
		for(int i = 0; i < dataList.size(); i++) {
			dataList.get(i).setSerialnumber(i+1);
		}
		if (dataList == null || dataList.size() < 0) {
			dataList = new ArrayList<SalesCompany>();
		}
		data.setData(dataList);
		return data;
	}

	@Override
	public ResponseInfo addSalesCompany(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		//新增销售公司
		SalesCompany data = new SalesCompany();
		//销售公司编号
		data.setCompanyNumber(map.get("companyNumber").toString());
		//销售公司名称
		data.setCompanyName(map.get("companyName").toString());
		//销售公司地址
		data.setCompanyAddress(map.get("companyAddress").toString());
		//销售公司电话
		data.setTelephone(map.get("telephone").toString());
		//销售公司联系人
		data.setContacts(map.get("contacts").toString());
		//备注
		data.setRemarks(map.get("remarks").toString());
		//创建人
		data.setCreater(String.valueOf(userInfo.getId()));
		int result = salesCompanyDao.addSalesCompany(data);
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
	public ResponseInfo updateSalesCompany(HttpServletRequest request, Map<String, Object> map)  throws IOException {
		ResponseInfo info = new ResponseInfo();
		// 获取当前登录用户的信息
		Userinfo userInfo = (Userinfo) request.getSession().getAttribute("user");
		//修改销售公司
		SalesCompany data = new SalesCompany();
		data.setId(Integer.parseInt(map.get("id").toString()));
		//销售公司编号
		data.setCompanyNumber(map.get("companyNumber").toString());
		//销售公司名称
		data.setCompanyName(map.get("companyName").toString());
		//销售公司地址
		data.setCompanyAddress(map.get("companyAddress").toString());
		//销售公司电话
		data.setTelephone(map.get("telephone").toString());
		//销售公司联系人
		data.setContacts(map.get("contacts").toString());
		//备注
		data.setRemarks(map.get("remarks").toString());
		//修改人
		data.setReviser(String.valueOf(userInfo.getId()));
		int result = salesCompanyDao.updateSalesCompany(data);
		if (result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	@Override
	public ResponseInfo delSalesCompany(HttpServletRequest request, Map<String, Object> map) throws IOException {
		ResponseInfo info = new ResponseInfo();
		int result = salesCompanyDao.delSalesCompany(map);
		if (result > 0) {
			info.setCode("success");
			// 操作成功
			info.setMessage(PropertyUtil.getProperties("M0005"));
		} else {
			info.setCode("error");
			// 操作失败
			info.setMessage(PropertyUtil.getProperties("M0006"));
		}
		return info;
	}

	@Override
	public SalesCompany getSalesCompanyInfo(Map<String, Object> map) {
		// 获取信息
		List<SalesCompany> dataList = salesCompanyDao.getSalesCompanyList(map);
		if (dataList != null && dataList.size() > 0) {
			SalesCompany SalesCompany = dataList.get(0);
			return SalesCompany;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getSalesCompanyName() {
		return salesCompanyDao.getSalesCompanyName();
	}

	@Override
	public List<Map<String, Object>> getAllSalesCompany(Map<String, Object> map) {
		return salesCompanyDao.getAllSalesCompany(map);
	}

}
