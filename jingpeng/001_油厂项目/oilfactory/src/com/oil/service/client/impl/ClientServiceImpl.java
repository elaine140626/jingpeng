package com.oil.service.client.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.client.ClientDao;
import com.oil.model.Arrears;
import com.oil.model.CityInfo;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.Provinceinfo;
import com.oil.service.client.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
		
	@Autowired
	private ClientDao clientDao;

	@Override
	public List<Customerinfo> getCustomerInfoList(Map<String, Object> map) {
		//获取客户信息数据
		List<Customerinfo> customerInfoList = clientDao.getCustomerInfoList(map);
		return customerInfoList;
	}

	//添加客户信息
	@Override
	public int addCustomerinfo(Customerinfo customerinfo) {
		return clientDao.addCustomerinfo(customerinfo);
	}

	//通过客户名称查询客户信息
	@Override
	public List<Customerinfo> findCustomerinfo(String customername) {
		return clientDao.findCustomerinfo(customername);
	}

	//通过Id查询客户信息
	@Override
	public Customerinfo findCustomerinfoById(int uuid) {
		return clientDao.findCustomerinfoById(uuid);
	}

	//修改客户信息
	@Override
	public int updateCustomerinfo(Customerinfo customerinfo) {
		return clientDao.updateCustomerinfo(customerinfo);
	}

	//删除客户信息
	@Override
	public ResponseInfo delCustomerinfoById(int uuid) {
		ResponseInfo info = new ResponseInfo();		
		// 判断客户是否被合同调用
		// 判断客户是否被止运地调用
		if (clientDao.getCustomerIdCount(uuid) > 0 || clientDao.getCustomerIdCount2(uuid) > 0) {
			info.setMessage("客户被调用，不能删除！");
			info.setCode("error");
		} else {
			int res = 0;
			// 删除客户信息
			res += clientDao.delCustomerinfoById(uuid);
			// 删除欠款明细
			res += clientDao.delArrears(uuid);	
			if(res>0) {
				info.setMessage("删除成功");
				info.setCode("success");
			} else {
				info.setMessage("删除失败");
				info.setCode("error");
			}		
		}	
		return info;
	}

	//查询数据字典等级
	@Override
	public List<Datadictionary> getDatadictionaryList() {
		return clientDao.getDatadictionaryList();
	}

	//查询所有的省
	@Override
	public List<Provinceinfo> getProvinceinfoList() {
		return clientDao.getProvinceinfoList();
	}

	//查询省下所有的市
	@Override
	public List<CityInfo> getCityInfoList(int ProvinceId) {
		return clientDao.getCityInfoList(ProvinceId);
	}

	//添加欠款明细
	@Override
	public int addArrears(Arrears arrears) {
		return clientDao.addArrears(arrears);
	}

	//修改删除欠款明细(物理删)
	@Override
	public int updateArrears(int customerId) {
		return clientDao.updateArrears(customerId);
	}

	//通过id查询所在的市
	@Override
	public CityInfo getCityInfo(int id) {
		return clientDao.getCityInfo(id);
	}

	//通过市查询所在的省
	@Override
	public Provinceinfo getProvinceinfo(int provinceId) {
		return clientDao.getProvinceinfo(provinceId);
	}
	
	//通过客户id查询欠款明细
	@Override
	public List<Arrears> getArrears(int customerId) {
		return clientDao.getArrears(customerId);
	}

	//根据客户父子级查询客户菜单
	@Override
	public DataTablesResponseInfo findByNameAndId(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Customerinfo> clientbackList = clientDao.findByNameAndId(map);
		for (int i = 0; i < clientbackList.size(); i++) {
			clientbackList.get(i).setRownum(i+1);
		}
		info.setData(clientbackList);
		return info;
	}
	//查询用户信息ztree
	@Override
	public List<Map<String,Object>> getClient_back() {
		return clientDao.getClient_back();
	}

	//修改欠款明细(逻辑删)
	@Override
	public int updateArrearsInfo(int id) {
		return clientDao.updateArrearsInfo(id);
	}

//	@Override
//	public int delArrears(int customerId) {
//		return clientDao.delArrears(customerId);
//	}
	
}
